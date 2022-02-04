package com.example.clientPoints.exceptionHandler;

import com.example.clientPoints.model.InvalidArgumentDescription;
import com.example.clientPoints.model.InvalidArgumentErrorModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        InvalidArgumentErrorModel invalidArgumentErrorModel = new InvalidArgumentErrorModel("Validation error");
        ex.getBindingResult().getFieldErrors().stream()
                .map(InvalidArgumentDescription::new)
                .forEach(invalidArgumentErrorModel::addDescription);
        return ResponseEntity.badRequest().body(invalidArgumentErrorModel);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String entityNotFoundExceptionHandler(EntityNotFoundException ex, WebRequest request){
        return ex.getMessage();
    }
}
