package it.tim.pay.model.w.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDataOut {
  private BillingData billingData;
  private PayInstrData payInstrData;
  private SEPAData sepaData;
  private CCData ccData;
  private PiStatus piStatus;
  private OpStatus opStatus;
  private PayerData payerData;
}

