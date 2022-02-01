package com.example.clientPoints.controller;

import com.example.clientPoints.model.Client;
import com.example.clientPoints.repository.ClientRepository;
import com.example.clientPoints.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private ClientRepository clientRepository;
    private PointService pointService;

    @Autowired
    public MainController(ClientRepository clientRepository, PointService pointService) {
        this.clientRepository = clientRepository;
        this.pointService = pointService;
    }

    @GetMapping
    @CrossOrigin
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{id}/points")
    @CrossOrigin
    public Integer getPoints(@PathVariable Long id, @RequestParam int year, @RequestParam int month) {
        return pointService.getPointsForClient(id, year, month);
    }

    @GetMapping("/clients/{id}/totalPoints")
    @CrossOrigin
    public Integer getTotalPoints(@PathVariable Long id) {
        return pointService.getTotalPointsForClient(id);
    }

}
