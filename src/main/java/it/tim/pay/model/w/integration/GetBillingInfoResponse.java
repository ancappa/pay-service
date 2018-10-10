package it.tim.pay.model.w.integration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBillingInfoResponse extends BaseResponse {
  private List<CustomerDataOut> customerDataList;
}

