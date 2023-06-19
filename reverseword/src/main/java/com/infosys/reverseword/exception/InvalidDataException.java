package com.infosys.reverseword.exception;

import com.infosys.reverseword.enumeration.ErrorCode;

public class InvalidDataException extends ServiceException {
    public InvalidDataException(ErrorCode code, String message) {
        super(code, message);
    }
}
