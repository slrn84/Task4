package ru.courses2.Task5.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.courses2.Task5.model.response.ResponseError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({HttpClientErrorException.class})
    protected ResponseEntity<ResponseError> handleHttpClientError(HttpClientErrorException ex) {
        return new ResponseEntity<>(new ResponseError(ex.getStatusCode(), ex.getMessage()), ex.getStatusCode());
    }
    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ResponseError> handleException(Exception ex) {
        return new ResponseEntity<>(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}