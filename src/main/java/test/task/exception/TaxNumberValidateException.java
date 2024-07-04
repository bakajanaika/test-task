package test.task.exception;

public class TaxNumberValidateException extends RuntimeException {
    public TaxNumberValidateException(String taxNumber) {
        super(String.format("Invalid tax number - %s", taxNumber));
    }
}
