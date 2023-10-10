package br.com.fiap.cde.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestNotFoundException extends RuntimeException {

    public RestNotFoundException() {
    }

    public RestNotFoundException(String arg0) {
        super(arg0);
    }

    public RestNotFoundException(Throwable arg0) {
        super(arg0);
    }

    public RestNotFoundException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public RestNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }   
}