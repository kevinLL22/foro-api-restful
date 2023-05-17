package co.kevinl.forumapirestful.repository;

import co.kevinl.forumapirestful.model.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity,Long> {
}
