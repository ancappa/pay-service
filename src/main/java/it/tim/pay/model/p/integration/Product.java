package it.tim.pay.model.p.integration;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
  private String code;
  private String description;
  private Integer items;
  private Amount amount;
  private String imgURL;
}

