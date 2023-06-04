package co.kevinl.forumapirestful.dto.answer;

import co.kevinl.forumapirestful.model.AnswerEntity;


public record DataAnswerResponse(
        Long id,
        String message,
        String date,
        String author,
        Integer votes,
        Boolean solve) {

    public DataAnswerResponse(AnswerEntity answerEntity){
        this(answerEntity.getId(), answerEntity.getMessage(), answerEntity.getDate().toString(), answerEntity.getAuthor(),
                answerEntity.getVotes(), answerEntity.isSolve());
    }
}
