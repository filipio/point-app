package com.example.clientPoints.controller;

import com.example.clientPoints.model.Client;
import com.example.clientPoints.model.Transaction;
import com.example.clientPoints.repository.ClientRepository;
import com.example.clientPoints.repository.TransactionRepository;
import com.example.clientPoints.request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
public class TransactionController {
    private TransactionRepository transactionRepository;
    private ClientRepository clientRepository;

    @Autowired
    public TransactionController(TransactionRepository transactionRepository, ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactions() {
        return ResponseEntity.ok(transactionRepository.findAll());
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionRequest transactionRequest) {
        Optional<Client> client = clientRepository.findById(transactionRequest.getClientId());
        if (client.isPresent()) {
            Transaction transaction = new Transaction(client.get(), transactionRequest.getTransactionDate(), transactionRequest.getPrice());
            return ResponseEntity.ok(transactionRepository.save(transaction));
        } else {
            throw new EntityNotFoundException("Can't find client with id=" + transactionRequest.getClientId());
        }
    }
}
