package co.kevinl.forumapirestful.dto;

import co.kevinl.forumapirestful.model.TopicEntity;

import java.sql.Timestamp;

public record DataTopicResponse(String title, String message, Timestamp date, String author, String course) {

    public DataTopicResponse(TopicEntity entity){
        this(entity.getTitle(), entity.getMessage(), entity.getDate(), entity.getAuthor(), entity.getCourse());
    }
}
