package com.example.traffic.config;

import com.example.traffic.etl.CsvImportService;
import com.example.traffic.repository.TrafficRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataImportRunner implements CommandLineRunner {

    private final CsvImportService csvImportService;
    private final TrafficRepository trafficRepository;
    private final CsvProperties csvProperties;

    public DataImportRunner(
            CsvImportService csvImportService,
            TrafficRepository trafficRepository,
            CsvProperties csvProperties
    ) {
        this.csvImportService = csvImportService;
        this.trafficRepository = trafficRepository;
        this.csvProperties = csvProperties;
    }

    @Override
    public void run(String... args) {
        if (trafficRepository.count() == 0) {
            csvImportService.importCsv(csvProperties.getImportPath());
        }
    }
}