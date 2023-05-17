package co.kevinl.forumapirestful.repository;

import co.kevinl.forumapirestful.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserDetails findByEmail(String username);
}
