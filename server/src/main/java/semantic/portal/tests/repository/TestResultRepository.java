package semantic.portal.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semantic.portal.tests.model.Attempt;
import semantic.portal.tests.model.TestResult;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, UUID> {

   List<TestResult> findByUserId(UUID userId);
}
