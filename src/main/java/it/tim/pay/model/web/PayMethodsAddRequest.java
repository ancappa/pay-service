package it.tim.pay.model.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayMethodsAddRequest {
	//sistemaPagamento
	@ApiModelProperty(notes = "Payment system" , required = true, dataType = "string", example = "NETS")
    private String paymentSystem;
    	
	@ApiModelProperty(notes = "Payment channel" , required = true, dataType = "string", example = "TIMMOTORIC")
    private String merId;
	
	@ApiModelProperty(notes = "Payment Transaction Id" , required = true, dataType = "string", example = "111")
    private String txId;
	
	@ApiModelProperty(notes = "Payment authorization date" , required = true, dataType = "string", example = "1111-AAAA")
    private String txDt;
	
	@ApiModelProperty(notes = "Payment authorization Id" , required = true, dataType = "int", example = "0")
    private int origTranId;
	
	@ApiModelProperty(notes = "Payment Amount Value" , required = true, dataType = "long", example = "100")
    private long amountValue;
}
