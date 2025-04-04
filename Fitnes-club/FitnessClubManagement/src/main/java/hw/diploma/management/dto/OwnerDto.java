package hw.diploma.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class OwnerDto {

    private Long id;

    private String fullName;
}
