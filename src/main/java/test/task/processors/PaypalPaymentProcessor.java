package test.task.processors;

import org.springframework.stereotype.Component;
import test.task.exception.PaypalPaymentException;

@Component
public class PaypalPaymentProcessor {

    public boolean makePayment(Integer amount) {
        if (amount > 100000) {
            throw new PaypalPaymentException();
        } else return true;
    }
}