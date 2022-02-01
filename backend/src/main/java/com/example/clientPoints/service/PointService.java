package com.example.clientPoints.service;

import com.example.clientPoints.model.Client;
import com.example.clientPoints.model.Transaction;
import com.example.clientPoints.repository.ClientRepository;
import com.example.clientPoints.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PointService {

    private static final Logger logger = LoggerFactory.getLogger(PointService.class);

    private final static int SECOND_THRESHOLD = 100;
    private final static int SECOND_THRESHOLD_MULTIPLIER = 2;
    private final static int FIRST_THRESHOLD = 50;
    private final static int THRESHOLD_DIFF = SECOND_THRESHOLD - FIRST_THRESHOLD;

    private TransactionRepository transactionRepository;
    private ClientRepository clientRepository;

    @Autowired
    public PointService(TransactionRepository transactionRepository, ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
    }

    public Integer getPointsForClient(Long clientId, int year, int month) {
        LocalDate startPeriod = LocalDate.of(year, month, 1);
        LocalDate endPeriod = LocalDate.of(year, month, startPeriod.lengthOfMonth());
        logger.debug("{} = {}", startPeriod, endPeriod);
        System.out.println(String.format("%s - %s", startPeriod, endPeriod));
        Client client = clientRepository.getById(clientId);
        List<Transaction> transactions = transactionRepository.findByClientAndTransactionDateBetween(client, startPeriod, endPeriod);
        return getPoints(transactions);

    }

    public Integer getTotalPointsForClient(Long id) {
        Client client = clientRepository.getById(id);
        List<Transaction> transactions = transactionRepository.findByClient(client);
        return getPoints(transactions);
    }

    private int getPoints(List<Transaction> transactions) {
        return transactions.stream()
                .map(this::calculatePoints)
                .reduce(0, Integer::sum);
    }

    private int calculatePoints(Transaction transaction) {
        int price = (int) transaction.getPrice();
        int secondThresholdDifference = price - SECOND_THRESHOLD;
        if (secondThresholdDifference > 0) {
            return THRESHOLD_DIFF + secondThresholdDifference * SECOND_THRESHOLD_MULTIPLIER;
        }
        int firstThresholdDifference = price - FIRST_THRESHOLD;
        return Math.max(firstThresholdDifference, 0);
    }

}
