package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {
  private String city;
  private String countryCode;
  private String street;
  private String street2;
  private String street3;
  private String postalCode;
  private String state;
}

