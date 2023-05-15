package com.rustdv.marketplace.exception.handler;

import com.rustdv.marketplace.exception.NoSuchElementInEnumException;
import com.rustdv.marketplace.exception.NoUserWithSuchCredentialsException;
import com.rustdv.marketplace.exception.NotUniqueNameException;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
            (
                    {
                            UserAlreadyExistsException.class,
                            NoSuchElementInEnumException.class,
                            NoUserWithSuchCredentialsException.class,
                            NotUniqueNameException.class
                    }
            )
    public ResponseEntity<String> handleException(RuntimeException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());

    }

    @ExceptionHandler
            (
                    {
                            MethodArgumentNotValidException.class
                    }
            )
    public ResponseEntity<String> handleException(MethodArgumentNotValidException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());

    }


}
