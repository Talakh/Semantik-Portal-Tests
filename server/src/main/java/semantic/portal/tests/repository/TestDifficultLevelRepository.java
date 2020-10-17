package semantic.portal.tests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semantic.portal.tests.model.TestDifficultLevel;

import java.util.UUID;

@Repository
public interface TestDifficultLevelRepository extends JpaRepository<TestDifficultLevel, UUID> {
}
