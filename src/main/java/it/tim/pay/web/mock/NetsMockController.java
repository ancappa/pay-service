package it.tim.pay.web.mock;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.tim.pay.model.p.integration.Amount;
import it.tim.pay.model.p.integration.CapturePaymentRequest;
import it.tim.pay.model.p.integration.CapturePaymentResponse;
import it.tim.pay.model.p.integration.TransactionRespData;
import it.tim.pay.model.p.integration.TransactionResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class NetsMockController {

	
      
    
    @PostMapping(value = "TIM_CG_SERVICES/pgw/payment/capture")
    ResponseEntity<CapturePaymentResponse> capture(
    		@RequestHeader HttpHeaders headers,
    		@RequestBody CapturePaymentRequest request){
    	
    	CapturePaymentResponse capturePaymentResponse = new CapturePaymentResponse();
    	
    	log.info("Start N&TS payment MOCK");
    	log.debug("MOCK HEADERS RECEIVED:"+headers.toString());
    	log.debug("MOCK PARAMS RECEIVED:"+request.toString());
    	
    	TransactionRespData txHead = new TransactionRespData();

        txHead.setMerId("TEST_ECOM");
        txHead.setTxId(request.getTxHead().getTxId());
        txHead.setResultCode("IGFS_000");
        txHead.setErrDescription("TRANSACTION OK");
    	
        // txRes
        TransactionResponse txRes = new TransactionResponse();
        long i = 123L;
        txRes.setTranId(i);
        
        txRes.setPaymentId("paymentID_1111");
        List<String> merTxInfo = new ArrayList<String>();
        merTxInfo.add("Anna merchant");
        txRes.setMerTxInfo(merTxInfo);
        
        Amount pendingAmount = new Amount();
        pendingAmount.setCurrency("EUR");
        pendingAmount.setValue(500);
        
        capturePaymentResponse.setPendingAmount(pendingAmount);
        capturePaymentResponse.setTxHead(txHead);
        capturePaymentResponse.setTxRes(txRes);
   
    	return ResponseEntity.ok(capturePaymentResponse);    	
    }
    
    
	
	
	
    
    
    
}
