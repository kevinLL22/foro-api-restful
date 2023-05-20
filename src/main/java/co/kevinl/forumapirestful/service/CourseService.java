package co.kevinl.forumapirestful.service;

import co.kevinl.forumapirestful.dto.DataEditCourse;
import co.kevinl.forumapirestful.dto.DataNewCourse;
import co.kevinl.forumapirestful.model.CourseEntity;
import co.kevinl.forumapirestful.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<CourseEntity> findAll(){
        return courseRepository.findAll();
    }

    public CourseEntity findById(Long id){

        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteById(Long id){
        courseRepository.deleteById(id);
    }

    public CourseEntity editCourse(DataEditCourse editCourse){

        CourseEntity courseEntity = courseRepository.findById(editCourse.id())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        courseEntity.setName(editCourse.name());
        courseEntity.setCategory(editCourse.category());
        courseRepository.save(courseEntity);
        return courseEntity;

    }
}
