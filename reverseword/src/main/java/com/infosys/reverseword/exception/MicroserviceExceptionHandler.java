package com.infosys.reverseword.exception;

import com.infosys.reverseword.enumeration.ErrorCode;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.StringJoiner;
@ControllerAdvice
public class MicroserviceExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionResponseCreator exceptionResponseCreator;
    @Autowired
    public MicroserviceExceptionHandler(ExceptionResponseCreator exceptionResponseCreator){
        this.exceptionResponseCreator = exceptionResponseCreator;
    }

    @ExceptionHandler(value = InvalidDataException.class)
    protected  ResponseEntity<Object> handleException(InvalidDataException invalidDataException){
        String errorMessage  = invalidDataException.getMeessage();
        return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.PRECONDITION_FAILED, invalidDataException.getCode(), invalidDataException, errorMessage);
    }

    @ExceptionHandler(value = Exception.class)
    protected final ResponseEntity<Object> handleException(final BadRequestException badRequestException){
        String errorMessage  = badRequestException.getMessage();
        return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.GENERAL_ERROR, badRequestException, badRequestException.getMessage());
    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers,
            HttpStatus status, WebRequest request
    ){
        StringJoiner errorMessage = new StringJoiner(",");
        if(methodArgumentNotValidException.getBindingResult().getFieldErrors().isEmpty()){
            if(!methodArgumentNotValidException.getBindingResult().getAllErrors().isEmpty()){
                ObjectError error = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0);
                String message = methodArgumentNotValidException.getMessage();
                return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.PRECONDITION_FAILED, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, methodArgumentNotValidException, message);
            }
        } else {
            for(FieldError error: methodArgumentNotValidException.getBindingResult().getFieldErrors()){
                errorMessage.add(error.getField() + ":" + error.getDefaultMessage());
            }
        }
        return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.BAD_REQUEST, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, methodArgumentNotValidException, errorMessage.toString());
    }
}
