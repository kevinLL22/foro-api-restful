package co.kevinl.forumapirestful.dto.answer;

import co.kevinl.forumapirestful.model.AnswerEntity;

import java.sql.Timestamp;

public record DataAnswerResponse(
        Long id,
        String message,
        Timestamp date,
        String author,
        Integer votes,
        Boolean solve) {

    public DataAnswerResponse(AnswerEntity answerEntity){
        this(answerEntity.getId(), answerEntity.getMessage(), answerEntity.getDate(), answerEntity.getAuthor(),
                answerEntity.getVotes(), answerEntity.isSolve());
    }
}
