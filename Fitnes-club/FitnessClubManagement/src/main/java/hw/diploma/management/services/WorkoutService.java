package hw.diploma.management.services;

import hw.diploma.management.dto.WorkoutCreateDto;
import hw.diploma.management.dto.WorkoutDto;
import hw.diploma.management.dto.WorkoutUpdateDto;

import java.util.List;

public interface WorkoutService {

    List<WorkoutDto> findAllWorkouts();

    WorkoutDto create(WorkoutCreateDto workoutCreateDto);

    WorkoutDto update(WorkoutUpdateDto workoutUpdateDto);

    void deleteWorkoutById(Long id);
}
