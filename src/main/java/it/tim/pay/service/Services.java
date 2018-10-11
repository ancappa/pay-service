package it.tim.pay.service;

import static it.tim.pay.service.IdsGenerator.generateTransactionId;
import static it.tim.pay.service.IdsGenerator.generateUUID;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.tim.pay.integration.proxy.Proxy;
import it.tim.pay.model.configuration.BuiltInConfiguration;
import it.tim.pay.model.p.integration.Amount;
import it.tim.pay.model.p.integration.CapturePaymentRequest;
import it.tim.pay.model.p.integration.CapturePaymentResponse;
import it.tim.pay.model.p.integration.TransactionData;
import it.tim.pay.model.p.integration.TransactionRequest;
import it.tim.pay.model.p.integration.TransactionRespData;
import it.tim.pay.model.web.PayMethodsAddRequest;
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


	public CapturePaymentResponse capture(PayMethodsAddRequest payMethodsAddRequest){

		log.info("Calling service capture...");

		String validSystemPayment = bIC.getValidpaysystem();
		log.info("Received payment system " + payMethodsAddRequest.getPaymentSystem());
		
		if (payMethodsAddRequest.getPaymentSystem().trim().equalsIgnoreCase(validSystemPayment) 
				|| payMethodsAddRequest.getPaymentSystem().trim().isEmpty()){
		
			CapturePaymentRequest capturePaymentRequest = new CapturePaymentRequest();

			// setMerId
			TransactionData txHead = new TransactionData();
			txHead.setMerId(payMethodsAddRequest.getMerId());
			txHead.setTxId(payMethodsAddRequest.getTxId());

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

			// merTxInfo
			List<String> merTxInfo = new ArrayList<String>();
			merTxInfo.add(payMethodsAddRequest.getMerId()); 
			txReq.setMerTxInfo(merTxInfo);

			capturePaymentRequest.setTxReq(txReq);
			return proxy.capture(capturePaymentRequest);


		} else {
			log.error("Invalid payment system: pay service manage only payment to NETS system");
			CapturePaymentResponse capturePaymentResponse = new CapturePaymentResponse();

			TransactionRespData txHead = new TransactionRespData();
			txHead.setTxId(payMethodsAddRequest.getTxId());
			txHead.setResultCode("IGFS_00157");
			txHead.setErrDescription("INVALID PAYMENT INSTRUMENT");
			capturePaymentResponse.setTxHead(txHead);

			return capturePaymentResponse;
		}


	}



}
