package com.example.clientPoints.controller;

import com.example.clientPoints.model.Client;
import com.example.clientPoints.repository.ClientRepository;
import com.example.clientPoints.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ClientController {

    private ClientRepository clientRepository;
    private PointService pointService;

    @Autowired
    public ClientController(ClientRepository clientRepository, PointService pointService) {
        this.clientRepository = clientRepository;
        this.pointService = pointService;
    }

    @GetMapping("/clients")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{id}/points")
    public Integer getPoints(@PathVariable Long id, @RequestParam int year, @RequestParam int month) {
        return pointService.getPointsForClient(id, year, month);
    }

    @GetMapping("/clients/{id}/totalPoints")
    public Integer getTotalPoints(@PathVariable Long id) {
        return pointService.getTotalPointsForClient(id);
    }

}
