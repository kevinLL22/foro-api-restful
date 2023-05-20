package co.kevinl.forumapirestful.service;

import co.kevinl.forumapirestful.dto.DataEditCourse;
import co.kevinl.forumapirestful.dto.DataNewCourse;
import co.kevinl.forumapirestful.model.CourseEntity;
import co.kevinl.forumapirestful.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()){
            return optionalCourse.get();
        }
        else {
            throw new RuntimeException("Course not found");
        }
    }

    public void deleteById(Long id){
        courseRepository.deleteById(id);
    }

    public CourseEntity editCourse(DataEditCourse editCourse){

        Optional<CourseEntity> optionalCourse = courseRepository.findById(editCourse.id());
        if (optionalCourse.isPresent()){
            CourseEntity courseEntity = optionalCourse.get();
            courseEntity.setName(editCourse.name());
            courseEntity.setCategory(editCourse.category());
            courseRepository.save(courseEntity);
            return courseEntity;
        }
        else {
            throw new RuntimeException("Course not found");
        }

    }
}
