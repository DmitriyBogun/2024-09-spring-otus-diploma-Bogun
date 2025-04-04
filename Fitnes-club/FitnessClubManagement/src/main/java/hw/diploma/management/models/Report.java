package hw.diploma.management.models;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Report {

    private List<Trainer> trainers;

    private List<Visitor> visitors;

}
