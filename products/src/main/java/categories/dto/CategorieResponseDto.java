package categories.dto;

import jakarta.validation.constraints.*;

public record CategorieResponseDto(
        @NotNull(message = "Id is requaired")
        @NotEmpty(message = "Id is requaired")
        @NotBlank(message = "Id is requaired")
        int id,

        @NotNull(message = "Name can not be null")
        @NotBlank(message = "Name can not be empty")
        @Size(min = 1, max = 100, message = "Max size is 100 characters")
        String name,

        @NotNull(message = "description can not be null")
        @NotBlank(message = "description can not be empty")
        @Min(value = 3, message = "Min size is 3 characters")
        String description) {
}
