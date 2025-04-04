package hw.diploma.fc.controller;

import hw.diploma.fc.dto.Visitor;
import hw.diploma.fc.dto.VisitorCreateDto;
import hw.diploma.fc.service.VisitorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/fitness_club/visitor")
public class VisitorController {

    private final VisitorService visitorService;

    @CircuitBreaker(name = "visitorBreaker", fallbackMethod = "unknownVisitorFallback")
    @GetMapping("")
    public List<Visitor> getVisitors() {
        return visitorService.getVisitors();
    }

    @CircuitBreaker(name = "visitorBreaker", fallbackMethod = "unknownVisitorFallback")
    @GetMapping("/{id}")
    public Visitor getVisitor(@PathVariable("id") Long id) {
        return visitorService.getVisitorById(id);
    }

    @Retry(name = "retryCreateVisitor", fallbackMethod = "fallbackAfterRetry")
    @PostMapping
    public Visitor createVisitor(@RequestBody VisitorCreateDto visitorCreateDto){
        return visitorService.createVisitor(visitorCreateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVisitor(@PathVariable("id") Long id){
        visitorService.deleteVisitor(id);
    }

    public String unknownVisitorFallback(Exception e) {
        log.error(e.getMessage());
        return "Босс,я устал)";
    }

    public String fallbackAfterRetry(Exception e) {
        return "Остановись, злобный разработчик,я больше не могу)";
    }
}
