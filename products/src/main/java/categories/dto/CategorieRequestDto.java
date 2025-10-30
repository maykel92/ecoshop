package categories.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategorieRequestDto(
        @NotNull(message = "Name can not be null")
        @NotBlank(message = "Name can not be empty")
        @Size(min = 1, max = 100, message = "Max size is 100 characters")
        String name,

        @NotNull(message = "description can not be null")
        @NotBlank(message = "description can not be empty")
        @Size(min = 1, max = 150, message = "Max size is 150 characters")
        String description
) {
}
