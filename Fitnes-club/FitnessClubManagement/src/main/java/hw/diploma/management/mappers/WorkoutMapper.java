package hw.diploma.management.mappers;

import hw.diploma.management.dto.WorkoutDto;
import hw.diploma.management.models.Trainer;
import hw.diploma.management.models.Workout;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkoutMapper {


    public WorkoutDto modelToDto(Workout workout){
        WorkoutDto workoutDto = new WorkoutDto(workout.getId(),
                workout.getWorkoutName(), workout.getCostOfWorkout(), getTrainers(workout));
        return workoutDto;
    }

    private List<String> getTrainers(Workout workout){
        List<Trainer> trainerList= workout.getTrainers();
        List<String> trainers = trainerList.stream()
                .map(Trainer::getFullName)
                .collect(Collectors.toList());
        return trainers;
    }
}
