package semantic.portal.tests.utils;

import lombok.experimental.UtilityClass;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;

import java.util.ArrayList;
import java.util.List;
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

    public static Integer getRandomKey(Map<Integer, List<ThesisDTO>> thesisMap) {
        int thesisIdNumber = random.nextInt(thesisMap.keySet().size());
        return new ArrayList<>(thesisMap.keySet()).get(thesisIdNumber);
    }
}
