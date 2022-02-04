package com.example.clientPoints.model;

import org.springframework.validation.FieldError;

public class InvalidArgumentDescription {
    private String fieldName;
    private String value;
    private String error;

    public InvalidArgumentDescription(FieldError fieldError) {
        this.fieldName = fieldError.getField();
        this.value = fieldError.getRejectedValue().toString();
        this.error = fieldError.getDefaultMessage();
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getValue() {
        return value;
    }

    public String getError() {
        return error;
    }
}
