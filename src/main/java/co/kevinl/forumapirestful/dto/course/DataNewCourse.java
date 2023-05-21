package co.kevinl.forumapirestful.dto.course;

import jakarta.validation.constraints.NotBlank;

public record DataNewCourse(
        @NotBlank
        String name,
        @NotBlank
        String category) {
}
