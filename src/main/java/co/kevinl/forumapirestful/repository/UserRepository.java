package co.kevinl.forumapirestful.repository;

import co.kevinl.forumapirestful.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
