package test.task.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import test.task.models.requests.CalculateRequest;
import test.task.models.requests.PurchaseRequest;
import test.task.models.responses.CalculateResponse;

@RequestMapping(PurchaseApi.ROOT)
public interface PurchaseApi {
    String ROOT = "api/products";

    @PostMapping("/calculate")
    ResponseEntity<CalculateResponse> calculate(@RequestBody CalculateRequest request);

    @PostMapping("/purchase")
    ResponseEntity<CalculateResponse> makePayment(@RequestBody PurchaseRequest request);
}
