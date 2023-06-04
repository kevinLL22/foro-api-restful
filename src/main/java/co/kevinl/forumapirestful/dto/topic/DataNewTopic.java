package co.kevinl.forumapirestful.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataNewTopic(
        @NotNull
        Long id_course,
        @NotBlank
        String title,
        @NotBlank
        String message) {
}
