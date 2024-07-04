package test.task.processors;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentProcessor {

    public boolean pay(Float amount) {
        return amount >= 100;
    }
}
