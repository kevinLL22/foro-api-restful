package co.kevinl.forumapirestful.controller;

import co.kevinl.forumapirestful.dto.DataCourseResponse;
import co.kevinl.forumapirestful.dto.DataNewCourse;
import co.kevinl.forumapirestful.model.CourseEntity;
import co.kevinl.forumapirestful.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/course")
public class CourseController {
    final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<DataCourseResponse> newCourse(DataNewCourse dataNewCourse, UriComponentsBuilder builder){
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
}
