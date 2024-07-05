package test.task.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import test.task.api.PurchaseApi;
import test.task.models.requests.CalculateRequest;
import test.task.models.requests.PurchaseRequest;
import test.task.models.responses.CalculateResponse;
import test.task.services.PurchaseService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PurchaseController implements PurchaseApi {
    PurchaseService purchaseService;

    @Override
    public ResponseEntity<CalculateResponse> calculate(CalculateRequest request) {
        return ResponseEntity.ok(purchaseService.calculate(request));
    }

    @Override
    public ResponseEntity<CalculateResponse> makePayment(PurchaseRequest request) {
        return ResponseEntity.ok(purchaseService.purchase(request));
    }
}
