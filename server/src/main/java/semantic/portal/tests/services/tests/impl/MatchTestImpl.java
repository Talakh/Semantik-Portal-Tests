package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import semantic.portal.tests.dto.ConceptDto;
import semantic.portal.tests.dto.ThesisDTO;
import semantic.portal.tests.model.Test;
import semantic.portal.tests.services.tests.SPTest;

import java.util.*;

import static java.util.stream.Collectors.*;
import static semantic.portal.tests.enums.ThesesClassEnum.ESSENCE;

@Service
public class MatchTestImpl implements SPTest {
    private static final int DEFINITION_COUNT = 4;

    private static final List<String> thesesTypesForAnswer = Lists.newArrayList(ESSENCE.getValue());
    private static final Random random = new Random();

    @Override
    public Test create(List<ConceptDto> concepts, List<ThesisDTO> theses) {
        Map<Integer, String> conceptMap = concepts.stream()
                .collect(toMap(ConceptDto::getId, ConceptDto::getConcept));
        Map<Integer, List<String>> thesesMap = theses.stream()
                .filter(thesis -> thesesTypesForAnswer.contains(thesis.getClazz()))
                .collect(groupingBy(ThesisDTO::getConceptId, mapping(ThesisDTO::getThesis, toList())));
        Map<String, String> qa = new HashMap<>();

        for (int i = 0; i < DEFINITION_COUNT; i++) {
            List<Integer> conceptIds = new ArrayList<>(thesesMap.keySet());
            int conceptId = conceptIds.get(random.nextInt(conceptIds.size()));
            List<String> answers = thesesMap.get(conceptId);
            qa.put(conceptMap.get(conceptId), answers.get(random.nextInt(answers.size())));
        }
        return null;
    }
}
