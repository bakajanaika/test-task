package test.task.exception;

public class PaypalPaymentException extends RuntimeException {
    public PaypalPaymentException() {
        super("Payment amount too high");
    }
}
