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
public class Order {
  private String invoiceNumber;
  private String invoiceExpirationDate;
  private String senderPostalCode;
  private String senderCountryCode;
  private String destinationName;
  private Address destinationAddress;
  private String destinationPhone;
  private String destinationFax;
  private String destinationEmail;
  private String destinationDate;
  private String billingName;
  private Address billingAddress;
  private String billingPhone;
  private String billingFax;
  private String billingEmail;
  private Amount freightAmount;
  private Amount taxAmount;
  private String vatCode;
  private String note;
  private List<Product> products;
}

