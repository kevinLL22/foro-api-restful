package co.kevinl.forumapirestful.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataNewTopic(
        @NotNull
        Long id_user,
        @NotNull
        Long id_course,
        @NotBlank
        String title,
        @NotBlank
        String message) {
}
