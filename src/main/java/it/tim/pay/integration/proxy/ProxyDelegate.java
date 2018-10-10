package it.tim.pay.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import it.tim.pay.integration.client.NetsCaptureClient;
import it.tim.pay.model.p.integration.CapturePaymentRequest;
import it.tim.pay.model.p.integration.CapturePaymentResponse;
import it.tim.pay.model.p.integration.InitPaymentRequest;
import it.tim.pay.model.p.integration.InitPaymentResponse;
import it.tim.pay.model.p.integration.VerifyPaymentResponse;
import it.tim.pay.model.w.integration.EnrollDeactivateResponse;
import it.tim.pay.model.w.integration.GetBillingInfoResponse;

@Component
public class ProxyDelegate {

   private NetsCaptureClient netsCaptureClient;

    @Autowired
	public ProxyDelegate(NetsCaptureClient netsCaptureClient) {
    	
    	this.netsCaptureClient = netsCaptureClient;
    }

	@HystrixCommand(fallbackMethod = "reliableCapture")
    public ResponseEntity<CapturePaymentResponse> capture(CapturePaymentRequest capturePaymentRequest) {
        return netsCaptureClient.capture(capturePaymentRequest);
    }
    //FALLBACK
    @SuppressWarnings("unused")
    ResponseEntity<CapturePaymentResponse> reliableCapture(CapturePaymentRequest request, Throwable throwable) {
        return ProxyTemplate.getFallbackResponse(throwable);
    }
}
