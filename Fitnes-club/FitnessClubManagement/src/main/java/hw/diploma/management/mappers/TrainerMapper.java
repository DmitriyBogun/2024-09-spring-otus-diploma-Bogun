package hw.diploma.management.mappers;

import hw.diploma.management.dto.TrainerDto;
import hw.diploma.management.models.Trainer;
import hw.diploma.management.models.Visitor;
import hw.diploma.management.models.Workout;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainerMapper {

    public TrainerDto modelToDto(Trainer trainer){

        TrainerDto trainerDto = new TrainerDto(trainer.getId()
                , trainer.getFullName(), trainer.getAge(),
                trainer.getCoachingExperience(),getVisitorsName(trainer.getVisitors()), getWorkouts(trainer));
        return trainerDto;
    }

    private List<String> getWorkouts(Trainer trainer){
        List<Workout> workoutList= trainer.getCompetence();
        List<String> workouts = workoutList.stream()
                .map(Workout::getWorkoutName)
                .collect(Collectors.toList());
        return workouts;
    }

    private List<String> getVisitorsName(List<Visitor> visitors){
        if(visitors==null){
            return null;
        }
        List<String> visitorNames = new ArrayList<>();
        for (Visitor visitor : visitors) {
            String fullName = visitor.getFullName();
            visitorNames.add(fullName);
        }
        return visitorNames;
    }
}
