package com.example.clientPoints.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class TransactionRequest {

    @Past(message = "Transaction date must be in the past.")
    private LocalDate transactionDate;

    @DecimalMin(value = "0", message = "Price can't be negative.")
    private float price;

    @NotNull(message = "Request have to contain non-negative clientId.")
    @Min(value = 1, message = "Client must be positive.")
    private Long clientId;

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
