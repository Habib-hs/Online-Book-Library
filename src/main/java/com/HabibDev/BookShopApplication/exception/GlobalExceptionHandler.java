package com.HabibDev.BookShopApplication.exception;

import com.HabibDev.BookShopApplication.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // handle exceptions globally across all controllers in the application.
public class GlobalExceptionHandler {
    @ExceptionHandler({AuthorNotFoundException.class,
            IllegalArgumentException.class,
            BookNotFoundException.class,
            UserAlreadyExistsException.class,
            RegistrationException.class,
           AuthenticationException.class
          }) // exceptions that will returnNotFoundException method handle

    public ResponseEntity<Object> returnNotFoundException(Exception ex) {

        if(ex instanceof AuthorNotFoundException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        } else if(ex instanceof BookNotFoundException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        } else if(ex instanceof RegistrationException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

        } else if(ex instanceof UserAlreadyExistsException){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

    } else if(ex instanceof AuthenticationException){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }else { //anything else exception
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}