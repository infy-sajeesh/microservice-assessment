package com.infosys.onearray.exception;

import com.infosys.onearray.enumeration.ErrorCode;

public class InvalidDataException extends ServiceException {
    public InvalidDataException(ErrorCode code, String message) {
        super(code, message);
    }
}
