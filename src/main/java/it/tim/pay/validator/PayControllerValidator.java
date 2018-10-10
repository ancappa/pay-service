package it.tim.pay.validator;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import it.tim.pay.common.headers.TimHeaders;
import it.tim.pay.model.exception.BadRequestException;
import it.tim.pay.model.p.integration.PitypeEnum;
public class PayControllerValidator {

	public PayControllerValidator() {}

	  private static final Pattern TX_ID = Pattern.compile("^[a-zA-Z0-9-]{1,256}$");
	  private static final Pattern PAYMENT_ID = Pattern.compile("^[0-9]{1,32}$");
	  
	
	  public static void validateRequest4Init(TimHeaders headers, String paymentMethod) {
	      
	    	validateRequest(headers);
	      
	    	if(PitypeEnum.fromValue(paymentMethod)==null)
	    		throw new BadRequestException("Missing/wrong paymentMethod");
	 } 
	  
	
    public static void validateRequest(TimHeaders headers) {
       
        if(headers == null || headers.getSession() == null
                || StringUtils.isEmpty(headers.getSession().getUserReference()))
            throw new BadRequestException("Missing user reference");
    }
    
    public static void validateRequest4Verify(TimHeaders headers, String txId, String paymentId) {
        
    	validateRequest(headers);
    	
        if(txId == null || !validTxId(txId))
          	throw new BadRequestException("Missing/wrong txId");	
        
        if(paymentId == null || !validPaymentId(paymentId))
        	throw new BadRequestException("Missing/wrong paymentId");
    }
    
    public static void validateRequest4Delete(TimHeaders headers, String billAccntId) {
        
    	validateRequest(headers);
        
        if(billAccntId == null)
        	throw new BadRequestException("Missing/wrong billAccntId");
    }
    
    
    
    
    
    static boolean validTxId(String txId){
        return TX_ID.matcher(txId).matches();
    }

    static boolean validPaymentId(String paymentId){
        return PAYMENT_ID.matcher(paymentId).matches();
    }
    
}
