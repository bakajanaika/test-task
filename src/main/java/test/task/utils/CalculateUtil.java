package test.task.utils;

import test.task.models.responses.CalculateResponse;

public class CalculateUtil {

    public static CalculateResponse calculate(Double tax, Double price, Double discount) {
        double sum = price - discount + (price - discount) * tax;
        double taxSum = (price - discount) * tax;
        return new CalculateResponse(sum, discount, taxSum);
    }
}
