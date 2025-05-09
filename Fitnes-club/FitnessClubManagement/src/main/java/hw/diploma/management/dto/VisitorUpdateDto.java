package hw.diploma.management.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@AllArgsConstructor
@ToString
public class VisitorUpdateDto {

    private Long id;

    @NotBlank(message = "Name can't be null")
    @Size(min = 1, max = 50, message = "Name should be with size from 1 to 50 symbols")
    private String fullName;

    @NotNull(message = "Age can't be null")
    @Min(value = 16,message = "Age should not be less than 16")
    @Max(value = 70, message = "Age should not be greater than 70")
    private int age;
}
