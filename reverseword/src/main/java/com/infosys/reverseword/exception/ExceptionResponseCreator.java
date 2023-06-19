package com.infosys.reverseword.exception;

import com.infosys.reverseword.enumeration.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
@Component
public class ExceptionResponseCreator {

    private static final String MESSAGE_NEW_LINE = "\n";

    Environment environment;
    ExceptionResolver exceptionResolver;
    @Autowired
    public  ExceptionResponseCreator(Environment environment, ExceptionResolver exceptionResolver){
        this.environment = environment;
        this.exceptionResolver = exceptionResolver;
    }

    public ResponseEntity<Object> getExceptionResponseEntity(HttpStatus status, ErrorCode errorCode,
                                                             Exception exception, String message){
        ExceptionResolver resolver = new ExceptionResolver();
        String errorStack = getErrorAsString(exception);
        return new ResponseEntity<>(resolver.resolveError(status, errorCode, message, errorStack), status);
    }

    public String getErrorAsString(Exception exception){
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));
        return errors.toString()
                .replace(MESSAGE_NEW_LINE, " ")
                .replace("\r", " ")
                .replace("\t", " ");
    }
}
