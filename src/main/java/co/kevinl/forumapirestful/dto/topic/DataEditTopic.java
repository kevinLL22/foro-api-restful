package co.kevinl.forumapirestful.dto.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataEditTopic(
        @NotNull
        Long id,
        @NotBlank
        String title,
        @NotBlank
        String message) {

}
