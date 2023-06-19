package com.infosys.fibonacci.exception;

import com.infosys.fibonacci.enumeration.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
@Component
public class ExceptionResolver {

    public ExceptionDetail resolveError(final HttpStatus statusCode, final ErrorCode messageCode,
                                        final String message, final String error){
        final ExceptionDetail.Builder builder = new ExceptionDetail.Builder();
        builder.setDateTime(ZonedDateTime.now().toInstant().toEpochMilli());
        builder.setResponseCode(statusCode.value());
        builder.setMessage(message);
        builder.setMessageCode(Integer.toString(messageCode.getKey()));
        builder.setException(error);
        builder.setStatus("FAILED");

        return builder.build();
    }
}
