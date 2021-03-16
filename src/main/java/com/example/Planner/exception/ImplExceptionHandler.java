package com.example.Planner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ImplExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException requestException) {

         HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

         Exception exception = new Exception(
                 requestException.getMessage(),
                 httpStatus,
                 ZonedDateTime.now(ZoneId.of("Z"))
    );

     return new ResponseEntity<>(exception, httpStatus);

    }


}
