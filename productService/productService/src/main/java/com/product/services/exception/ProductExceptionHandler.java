package com.product.services.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.services.model.ErrorResponse;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(
            final NotFoundException userNotFoundException) {

        List<String> errorList = new ArrayList<>();
        errorList.add(userNotFoundException.getMessage());

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(),
                HttpStatus.NOT_FOUND.toString(),
                errorList
                );

        return new ResponseEntity<>(errorReponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(
            final BadRequestException badRequestException) {

        List<String> errorList = new ArrayList<>();
        errorList.add(badRequestException.getMessage());
        ErrorResponse errorReponse = new ErrorResponse(Instant.now(),
                HttpStatus.BAD_REQUEST.toString(),
                errorList
                );

        return new ResponseEntity<>(errorReponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        List<String> errorReponse = new ArrayList<>();

        for (ObjectError objectError : methodArgumentNotValidException
                .getBindingResult().getAllErrors()) {
            errorReponse.add(objectError.getDefaultMessage());
        }

        ErrorResponse errorDetails = new ErrorResponse(Instant.now(),
                HttpStatus.BAD_REQUEST.toString(), errorReponse);

        return new ResponseEntity<ErrorResponse>(errorDetails,
                HttpStatus.BAD_REQUEST);
    }
}
