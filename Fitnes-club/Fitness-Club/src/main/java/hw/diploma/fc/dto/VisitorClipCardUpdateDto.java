package hw.diploma.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class VisitorClipCardUpdateDto {

    private Long id;

    private String visitorName;

    private String trainerName;

    private String workoutName;

    private int numberOfWorkouts;
}
