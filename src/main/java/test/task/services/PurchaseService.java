package test.task.services;

import test.task.models.requests.CalculateRequest;
import test.task.models.requests.PurchaseRequest;
import test.task.models.responses.CalculateResponse;

public interface PurchaseService {
    CalculateResponse purchase(PurchaseRequest request);

    CalculateResponse calculate(CalculateRequest request);
}
