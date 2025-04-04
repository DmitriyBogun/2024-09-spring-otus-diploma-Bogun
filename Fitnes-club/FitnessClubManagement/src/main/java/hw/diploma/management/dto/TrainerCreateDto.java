package hw.diploma.management.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TrainerCreateDto {

    private Long id;

    @NotBlank(message = "Name can't be null")
    @Size(min = 1, max = 100, message = "Name should be with size from 1 to 50 symbols")
    private String fullName;

    @NotNull(message = "Age can't be null")
    @Min(value = 18,message = "Age should not be less than 18")
    @Max(value = 70, message = "Age should not be greater than 70")
    private int age;

    @NotNull(message = "CoachingExperience can't be null")
    @Min(value = 1,message = "Coaching experience should not be less than 1")
    private int coachingExperience;

    private List<Long> workoutIds;
}
