package com.debesh.smartspend.exceptions;

import com.debesh.smartspend.model.ErrorOutputModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorOutputModel> handleGlobalException(Exception e){
        ErrorOutputModel error = new ErrorOutputModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCutomerCredentialException.class)
    public ResponseEntity<ErrorOutputModel> handleInvalidCutomerCredentialException(InvalidCutomerCredentialException e){
        ErrorOutputModel error = new ErrorOutputModel(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.UNAUTHORIZED);
    }

}
