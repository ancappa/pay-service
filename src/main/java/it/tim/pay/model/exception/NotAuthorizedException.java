package it.tim.pay.model.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by alongo on 16/04/18.
 */
public class NotAuthorizedException extends ErrorResponseException {

    public NotAuthorizedException(String message) {
        super(message, "Parametri della richiesta mancanti o errati", "WAL002", HttpStatus.UNAUTHORIZED, false);
    }

}
