package com.LilGonzz.sicredappvotation.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class GenericNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public GenericNotFoundException(String s) {
        super(s);
    }
}
