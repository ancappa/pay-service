package it.tim.pay.web;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.tim.pay.aspects.Loggable;
import it.tim.pay.common.headers.TimHeaders;
import it.tim.pay.model.p.integration.CapturePaymentResponse;
import it.tim.pay.model.p.integration.InitPaymentResponse;
import it.tim.pay.model.web.PayMethodsAddRequest;
import it.tim.pay.service.Services;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/pay")
@CrossOrigin
@Api("Controller exposing pay operations")
public class PayController {

    private Services services;
    private TimHeaders headers;
    private static final DateTimeFormatter AUTH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    
    /*
    @Autowired
    public PayController(TimHeaders timHeaders, Services findService) {
        this.headers = timHeaders;
        this.services = findService;
    }
    */
    
    @Autowired
	public PayController(Services services) {
    	this.services = services;
	}

    
    @RequestMapping(method = RequestMethod.POST, value = "/capture", produces = "application/json")
    @ApiOperation(value = "Add a payment method in pay", response = InitPaymentResponse.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "SessionID", value = "PARAMETRO PASSANTE. Individua la sessione utente. Formato: UUID 24 caratteri esadecimali", required = true, dataType = "string", paramType = "header"),
        @ApiImplicitParam(name = "BusinessID", value = "PARAMETRO PASSANTE. Individua il processo di business. Formato: UUID 24 caratteri esadecimali", required = true, dataType = "string", paramType = "header"),

        @ApiImplicitParam(name = "MessageID", value = "PARAMETRO NON PASSANTE. Identificativo univoco del singolo messaggio. Formato: UUID 24 caratteri esadecimali", required = true, dataType = "string", paramType = "header"),
        @ApiImplicitParam(name = "TransactionID", value = "PARAMETRO PASSANTE. Identificativo univoco della transazione. Formato: UUID 24 caratteri esadecimali", required = true, dataType = "string", paramType = "header"),
        @ApiImplicitParam(name = "SourceSystem", value = "PARAMETRO NON PASSANTE. Identificativo del sistema chiamante. LOV: WEB, APP, MSITE, CBE, DCA", required = true, dataType = "string", paramType = "header"),
        @ApiImplicitParam(name = "Channel", value = "PARAMETRO PASSANTE. Canale da cui parte la richiesta. LOV: MYTIMWEB, MYTIMAPP, MYTIMMSITE", required = true, dataType = "string", paramType = "header"),
        
        @ApiImplicitParam(name = "InteractionDate-Date", value = "Data interazione utente iniziale. Creato da APP FE, sovrascritto da CBE e poi passante per gli altri sistemi. Formato: YYYY-MM-DD", required = true, dataType = "string", paramType = "header"),
        @ApiImplicitParam(name = "InteractionDate-Time", value = "Orario interazione utente iniziale. Creato da APP FE, sovrascritto da CBE e poi passante per gli altri sistemi. Formato: HH:MM:SS", required = true, dataType = "string", paramType = "header"),
        @ApiImplicitParam(name = "DeviceType", value = "Solo per APP. Indica il tipo di device da cui viene la richiesta. Passante tra APP FE e CBE. LOV: ANDROID, SMARTPHONE, TABLET, I-PAD, IPHONE", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation success"),
            @ApiResponse(code = 400, message = "Missing or wrong mandatory parameters"),
            @ApiResponse(code = 503, message = "Service unavailable")
    })
    @Loggable
    public CapturePaymentResponse capture(
    		@RequestHeader(value = "sessionJWT", required = false) String xSessionJWT,
    		@RequestHeader(value = "businessID", required = false) String xBusinessId,    		
									@RequestHeader(value = "messageID", required = false) String xMessageID,    		
									@RequestHeader(value = "transactionID", required = false) String xTransactionID,    		
									@RequestHeader(value = "channel", required = false) String xChannel,    		
									@RequestHeader(value = "sourceSystem", required = false) String xSourceSystem,    		
									@RequestHeader(value = "sessionID", required = false) String xSessionID,
									@RequestBody  PayMethodsAddRequest request) {// CapturePaymentRequest request) {		
    	//PayControllerValidator.validateRequest4Init(headers, request.getPaymentType());   	
    	log.info("Calling Pay Controller...");
    	//String userReference = "anna";//headers.getSession().getUserReference();
    	//String email = "annamaria.cappa@eng.it";//headers.getSession().getUserAccount();
    	   	
    	return services.capture(request);		
    }
    
    
    
    
    
    
    
    
    
    
}
