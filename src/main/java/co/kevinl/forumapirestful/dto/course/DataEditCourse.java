package co.kevinl.forumapirestful.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataEditCourse(@NotNull Long id, @NotBlank String name, @NotBlank String category) {
}
