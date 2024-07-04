package test.task.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.task.exception.CouponNotFoundException;
import test.task.exception.ProductNotFoundException;
import test.task.exception.TaxNumberValidateException;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<?> handleProductNotFound(ProductNotFoundException e) {
        return this.create(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(CouponNotFoundException.class)
    ResponseEntity<?> handleCouponNotFoundException(CouponNotFoundException e) {
        return this.create(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(TaxNumberValidateException.class)
    ResponseEntity<?> handleTaxNumberValidate(TaxNumberValidateException e) {
        return this.create(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<?> create(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(Map.of("status", status, "error", message));
    }

}
