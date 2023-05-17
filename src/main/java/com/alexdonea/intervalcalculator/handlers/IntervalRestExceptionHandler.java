package com.alexdonea.intervalcalculator.handlers;

import com.alexdonea.intervalcalculator.errors.BadRequestException;
import com.alexdonea.intervalcalculator.errors.IntervalErrorResponse;
import com.alexdonea.intervalcalculator.errors.IntervalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IntervalRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<IntervalErrorResponse> handleException(IntervalNotFoundException exception) {
        IntervalErrorResponse error = new IntervalErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IntervalErrorResponse> handleException(BadRequestException exception) {
        IntervalErrorResponse error = new IntervalErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
