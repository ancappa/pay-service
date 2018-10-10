package it.tim.pay.web;

import static it.tim.pay.service.IdsGenerator.generateTransactionId;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import it.tim.pay.common.headers.TimHeaders;
import it.tim.pay.model.configuration.BuiltInConfiguration;
import it.tim.pay.service.HeaderPreset;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HeaderForwardInterceptor implements RequestInterceptor{

    @Autowired
    private ApplicationContext context;

    @Autowired
    private BuiltInConfiguration bIC;
    
    @Override
    public void apply(RequestTemplate template) {
    	
    	if (template.url().equals("/TIM_CG_PAY/pay/enroll/find") || template.url().equals("/TIM_CG_SERVICES/pgw/payment/verify")) {
			
			HttpHeaders httpHeaders = new HeaderPreset().getHeaders4Get(template.method(), template.request().url(), bIC);
			httpHeaders.forEach((headerName, headerValue) -> template.header(headerName, headerValue));

		} else if (template.url().equals("/TIM_CG_SERVICES/pgw/payment/capture") || template.url().equals("/TIM_CG_PAY/pay/enroll/deactivate")) {
			
			HttpHeaders httpHeaders = new HeaderPreset().getHeaders4Post(template.method(),
																		 template.url(),
																		 new String(template.body(), StandardCharsets.UTF_8), 
																		 bIC);

			httpHeaders.forEach((headerName, headerValue) -> template.header(headerName, headerValue));
			
		} else {
	    		
	        TimHeaders headers = context.getBean(TimHeaders.class);   
	        headers.getTransientHeaders().forEach((headerName, headerValue) -> template.header(headerName.value(),headerValue));
	        
	        log.debug("HTTP-HEADERS before transformation");
	        for (Map.Entry<String, Collection<String>> entry : template.headers().entrySet()) {
	        	log.debug("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        }
	         
	        template.header("sourceSystem","SDP");
	        
	        Date now = new Date(System.currentTimeMillis());
	        
	        if(!template.headers().containsKey("channel")) {
	        	template.header("channel","MYTIMAPP");
	        }
	        
	        if(!template.headers().containsKey("interactionDate-Date")) {
	        	template.header("interactionDate-Date", getDate(now));
	        }
	        
	        if(!template.headers().containsKey("interactionDate-Time")) {
	        	template.header("interactionDate-Time", getTime(now));
	        }
	     
	        if(!template.headers().containsKey("sessionID")) {
	        	template.header("sessionID", generateTransactionId(24));
	        }
	        
	        if(!template.headers().containsKey("businessID")) {
	        	template.header("businessID", generateTransactionId(24));
	        }
	        
	        if(!template.headers().containsKey("transactionID")) {
	        	template.header("transactionID", generateTransactionId(24));
	        }
	         
	        template.header("messageID", generateTransactionId(24));
	        
	        log.debug("HTTP-HEADERS after transformation");
	        for (Map.Entry<String, Collection<String>> entry : template.headers().entrySet()) {
	        	log.debug("Key : " + entry.getKey() + " Value : " + entry.getValue());
	        }
        }
    }   

	private static String getTime(Date d){
		SimpleDateFormat sdfTime = new SimpleDateFormat ( "HH:mm:ss.SSS" );
		return sdfTime.format(d);
	}

	private static String getDate(Date d){
		SimpleDateFormat sdfDate = new SimpleDateFormat ( "yyyy-MM-dd" );
		return sdfDate.format(d);
	}   
}
