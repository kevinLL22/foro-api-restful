package co.kevinl.forumapirestful.repository;

import co.kevinl.forumapirestful.model.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
