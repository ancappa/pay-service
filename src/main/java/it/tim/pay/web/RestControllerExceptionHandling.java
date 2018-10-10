package it.tim.pay.web;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import it.tim.pay.model.exception.ErrorResponseException;
import it.tim.pay.model.web.ErrorResponse;

/**
 * Created by alongo on 16/04/18.
 */
@ControllerAdvice
@Slf4j
public class RestControllerExceptionHandling {

    @ExceptionHandler(value = ErrorResponseException.class)
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex, WebRequest request){
        if(ex.isToLog()) {
            log.error("An error occured: " + ex.getMessage() + " - HTTP STATUS : " + ex.getHttpStatus());
        }   
        
        if(ex.getMessage()!=null && ex.getMessage().indexOf("404")>0) {
            return new ResponseEntity<>(
                    new ErrorResponse(ex.getErrorCode(), "Dati non presenti"),
                    HttpStatus.NOT_FOUND
            );
        	
        }
        
        return new ResponseEntity<>(
                new ErrorResponse(ex.getErrorCode(), ex.getOutputMessage()),
                ex.getHttpStatus()
        );
    }

}
