package com.infosys.fibonacci.enumeration;

public enum ErrorCode {

    GENERAL_ERROR(5000),
    INVALID_DATA(5001),

    ARGUMENT_NOT_VALID_EXCEPTION(5002);

    private final int key;

    ErrorCode(int key) {this.key = key;}

    public int getKey(){ return this.key;}

}
