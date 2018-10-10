package it.tim.pay.validation;

import org.junit.Test;

import it.tim.pay.common.headers.TimHeaders;
import it.tim.pay.common.headers.TimSession;
import it.tim.pay.common.headers.TransientHeaderName;
import it.tim.pay.validator.PayControllerValidator;

/**
 * Created by alongo on 30/04/18.
 */
public class PayControllerValidatorTest {

    @Test
    public void validatePrivateConstructor() throws Exception {
        new PayControllerValidator();
    }

    @Test
    public void validateRequestOk() throws Exception {
        PayControllerValidator.validateRequest(getTimHeader("a reference", "JAVA"));
    }

    @Test(expected = Exception.class)
    public void validateRequestNOK() throws Exception {
        PayControllerValidator.validateRequest(null);
    }




    private TimHeaders getTimHeader(String userReference, String deviceType){
        TimHeaders headers = new TimHeaders();
        headers.setHeader(TransientHeaderName.DEVICE_TYPE, deviceType);
        TimSession session = new TimSession();
        session.setUserReference(userReference);
        headers.setSession(session);
        return headers;
    }

}