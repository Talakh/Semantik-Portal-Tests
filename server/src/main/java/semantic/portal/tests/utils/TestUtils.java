package semantic.portal.tests.utils;

import lombok.experimental.UtilityClass;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;

import java.util.*;
import java.util.stream.Collectors;
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

    public static ThesisDTO getRandomThesis(List<ThesisDTO> thesisDTOS) {
        int conceptIdNumber = random.nextInt(thesisDTOS.size());
        return thesisDTOS.remove(conceptIdNumber);
    }

    public static Map<Integer, ConceptDto> filterPossibleConcepts(List<String>  thesesTypesForAnswer, List<ConceptDto> concepts, List<ThesisDTO> theses) {
        List<Integer> possibleConcepts = theses.stream()
                .filter(t -> thesesTypesForAnswer.contains(t.getClazz()))
                .filter(t -> Objects.isNull(t.getToThesisId()))
                .map(ThesisDTO::getConceptId)
                .collect(Collectors.toList());

        return concepts.stream()
                .filter(conceptDto -> conceptDto.getIsAspect() == 0)
                .filter(conceptDto -> possibleConcepts.contains(conceptDto.getId()))
                .collect(Collectors.toMap(ConceptDto::getId, c -> c));
    }
}
