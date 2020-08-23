package semantic.portal.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semantic.portal.tests.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);
}
