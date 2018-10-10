package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InitPaymentRequest extends BaseRequest {
  private TransactionRequest txReq;
  private PoiProcessorInfoType poiInfo;
  private BuyerInit buyer;
  private PaymentInstrumentInfoPay pi;
  private String errorURL;
  private String callbackURL;
  private String notifyURL;
  private Order order;
}

