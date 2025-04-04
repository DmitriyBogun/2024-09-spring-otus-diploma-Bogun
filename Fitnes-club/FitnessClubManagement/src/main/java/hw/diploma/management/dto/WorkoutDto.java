package hw.diploma.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class WorkoutDto {

    private Long id;

    private String workoutName;

    private int costOfWorkout;

    private List<String> trainers;
}
