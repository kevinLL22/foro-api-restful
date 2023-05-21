package co.kevinl.forumapirestful.dto.answer;

import java.sql.Timestamp;

public record DataAnswerResponse(
        Long id,
        Long id_user,
        Long id_topic,
        String message,
        Timestamp date,
        String author,
        Integer votes,
        Boolean solve
) {
}
