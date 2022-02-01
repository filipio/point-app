package com.example.clientPoints.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "CLIENT_TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    private Client client;

    private LocalDate transactionDate;
    private float price;

    public Transaction() {
    }

    public Transaction(Client client, LocalDate transactionDate, float price) {
        this.client = client;
        this.transactionDate = transactionDate;
        this.price = price;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate date) {
        this.transactionDate = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
