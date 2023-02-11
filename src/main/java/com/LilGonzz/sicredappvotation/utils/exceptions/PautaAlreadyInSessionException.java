package com.LilGonzz.sicredappvotation.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PautaAlreadyInSessionException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public PautaAlreadyInSessionException(String s){
        super(s);
    }
}
