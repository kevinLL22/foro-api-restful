package co.kevinl.forumapirestful.controller;

import co.kevinl.forumapirestful.dto.course.DataCourseResponse;
import co.kevinl.forumapirestful.dto.course.DataEditCourse;
import co.kevinl.forumapirestful.dto.course.DataNewCourse;
import co.kevinl.forumapirestful.model.CourseEntity;
import co.kevinl.forumapirestful.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/course")
@SecurityRequirement(name = "bearer-key")
public class CourseController {
    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<DataCourseResponse> newCourse(@RequestBody @Valid DataNewCourse dataNewCourse, UriComponentsBuilder builder){
        CourseEntity courseEntity = courseService.newCourse(dataNewCourse);
        DataCourseResponse dataCourseResponse = new DataCourseResponse(courseEntity);

        URI uri = builder.path("/course/{id}").buildAndExpand(courseEntity.getId()).toUri();

        return ResponseEntity.created(uri).body(dataCourseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataCourseResponse> GetCourseById (@PathVariable Long id){
        CourseEntity courseEntity = courseService.findById(id);

        DataCourseResponse dataCourseResponse = new DataCourseResponse(courseEntity);

        return ResponseEntity.ok(dataCourseResponse);
    }

    @GetMapping
    public Page<DataCourseResponse> getAllCourses(Pageable pageable){
        return courseService.findAll(pageable).map(DataCourseResponse::new);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataCourseResponse> DeleteCourseById (@PathVariable Long id){
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DataCourseResponse> EditCourse(@RequestBody @Valid DataEditCourse dataEditCourse){
        CourseEntity courseEntity = courseService.editCourse(dataEditCourse);
        DataCourseResponse dataCourseResponse = new DataCourseResponse(courseEntity);

        return ResponseEntity.ok(dataCourseResponse);
    }


}
