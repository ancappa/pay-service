package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.tim.pay.model.p.integration.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * @author Engineering
 *
 */

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CapturePaymentResponse extends BaseResponse {
  private TransactionRespData txHead;
  private TransactionResponse txRes;
  private Amount pendingAmount;
}



