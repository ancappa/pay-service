package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.tim.pay.model.p.integration.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InitPaymentResponse extends BaseResponse {
  private String paymentId;
  private String redirectURL;
}

