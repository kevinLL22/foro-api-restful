package co.kevinl.forumapirestful.service;

import co.kevinl.forumapirestful.dto.DataNewCourse;
import co.kevinl.forumapirestful.model.CourseEntity;
import co.kevinl.forumapirestful.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity newCourse(DataNewCourse dataNewCourse){

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(dataNewCourse.name());
        courseEntity.setCategory(dataNewCourse.category());

        courseRepository.save(courseEntity);

        return courseEntity;
    }
}
