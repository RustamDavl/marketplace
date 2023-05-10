package com.rustdv.marketplace.exception.handler;

import com.rustdv.marketplace.exception.NoSuchElementInEnumException;
import com.rustdv.marketplace.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class, NoSuchElementInEnumException.class})
    public ResponseEntity<String> handleException(UserAlreadyExistsException e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(e.getMessage());

    }


}
