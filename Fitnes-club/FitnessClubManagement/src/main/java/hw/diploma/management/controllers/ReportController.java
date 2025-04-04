package hw.diploma.management.controllers;

import hw.diploma.management.dto.ReportDto;
import hw.diploma.management.services.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/report")
public class ReportController {


    private final ReportService reportService;

    @GetMapping()
    public ReportDto getReport() {
        log.info("getReport()");
        return reportService.create();
    }
}
