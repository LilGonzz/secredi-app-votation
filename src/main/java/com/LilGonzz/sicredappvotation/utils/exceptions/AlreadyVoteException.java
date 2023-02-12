package com.LilGonzz.sicredappvotation.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlreadyVoteException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AlreadyVoteException(String s) {
        super(s);
    }
}
