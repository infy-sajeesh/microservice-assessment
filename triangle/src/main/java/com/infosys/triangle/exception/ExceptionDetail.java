package com.infosys.triangle.exception;

import lombok.Getter;

import java.util.Objects;

@Getter
public class ExceptionDetail {
    
    private final long dateTime;
    
    private final String status;
    
    private final Integer responseCode;

    private final String message;

    private final String messageCode;

    private final String exception;
    
    public ExceptionDetail(final long dateTime, final String status, final Integer responseCode,
                           final String message, final String messageCode, final String exception){
        this.dateTime = dateTime;
        this.status = status;
        this.responseCode = responseCode;
        this.message = message;
        this.exception = exception;
        this.messageCode = messageCode;
    }
    
    @Override
    public boolean equals(final  Object o){
        if(this == o){
            return Boolean.TRUE;
        }
        if(o instanceof ExceptionDetail){
            final ExceptionDetail error = (ExceptionDetail) o;
            return Objects.equals(getStatus(), error.getStatus())
                    && getResponseCode() == error.getResponseCode()
                    && Objects.equals(getMessage(), error.getMessage())
                    && Objects.equals(getMessageCode(), error.getMessageCode())
                    && Objects.equals(getException(), error.getException());
        }
        return Boolean.FALSE;
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(getStatus(), getResponseCode(), getMessage(), getMessageCode(), getException());
    }
    
    @Override
    public String toString(){
        return new StringBuilder().append(getStatus()).toString();
    }
    
    public static class Builder {
        private long dateTime;

        private String status;

        private Integer responseCode;

        private String message;

        private String messageCode;

        private String exception;

        public Builder setDateTime(long dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setResponseCode(Integer responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessageCode(String messageCode) {
            this.messageCode = messageCode;
            return this;
        }

        public Builder setException(String exception) {
            this.exception = exception;
            return this;
        }

        public ExceptionDetail build(){
            return new ExceptionDetail(this.dateTime, this.status, this.responseCode, this.message,
                    this.messageCode, this.exception);
        }
    }
}
