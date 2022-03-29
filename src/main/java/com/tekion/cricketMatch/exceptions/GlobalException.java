package com.tekion.cricketMatch.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(IllegalStateException.class)
        public String handleNotFoundException(IllegalStateException ex) {
            return ex.getMessage();
        }
}
