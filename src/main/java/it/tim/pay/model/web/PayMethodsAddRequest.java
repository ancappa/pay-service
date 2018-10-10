package it.tim.pay.model.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayMethodsAddRequest {
	
	/*
	@ApiModelProperty(notes = "Payment type" , required = true, dataType = "string", example = "CC")
    private String paymentType;
    */
	
	//
	
	@ApiModelProperty(notes = "Payment Amount Value" , required = true, dataType = "long", example = "100")
    private long amountValue;
	
	@ApiModelProperty(notes = "Payment channel" , required = true, dataType = "string", example = "CC")
    private String merId;
	
	@ApiModelProperty(notes = "Payment authorization date" , required = false, dataType = "string", example = "1111-AAAA")
    private String txDt;
	
	@ApiModelProperty(notes = "Payment authorization Id" , required = true, dataType = "int", example = "0")
    private int origTranId;
}
