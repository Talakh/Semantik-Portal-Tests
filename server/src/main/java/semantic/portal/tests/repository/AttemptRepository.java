package semantic.portal.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semantic.portal.tests.model.Attempt;

import java.util.UUID;

public interface AttemptRepository extends JpaRepository<Attempt, UUID> {
}
