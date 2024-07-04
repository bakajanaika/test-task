package test.task.exception;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(String code) {
        super(String.format("Coupon with code %s not found", code));
    }
}
