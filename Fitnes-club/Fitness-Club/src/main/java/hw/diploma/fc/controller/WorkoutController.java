package hw.diploma.fc.controller;

import hw.diploma.fc.dto.Workout;
import hw.diploma.fc.dto.WorkoutCreateDto;
import hw.diploma.fc.service.WorkoutService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/fitness_club/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    @CircuitBreaker(name = "workoutBreaker", fallbackMethod = "unknownWorkoutFallback")
    @GetMapping("/{id}")
    Workout getWorkout(@PathVariable("id") Long id){
        return workoutService.getWorkout(id);
    }


    @CircuitBreaker(name = "workoutBreaker", fallbackMethod = "unknownWorkoutFallback")
    @GetMapping()
    List<Workout> getWorkouts(){
        return workoutService.getWorkouts();
    }

    @Retry(name = "retryCreateWorkout", fallbackMethod = "fallbackAfterRetry")
    @PostMapping()
    Workout createWorkout(@RequestBody WorkoutCreateDto cardCreateDto){
        return workoutService.createWorkout(cardCreateDto);
    }

    @DeleteMapping("/{id}")
    void deleteWorkout(@PathVariable("id") Long id){
        workoutService.deleteWorkout(id);
    }

    public String unknownWorkoutFallback(Exception e) {
        log.error(e.getMessage());
        return "Босс,я устал)";
    }

    public String fallbackAfterRetry(Exception e) {
        return "Остановись, злобный разработчик,я больше не могу)";
    }
}
