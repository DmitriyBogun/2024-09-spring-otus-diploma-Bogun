package hw.diploma.fc.controller;

import hw.diploma.fc.dto.*;
import hw.diploma.fc.service.TrainerService;
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
@RequestMapping("/api/fitness_club/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    @CircuitBreaker(name = "trainerBreaker", fallbackMethod = "unknownTrainerFallback")
    @GetMapping
    public List<Trainer> getTrainers(){
        return trainerService.getTrainers();
    }

    @Retry(name = "retryCreateTrainer", fallbackMethod = "fallbackAfterRetry")
    @PostMapping
    public Trainer createTrainer(@RequestBody TrainerCreateDto trainerCreateDto){
        return trainerService.createTrainer(trainerCreateDto);
    }

    @CircuitBreaker(name = "trainerBreaker", fallbackMethod = "unknownTrainerFallback")
    @GetMapping("/{id}")
    public Trainer getTrainer(@PathVariable("id") Long id){
        return trainerService.getTrainerById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Trainer updateTrainer(@PathVariable("id") Long id,
                                 @RequestBody TrainerUpdateDto trainerUpdateDto){
        return trainerService.updateTrainer(id, trainerUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable("id") Long id){
        trainerService.deleteTrainer(id);
    }

    public String unknownTrainerFallback(Exception e) {
        log.error(e.getMessage());
        return "Босс,я устал)";
    }

    public String fallbackAfterRetry(Exception e) {
        return "Остановись, злобный разработчик,я больше не могу)";
    }
}
