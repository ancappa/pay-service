package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapturePaymentRequest extends BaseRequest {
  private TransactionData txHead;
  private TransactionRequest txReq;
  

}

