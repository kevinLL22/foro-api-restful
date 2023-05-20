package co.kevinl.forumapirestful.dto;

import co.kevinl.forumapirestful.model.CourseEntity;

public record DataCourseResponse(Long id, String name, String category) {

    public DataCourseResponse(CourseEntity entity){
        this(entity.getId(), entity.getName(), entity.getCategory());
    }
}
