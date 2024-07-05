package test.task.exception;

public class InvalidCouponCode extends RuntimeException {
    public InvalidCouponCode() {
        super("Invalid coupon code");
    }
}
