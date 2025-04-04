package hw.diploma.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class TrainerUpdateDto {

    private Long id;

    private String fullName;

    private int age;

    private int coachingExperience;

    private List<Long> workoutIds;

    private List<Long> visitorIds;
}
