package hw.diploma.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class ReportDto {

    private List<TrainerDto> trainers;

    private List<VisitorDto> visitors;
}
