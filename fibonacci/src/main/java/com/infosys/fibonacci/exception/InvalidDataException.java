package com.infosys.fibonacci.exception;

import com.infosys.fibonacci.enumeration.ErrorCode;

public class InvalidDataException extends ServiceException{
    public InvalidDataException(ErrorCode code, String message) {
        super(code, message);
    }
}
