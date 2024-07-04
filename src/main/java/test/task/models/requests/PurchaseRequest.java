package test.task.models.requests;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import test.task.models.enums.PaymentProcessor;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
public class PurchaseRequest {
    UUID productId;
    String taxNumber;
    String couponCode;
    PaymentProcessor paymentProcessor;
}
