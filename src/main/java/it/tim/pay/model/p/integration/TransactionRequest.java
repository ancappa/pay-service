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
public class TransactionRequest {
  private String txDt;
  private int origTranId;
  private Amount amount;
  private List<String> merTxInfo;
  
  /*
  
  private TxOpEnum txOp;
  private String txExpDt;
  private String description;
  private String customerRef;
  */

}

