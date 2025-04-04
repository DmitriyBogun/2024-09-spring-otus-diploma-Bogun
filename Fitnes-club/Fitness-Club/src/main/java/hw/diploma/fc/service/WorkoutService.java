package hw.diploma.fc.service;

import hw.diploma.fc.dto.VisitorCreateDto;
import hw.diploma.fc.dto.Workout;
import hw.diploma.fc.dto.WorkoutCreateDto;

import java.util.List;

public interface WorkoutService {
    List<Workout> getWorkouts();
    Workout getWorkout(Long id);
    Workout createWorkout(WorkoutCreateDto createWorkout);
    void deleteWorkout(Long id);

}
