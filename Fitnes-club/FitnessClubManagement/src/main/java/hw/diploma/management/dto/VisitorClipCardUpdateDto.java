package hw.diploma.management.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class VisitorClipCardUpdateDto {

    private Long id;

    @NotBlank(message = "Name can't be null")
    @Size(min = 1, max = 100, message = "Name should be with size from 1 to 50 symbols")
    private String visitorName;

    private String trainerName;

    private String workoutName;

    @NotNull(message = "Number of workouts can't be null")
    @Min(value = 1,message = "Minimum number of workouts 1")
    @Max(value = 10, message = "Maximum number of workouts 10")
    private int numberOfWorkouts;
}
