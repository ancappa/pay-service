package it.tim.pay.integration.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.tim.pay.integration.FeignConfiguration;
import it.tim.pay.model.p.integration.CapturePaymentRequest;
import it.tim.pay.model.p.integration.CapturePaymentResponse;



@FeignClient(
        name="capture",
        url = "${integration.nets.capture}"
        , configuration = FeignConfiguration.class
)
public interface NetsCaptureClient {
	@PostMapping(path="/TIM_CG_SERVICES/pgw/payment/capture",  consumes = "application/json", produces = "application/json")
    ResponseEntity<CapturePaymentResponse> capture(
   		@RequestBody CapturePaymentRequest request);
}
