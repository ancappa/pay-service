package it.tim.pay.integration.proxy;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;


import it.tim.pay.integration.client.NetsCaptureClient;

import it.tim.pay.integration.proxy.ProxyDelegate;
import it.tim.pay.model.p.integration.CapturePaymentResponse;


/**
 * Created by alongo on 27/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProxyDelegateTest {

	    
    @Mock
	NetsCaptureClient netsCaptureClient;
	
    ProxyDelegate delegate;

    @Before
    public void init(){
        delegate = new ProxyDelegate(netsCaptureClient);
    }

    @After
    public void cleanup(){
        Mockito.reset(netsCaptureClient);
    }

    
    @Test
    public void reliableInitTest(){
        ResponseEntity<CapturePaymentResponse> out = delegate.reliableCapture(null, null);
        assertEquals(ProxyTemplate.getFallbackResponse(null).getStatusCode(), out.getStatusCode());
    }


    
}