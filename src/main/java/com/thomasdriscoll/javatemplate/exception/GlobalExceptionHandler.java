package com.thomasdriscoll.javatemplate.exception;

import com.thomasdriscoll.javatemplate.lib.exceptions.DriscollException;
import com.thomasdriscoll.javatemplate.lib.responses.DriscollResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DriscollException.class)
    public ResponseEntity<DriscollResponse<String>> driscollExceptions(DriscollException e) {
        return ResponseEntity.status(e.getStatus()).body(new DriscollResponse<>(e.getStatus().value(), e.getMessage()));
    }

}
