package hw.diploma.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Trainer {

    Long id;

    private String fullName;

    private int age;

    private int coachingExperience;

    private List<String> visitorsName;

    private List<String> competence;
}
