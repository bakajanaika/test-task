package test.task.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import test.task.exception.*;
import test.task.models.entity.ProductEntity;
import test.task.models.enums.CouponCodes;
import test.task.models.enums.PaymentProcessor;
import test.task.models.enums.TaxCountry;
import test.task.models.requests.PurchaseRequest;
import test.task.models.responses.CalculateResponse;
import test.task.processors.PaypalPaymentProcessor;
import test.task.processors.StripePaymentProcessor;
import test.task.repository.CouponEntityRepository;
import test.task.repository.ProductEntityRepository;
import test.task.services.PurchaseService;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    CouponEntityRepository couponEntityRepository;
    ProductEntityRepository productEntityRepository;

    PaypalPaymentProcessor paypalPaymentProcessor;
    StripePaymentProcessor stripePaymentProcessor;

    @Override
    public CalculateResponse purchase(PurchaseRequest request) {

        ProductEntity product = this.validateAndGetProduct(request.getProductId());
        if (!this.processPayment(product.getPrice(), request.getPaymentProcessor())) {
            throw new UnsupportedPaymentProcessor("Unsupported payment processor");
        }
        Double discount = 0.0;
        if (request.getCouponCode() != null && !request.getCouponCode().isEmpty()) {
            discount = this.validateAndGetCoupon(request.getCouponCode(), product.getPrice());
        }

        Double tax = this.validateAndGetTaxNumber(request.getTaxNumber());
        Double sum = product.getPrice() - discount * tax;
        Double taxSum = product.getPrice() - discount - (product.getPrice() - discount * tax);

        return new CalculateResponse(sum, discount, taxSum);
    }

    public double validateAndGetTaxNumber(String taxNumber) {
        for (TaxCountry tax : TaxCountry.values()) {
            if (Pattern.matches(tax.getPattern(), taxNumber)) {
                return tax.getSum();
            }
        }
        throw new TaxNumberValidateException(taxNumber);
    }

    public ProductEntity validateAndGetProduct(UUID id) {
        return productEntityRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    public Double validateAndGetCoupon(String code, Double sum) {
        Double coupon = Double.valueOf(couponEntityRepository.findByCode(code)
                .orElseThrow(() -> new CouponNotFoundException(code)).getCode().replaceAll("P, S", ""));
        if (code.startsWith(CouponCodes.P.getDescription())) {
            return sum * coupon / 100;
        } else if (code.startsWith(CouponCodes.S.getDescription())) {
            return coupon;
        } else {
            throw new InvalidCouponCode();
        }
    }


    private boolean processPayment(Double amount, PaymentProcessor processor) {
        if (processor.equals(PaymentProcessor.PAYPAL)) {
            return paypalPaymentProcessor.makePayment(amount.intValue());
        } else if (processor.equals(PaymentProcessor.STRIPE)) {
            return stripePaymentProcessor.pay(amount.floatValue());
        }
        throw new UnsupportedPaymentProcessor("Unsupported payment processor");
    }
}
