package hw.diploma.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Workout {

    private Long id;

    private String workoutName;

    private int costOfWorkout;

    private List<String> trainers;
}
