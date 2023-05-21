package co.kevinl.forumapirestful.dto.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataEditAnswer(
        @NotNull
        Long id,
        @NotBlank
        String message) {
}
