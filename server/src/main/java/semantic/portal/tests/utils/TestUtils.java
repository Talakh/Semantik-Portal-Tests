package semantic.portal.tests.utils;

import lombok.experimental.UtilityClass;
import semantic.portal.tests.dto.ConceptDto;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

@UtilityClass
public class TestUtils {
    private final Random random = new Random();

    public static ConceptDto getRandomConcept(Map<Integer, ConceptDto> conceptMap) {
        int conceptIdNumber = random.nextInt(conceptMap.keySet().size());
        Integer conceptId = new ArrayList<>(conceptMap.keySet()).get(conceptIdNumber);
        return conceptMap.remove(conceptId);
    }
}
