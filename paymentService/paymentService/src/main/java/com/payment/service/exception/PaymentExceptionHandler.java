package com.payment.service.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.payment.service.dbo.ErrorResponse;

@ControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(
            final NotFoundException notFoundException) {

        List<String> errorList = new ArrayList<>();
        errorList.add(notFoundException.getMessage());

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(),
                HttpStatus.NOT_FOUND.toString(),
                errorList);

        return new ResponseEntity<>(errorReponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(
            final BadRequestException badRequestException) {

        List<String> errorList = new ArrayList<>();
        errorList.add(badRequestException.getMessage());

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(),
                HttpStatus.NOT_FOUND.toString(),
                errorList);

        return new ResponseEntity<>(errorReponse, HttpStatus.BAD_REQUEST);
    }
}
