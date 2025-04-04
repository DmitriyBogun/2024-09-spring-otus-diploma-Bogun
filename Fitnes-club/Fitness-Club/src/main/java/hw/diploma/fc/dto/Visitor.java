package hw.diploma.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Visitor {

    private Long id;

    private String fullName;

    private int age;

    private List<VisitorClipCard> cards;
}
