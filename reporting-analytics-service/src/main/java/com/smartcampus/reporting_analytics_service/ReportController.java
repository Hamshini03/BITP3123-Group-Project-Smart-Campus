package com.smartcampus.reporting_analytics_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/api/reports/summary")
    public ReportSummary getSummaryReport() {
        return reportService.getSummaryReport();
    }
}
