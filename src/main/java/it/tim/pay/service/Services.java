package it.tim.pay.service;

import static it.tim.pay.service.IdsGenerator.generateTransactionId;
import static it.tim.pay.service.IdsGenerator.generateUUID;

import java.io.UnsupportedEncodingException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tim.pay.integration.proxy.Proxy;
import it.tim.pay.model.configuration.BuiltInConfiguration;
import it.tim.pay.model.p.integration.Amount;
import it.tim.pay.model.p.integration.BuyerInit;
import it.tim.pay.model.p.integration.CapturePaymentRequest;
import it.tim.pay.model.p.integration.CapturePaymentResponse;
import it.tim.pay.model.p.integration.InitPaymentRequest;
import it.tim.pay.model.p.integration.InitPaymentResponse;
import it.tim.pay.model.p.integration.PaymentInstrumentInfoPay;
import it.tim.pay.model.p.integration.PitypeEnum;
import it.tim.pay.model.p.integration.PoiProcessorInfoType;
import it.tim.pay.model.p.integration.TransactionData;
import it.tim.pay.model.p.integration.TransactionRequest;
import it.tim.pay.model.p.integration.TxOpEnum;
import it.tim.pay.model.p.integration.VerifyPaymentResponse;
import it.tim.pay.model.w.integration.CustomerDataOut;
import it.tim.pay.model.w.integration.EnrollDeactivateResponse;
import it.tim.pay.model.w.integration.GetBillingInfoResponse;
import it.tim.pay.model.w.integration.StatusEnumPiStatus;
import it.tim.pay.model.web.PayMethodsAddRequest;
import it.tim.pay.util.CipherUtil;
import it.tim.pay.web.mock.NetsMockController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Services {
   
    @Autowired
    private BuiltInConfiguration bIC;
    
    private Proxy proxy;
    
    @Autowired
    public Services(Proxy proxy) {
        this.proxy = proxy;
    }

   
    
    /*  Capture ********************* */
//public CapturePaymentResponse capture(String merCustId, PayMethodsAddRequest payMethodsAddRequest, String email){
    private CapturePaymentResponse captureOLD(String userReference ,CapturePaymentRequest request, String email){
	
    	log.info("Called service capture ............................");
    	CapturePaymentRequest capturePaymentRequest = new CapturePaymentRequest();
    	
    	TransactionData txHead = new TransactionData();
    	txHead.setMerId(bIC.getMerId());
    	
    	try {
			txHead.setTxId(generateUUID());
		} catch (UnsupportedEncodingException e) {
			txHead.setTxId(generateTransactionId(30));
		} 
    	
    	capturePaymentRequest.setTxHead(txHead);
    	
    	TransactionRequest txReq = new TransactionRequest();
    	//txReq.setTxOp(TxOpEnum.VERIFICATION);
  
    	capturePaymentRequest.setTxReq(txReq);
    	
    	
    	    	
    	/*
    	PoiProcessorInfoType poiInfo = new PoiProcessorInfoType() ;
    	poiInfo.setPitype(PitypeEnum.valueOf(payMethodsAddRequest.getPaymentType()));
		capturePaymentRequest.setPoiInfo(poiInfo);
		
    	PaymentInstrumentInfoPay pi = new PaymentInstrumentInfoPay();
    	pi.setBillAccntId(payMethodsAddRequest.getBillAccntId());
    	
    	
    	if(bIC.getIsCFEncrypted().equals("true"))
    		pi.setMerCustId(CipherUtil.encrypt(bIC.getSecretCF(), merCustId));
    	else
    		pi.setMerCustId(merCustId);
    	
		capturePaymentRequest.setPi(pi);
    	
    	
		capturePaymentRequest.setErrorURL(bIC.getErrorURL());
		capturePaymentRequest.setCallbackURL(bIC.getErrorURL());
		capturePaymentRequest.setNotifyURL(bIC.getNotifyURL());	
    	*/
    	
		return proxy.capture(capturePaymentRequest);
    }
    
    // capture light: simplified version
    public CapturePaymentResponse capture(String transactionID, PayMethodsAddRequest payMethodsAddRequest){
    	
    	log.info("Calling service capture...");
    	CapturePaymentRequest capturePaymentRequest = new CapturePaymentRequest();
    	
    	// setMerId
    	TransactionData txHead = new TransactionData();
    	txHead.setMerId(payMethodsAddRequest.getMerId());
     	txHead.setTxId(transactionID);
		
    	capturePaymentRequest.setTxHead(txHead);
    	
    	// TransactionRequest
    	TransactionRequest txReq = new TransactionRequest();
    	txReq.setTxDt(payMethodsAddRequest.getTxDt());
		txReq.setOrigTranId(payMethodsAddRequest.getOrigTranId());
    	
    	 // amount
    	 Amount amount = new Amount();
         amount.setCurrency("EUR");
         long amountVal = payMethodsAddRequest.getAmountValue();
         amount.setValue((int)amountVal);
         txReq.setAmount(amount);

	     // merTxInfo metto merId ***************** corretto?
	     List<String> merTxInfo = new ArrayList<String>();
	     merTxInfo.add(payMethodsAddRequest.getMerId()); 
	     txReq.setMerTxInfo(merTxInfo);
     	
         capturePaymentRequest.setTxReq(txReq);
    	   	
		return proxy.capture(capturePaymentRequest);
    }
    
}
