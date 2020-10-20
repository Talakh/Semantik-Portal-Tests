package semantic.portal.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import semantic.portal.tests.model.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {


}
