package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerInit extends BuyerBase {
  private String name;
  private String lastName;
  private String msisdn;
  private String account;
  private String imei;
}

