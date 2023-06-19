package com.infosys.reverseword.exception;

import com.infosys.reverseword.enumeration.ErrorCode;
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
