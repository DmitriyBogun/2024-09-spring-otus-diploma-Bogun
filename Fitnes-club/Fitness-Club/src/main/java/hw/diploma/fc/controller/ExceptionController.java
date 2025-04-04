package hw.diploma.fc.controller;


import hw.diploma.fc.dto.ErrorDetails;
import hw.diploma.fc.exceptions.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.util.Date;


@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RestClientException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleRestClientException(RestClientException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorDetails(new Date(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        String message = "Авторизуйся, что-ли=)";
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorDetails(new Date(), message), HttpStatus.FORBIDDEN);
    }
}
