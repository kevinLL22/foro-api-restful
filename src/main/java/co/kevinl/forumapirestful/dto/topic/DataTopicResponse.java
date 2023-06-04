package co.kevinl.forumapirestful.dto.topic;

import co.kevinl.forumapirestful.model.TopicEntity;


public record DataTopicResponse(Long id, String title, String message, String date, String author, String course) {

    public DataTopicResponse(TopicEntity entity){
        this(entity.getId(),entity.getTitle(), entity.getMessage(), entity.getDate().toString(), entity.getAuthor(), entity.getCourse());
    }
}
