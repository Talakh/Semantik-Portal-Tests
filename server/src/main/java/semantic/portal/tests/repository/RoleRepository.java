package semantic.portal.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semantic.portal.tests.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
