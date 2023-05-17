package co.kevinl.forumapirestful.repository;

import co.kevinl.forumapirestful.model.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
}
