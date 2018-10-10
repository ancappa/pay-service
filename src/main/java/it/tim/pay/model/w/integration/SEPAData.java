package it.tim.pay.model.w.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SEPAData {
  private Boolean seda;
  private String mandateId;
  private String mandateDtOfSign;
  private String bicSwift;
  private String billingName;
  private String billingSurname;
  private String billingBusinessName;
  private String subscriberVatCode;
  private String billingVatCode;
  private String billingCountryCode;
  private String billingState;
  private String billingCity;
  private String billingPostalCode;
  private String billingStreet;
  private String billingStreet2;
  private String billingStreet3;
}

