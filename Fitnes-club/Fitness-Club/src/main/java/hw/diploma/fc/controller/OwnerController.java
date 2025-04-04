package hw.diploma.fc.controller;

import hw.diploma.fc.dto.Report;
import hw.diploma.fc.service.OwnerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CircuitBreaker(name = "ownerBreaker", fallbackMethod = "unknownReportFallback")
@RequestMapping("/api/fitness_club/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/report")
    public Report getReport(){
        return ownerService.getReport();
    }

    public String unknownReportFallback(Exception e) {
        log.error(e.getMessage());
        return "Отчета нет=)";
    }
}

