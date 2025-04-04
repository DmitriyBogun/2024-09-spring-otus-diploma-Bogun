package hw.diploma.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class VisitorClipCardDto {

    private Long id;

    private String visitorName;

    private String trainerName;

    private String workoutName;

    private int numberOfWorkouts;
}
