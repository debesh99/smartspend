package com.debesh.smartspend.exceptions;

import com.debesh.smartspend.model.ErrorOutputModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorOutputModel> handleGlobalException(Exception e) {
        ErrorOutputModel error = new ErrorOutputModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // User exceptions
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorOutputModel> handleUserNotFoundException(UserNotFoundException e) {
        ErrorOutputModel error = new ErrorOutputModel(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCutomerCredentialException.class)
    public ResponseEntity<ErrorOutputModel> handleInvalidCustomerCredentialException(InvalidCutomerCredentialException e) {
        ErrorOutputModel error = new ErrorOutputModel(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Account exceptions
    @ExceptionHandler(FIAccountNotFoundException.class)
    public ResponseEntity<ErrorOutputModel> handleFIAccountNotFoundException(FIAccountNotFoundException e) {
        ErrorOutputModel error = new ErrorOutputModel(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Transaction exceptions
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorOutputModel> handleTransactionNotFoundException(TransactionNotFoundException e) {
        ErrorOutputModel error = new ErrorOutputModel(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
