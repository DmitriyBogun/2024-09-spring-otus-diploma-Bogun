package hw.diploma.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Owner {

    private Long id;

    private String fullName;
}
