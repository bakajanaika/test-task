package test.task.processors;

import org.springframework.stereotype.Component;
import test.task.exception.PaypalPaymentException;

@Component
public class PaypalPaymentProcessor {

    public void makePayment(Integer amount) {
        if (amount > 100000) {
            throw new PaypalPaymentException();
        }
    }
}