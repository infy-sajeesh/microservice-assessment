package com.infosys.fibonacci.exception;

import com.infosys.fibonacci.enumeration.ErrorCode;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private final ErrorCode code;

    private final String meessage;

    public ServiceException(final ErrorCode code, final String message){
        this.code = code;
        this.meessage = message;
    }

}
