package com.example.clientPoints.repository;

import com.example.clientPoints.model.Client;
import com.example.clientPoints.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByClientAndTransactionDateBetween(Client client, LocalDate start, LocalDate end);
    List<Transaction> findByClient(Client client);
}
