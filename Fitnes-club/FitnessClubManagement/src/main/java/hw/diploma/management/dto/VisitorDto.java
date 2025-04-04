package hw.diploma.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class VisitorDto {

    private Long id;

    private String fullName;

    private int age;

    private List<VisitorClipCardDto> cards;
}
