package hw.diploma.management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OwnerCreateDto {

    private Long id;

    @NotBlank(message = "Name can't be null")
    @Size(min = 1, max = 100, message = "Name should be with size from 1 to 50 symbols")
    private String fullName;
}
