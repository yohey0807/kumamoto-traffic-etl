package com.example.traffic.repository;

import com.example.traffic.entity.TrafficData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrafficRepository extends JpaRepository<TrafficData, Long> {

    List<TrafficData> findByRoadName(String roadName);

    List<TrafficData> findByCongestionLevel(String congestionLevel);
}
