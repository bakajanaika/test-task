package test.task.exception;

public class UnsupportedPaymentProcessor extends RuntimeException {
    public UnsupportedPaymentProcessor(String s) {
        super(s);
    }
}
