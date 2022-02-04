package com.example.clientPoints.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class InvalidArgumentErrorModel {


    private LocalDateTime timestamp;

    private String errorType;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<InvalidArgumentDescription> descriptions;

    public InvalidArgumentErrorModel(String errorType) {
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
        this.descriptions = new LinkedList<>();
    }


    public void addDescription(InvalidArgumentDescription description){
        descriptions.add(description);
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getErrorType() {
        return errorType;
    }

    public List<InvalidArgumentDescription> getDetails() {
        return descriptions;
    }
}
