package hw.diploma.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class WorkoutUpdateDto {

    private Long id;

    private String workoutName;

    private int costOfWorkout;
}
