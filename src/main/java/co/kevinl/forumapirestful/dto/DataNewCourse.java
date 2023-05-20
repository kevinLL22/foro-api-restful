package co.kevinl.forumapirestful.dto;

import jakarta.validation.constraints.NotBlank;

public record DataNewCourse(
        @NotBlank
        String name,
        @NotBlank
        String category) {
}
