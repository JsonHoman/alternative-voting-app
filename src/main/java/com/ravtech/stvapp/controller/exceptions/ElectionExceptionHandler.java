package com.ravtech.stvapp.controller.exceptions;

import com.ravtech.stvapp.controller.ElectionControllerV1;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@ControllerAdvice(basePackageClasses = ElectionControllerV1.class)
public class ElectionExceptionHandler {

    private ResponseEntity<ErrorResponse> handleException(Exception ex, HttpStatus httpStatus) {
        log.error("Error: \n Failed to create election. \n Exception type: {}. \n Message: {}.",
                    ex.getClass().getSimpleName(),
                    ex.getMessage(),
                    ex);

        // TODO: Prevent sensitive information from being exposed in production logs.

        ErrorResponse error = new ErrorResponse();
        error.setStatus(httpStatus.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(getTimestamp());

        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return handleException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException ex) {
        return handleException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getTimestamp() {
        long timestamp = System.currentTimeMillis();

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(java.time.Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss z");

        return zonedDateTime.format(formatter);
    }
}
