package com.example.clientPoints;

import com.example.clientPoints.model.Client;
import com.example.clientPoints.model.Transaction;
import com.example.clientPoints.repository.ClientRepository;
import com.example.clientPoints.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ClientPointsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientPointsApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertSampleDataToDB(ClientRepository clientRepository, TransactionRepository transactionRepository){
		return args -> {
			List<Client> clients = Arrays.asList(new Client("Ben", "Gates"), new Client("Joe", "Perry"), new Client("Jim", "Smith"), new Client("Benjamin", "Franklin"));
			clients.forEach(clientRepository::save);
			System.out.println(clientRepository.findAll());
			transactionRepository.save(new Transaction(clients.get(0), LocalDate.of(2022, 1, 30), 100));
			transactionRepository.save(new Transaction(clients.get(0), LocalDate.of(2022, 2, 20), 130));
			transactionRepository.save(new Transaction(clients.get(0), LocalDate.of(2022, 3, 15), 70));
			transactionRepository.save(new Transaction(clients.get(0), LocalDate.of(2022, 3, 1), 30));
			transactionRepository.save(new Transaction(clients.get(0), LocalDate.of(2022, 3, 1), 90));

			transactionRepository.save(new Transaction(clients.get(1), LocalDate.of(2022, 1, 27), 240));
			transactionRepository.save(new Transaction(clients.get(1), LocalDate.of(2022, 2, 10), 20));
			transactionRepository.save(new Transaction(clients.get(1), LocalDate.of(2022, 3, 15), 51));

			transactionRepository.save(new Transaction(clients.get(2), LocalDate.of(2022, 1, 15), 10));
			transactionRepository.save(new Transaction(clients.get(2), LocalDate.of(2022, 1, 4), 70));
			transactionRepository.save(new Transaction(clients.get(2), LocalDate.of(2022, 2, 1), 400));
			transactionRepository.save(new Transaction(clients.get(2), LocalDate.of(2022, 3, 15), 100));

			transactionRepository.save(new Transaction(clients.get(3), LocalDate.of(2022, 1, 3), 50));
		};
	}

}
