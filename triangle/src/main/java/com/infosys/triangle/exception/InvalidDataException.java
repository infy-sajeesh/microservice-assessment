package com.infosys.triangle.exception;

import com.infosys.triangle.enumeration.ErrorCode;

public class InvalidDataException extends ServiceException {
    public InvalidDataException(ErrorCode code, String message) {
        super(code, message);
    }
}
