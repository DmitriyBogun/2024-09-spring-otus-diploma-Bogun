package hw.diploma.management.controllers;

import hw.diploma.management.dto.*;
import hw.diploma.management.services.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workout")
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public List<WorkoutDto> getAllWorkouts(){
        return workoutService.findAllWorkouts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutDto createWorkout(@Valid @RequestBody WorkoutCreateDto workoutCreateDto){
        return workoutService.create(workoutCreateDto);
    }

    @PatchMapping("/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public WorkoutDto updateWorkoutByName(@PathVariable(value = "name") String name,
                                                  @Valid @RequestBody WorkoutUpdateDto workoutUpdateDto){
        return workoutService.update(workoutUpdateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkoutById(@PathVariable(value = "id") Long id){
        workoutService.deleteWorkoutById(id);
    }
}
