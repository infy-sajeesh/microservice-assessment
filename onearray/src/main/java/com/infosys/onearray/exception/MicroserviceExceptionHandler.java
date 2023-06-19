package com.infosys.onearray.exception;

import com.infosys.onearray.enumeration.ErrorCode;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

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

    @ExceptionHandler(value = BadRequestException.class)
    protected final ResponseEntity<Object> handleException(final BadRequestException badRequestException){
        String errorMessage  = badRequestException.getMessage();
        return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.GENERAL_ERROR, badRequestException, errorMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptionMethod(Exception ex, WebRequest requset) {

        // Handle All Field Validation Errors
        if(ex instanceof MethodArgumentNotValidException) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb.append(fieldError.getDefaultMessage());
                sb.append(";");
            }
            return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.PRECONDITION_FAILED, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, (Exception) ex, sb.toString());
        }else{
            String errorMessage = ex.getLocalizedMessage();
            return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.BAD_REQUEST, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, (Exception) ex, errorMessage);
        }

    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers,
//            HttpStatus status, WebRequest request
//    ){
//        StringJoiner errorMessage = new StringJoiner(",");
//        if (methodArgumentNotValidException.getBindingResult().getFieldErrors().isEmpty()) {
//            if (!methodArgumentNotValidException.getBindingResult().getAllErrors().isEmpty()) {
//                    ObjectError error = methodArgumentNotValidException.getBindingResult().getAllErrors().get(0);
//                    String message = methodArgumentNotValidException.getMessage();
//                    return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.PRECONDITION_FAILED, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, (Exception) methodArgumentNotValidException, message);
//                }
//            } else {
//                for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
//                    errorMessage.add(error.getField() + ":" + error.getDefaultMessage());
//                }
//        }
//        return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.BAD_REQUEST, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, (Exception) methodArgumentNotValidException, errorMessage.toString());
//    }


//    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
//        logger.info(ex.getClass().getName());
//        //
//        final List<String> errors = new ArrayList<>();
//        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//
//        return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.PRECONDITION_FAILED, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, (Exception) ex, errors.toString());
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValidException(
//            HttpServletRequest request, MethodArgumentNotValidException ex
//    ) {
//        final Optional<ObjectError> firstError = ex.getBindingResult().getAllErrors().stream().findFirst();
//        if (firstError.isPresent()) {
//            final String firstErrorMessage = firstError.get().getDefaultMessage();
//            return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.PRECONDITION_FAILED, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, (Exception) ex, firstErrorMessage);
//        }
//        return exceptionResponseCreator.getExceptionResponseEntity(HttpStatus.BAD_REQUEST, ErrorCode.ARGUMENT_NOT_VALID_EXCEPTION, (Exception) ex, firstError.toString());
//    }

}