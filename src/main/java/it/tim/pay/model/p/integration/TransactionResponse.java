package it.tim.pay.model.p.integration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {
  private String paymentId;
  private Long tranId;
  private StatusEnum status;
  private List<String> merTxInfo;
}

