package it.tim.pay.integration.proxy;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.tim.pay.integration.proxy.Proxy;
import it.tim.pay.integration.proxy.ProxyDelegate;

/**
 * Created by alongo on 27/04/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProxyTest {

    @Mock
    ProxyDelegate delegate;

    Proxy proxy;

    @Before
    public void init(){
        proxy = new Proxy(delegate);
    }

    @After
    public void cleanup(){
        Mockito.reset(delegate);
    }
/*
    @Test
    public void authorizeLineForRecharge() throws Exception {

        String lineNum = "lineNum";
        Mockito.when(delegate.authorizeLineForRecharge(Mockito.anyString(),Mockito.anyString(),Mockito.anyString()))
                .thenReturn(new ResponseEntity<>(
                        new RechargeAuthorizationResponse("A", lineNum, "OK"),
                        HttpStatus.OK));

        String legacyTid = generateTransactionId(24);
        RechargeAuthorizationResponse rechargeAuthorizationResponse = proxy.authorizeLineForRecharge("FRRCFT79P12F836G",lineNum, legacyTid);
        assertNotNull(rechargeAuthorizationResponse);
        //assertEquals(lineNum, rechargeAuthorizationResponse.getNumLinea());
        //assertEquals("A", rechargeAuthorizationResponse.getTransactionIdLegacy());
        //assertEquals("0", rechargeAuthorizationResponse.getStatoUtenza());

    }


    @Test
    public void rechargeWithScratchCard() throws Exception {
        String lineNum = "lineNum";
        String amount = "amount";
        Mockito.when(delegate.rechargeWithScratchCard(Mockito.anyObject(),Mockito.anyObject(),Mockito.anyObject()))
                .thenReturn(new ResponseEntity<>(
                		 new ScratchCardResponse(lineNum, amount, "0", "card", "tid"),
                        HttpStatus.OK));

        ScratchCardResponse scratchCardResponse = proxy.rechargeWithScratchCard("refName", lineNum, new ScratchCardRequest());
        assertNotNull(scratchCardResponse);
        assertEquals(lineNum, scratchCardResponse.getNumLinea());
        assertEquals(amount, scratchCardResponse.getImporto());
        assertEquals("0", scratchCardResponse.getEsito());
    }
*/
    @Test
    public void getSubsystemName() throws Exception {
        assertEquals(Proxy.SUBSYSTEM_NAME, proxy.getSubsystemName());
    }

}