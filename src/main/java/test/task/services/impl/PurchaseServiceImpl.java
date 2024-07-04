package test.task.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import test.task.exception.CouponNotFoundException;
import test.task.exception.ProductNotFoundException;
import test.task.exception.TaxNumberValidateException;
import test.task.models.entity.CouponEntity;
import test.task.models.entity.ProductEntity;
import test.task.models.enums.TaxCountry;
import test.task.models.requests.PurchaseRequest;
import test.task.models.responses.CalculateResponse;
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

    public CalculateResponse purchase(PurchaseRequest request) {
        ProductEntity product = this.validateAndGetProduct(request.getProductId());
        CouponEntity coupon;
        if (request.getCouponCode() != null && !request.getCouponCode().isEmpty()) {
            coupon = this.validateAndGetCoupon(request.getCouponCode());
        }
        Double tax = this.validateAndGetTaxNumber(request.getTaxNumber());

        return null;
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

    public CouponEntity validateAndGetCoupon(String code) {
        return couponEntityRepository.findByCode(code)
                .orElseThrow(() -> new CouponNotFoundException(code));
    }
}
