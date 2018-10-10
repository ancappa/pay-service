package it.tim.pay.integration.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.tim.pay.model.p.integration.CapturePaymentRequest;
import it.tim.pay.model.p.integration.CapturePaymentResponse;
import it.tim.pay.model.p.integration.InitPaymentRequest;
import it.tim.pay.model.p.integration.InitPaymentResponse;
import it.tim.pay.model.p.integration.VerifyPaymentResponse;
import it.tim.pay.model.w.integration.EnrollDeactivateResponse;
import it.tim.pay.model.w.integration.GetBillingInfoResponse;

@Component
public class Proxy extends ProxyTemplate {
	
    static final String SUBSYSTEM_NAME = "Nets Services";
    private ProxyDelegate delegate;

    @Autowired
    public Proxy(ProxyDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    String getSubsystemName() {
        return SUBSYSTEM_NAME;
    }

     
    public CapturePaymentResponse capture(CapturePaymentRequest request ){
        return getBody(delegate.capture(request));
    }

}
