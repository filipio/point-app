package com.example.clientPoints.repository;

import com.example.clientPoints.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
