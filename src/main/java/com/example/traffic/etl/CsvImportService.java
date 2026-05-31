package com.example.traffic.etl;

import com.example.traffic.entity.TrafficData;
import com.example.traffic.repository.TrafficRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CsvImportService {

    private final TrafficRepository repository;

    public CsvImportService(TrafficRepository repository) {
        this.repository = repository;
    }

    public void importCsv(String filePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                TrafficData data = new TrafficData();
                data.setRoadName(values[0]);
                data.setObservationTime(LocalDateTime.parse(values[1], formatter));

                double averageSpeed = Double.parseDouble(values[2]);
                data.setAverageSpeed(averageSpeed);

                data.setTrafficVolume(Integer.parseInt(values[3]));
                data.setCongestionLevel(judgeCongestionLevel(averageSpeed));

                repository.save(data);
            }

        } catch (Exception e) {
            throw new RuntimeException("CSV取込に失敗しました: " + filePath, e);
        }
    }

    private String judgeCongestionLevel(double averageSpeed) {
        if (averageSpeed < 15) {
            return "HIGH";
        } else if (averageSpeed < 25) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}