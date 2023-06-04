package co.kevinl.forumapirestful.dto.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataNewAnswer(
        @NotNull Long id_topic,
        @NotBlank String message) {
}
