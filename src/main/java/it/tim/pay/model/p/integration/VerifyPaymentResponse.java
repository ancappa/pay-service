package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyPaymentResponse extends BaseResponse {
  private TransactionResponse txRes;
  private String nssResult;
  private byte[] receiptPdf;
  private Payer payer;
  private ThreeDDataBase threeD;
  private ProcessorResponse procRes;
  private PoiProcessorInfoBase poiInfo;
  private PaymentInstrumentInfoBase pi;
  private CardInfo cardInfo;
  private Order order;
}

