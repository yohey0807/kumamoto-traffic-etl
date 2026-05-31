package com.example.traffic.controller;

import com.example.traffic.entity.TrafficData;
import com.example.traffic.repository.TrafficRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traffic")
public class TrafficController {

    private final TrafficRepository repository;

    public TrafficController(TrafficRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TrafficData> getAll() {
        return repository.findAll();
    }

    @GetMapping("/road/{roadName}")
    public List<TrafficData> getByRoadName(@PathVariable String roadName) {
        return repository.findByRoadName(roadName);
    }

    @GetMapping("/level/{level}")
    public List<TrafficData> getByCongestionLevel(@PathVariable String level) {
        return repository.findByCongestionLevel(level);
    }
}
