package hw.diploma.management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class WorkoutUpdateDto {

    private Long id;

    @NotBlank(message = "Name of workout can't be null")
    @Size(min = 1, max = 50, message = "Name should be with size from 1 to 50 symbols")
    private String workoutName;

    @NotNull(message = "Cost of workout can't be null")
    @Min(value = 1000, message = "Minimum cost of workouts 1000")
    private int costOfWorkout;
}
