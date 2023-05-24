package co.kevinl.forumapirestful.service;

import co.kevinl.forumapirestful.dto.course.DataEditCourse;
import co.kevinl.forumapirestful.dto.course.DataNewCourse;
import co.kevinl.forumapirestful.model.CourseEntity;
import co.kevinl.forumapirestful.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<CourseEntity> findAll(Pageable pageable){
        return courseRepository.findAll(pageable);
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
