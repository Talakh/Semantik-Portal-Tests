package semantic.portal.tests.services.tests.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import semantic.portal.tests.enums.DifficultLevelEnum;
import semantic.portal.tests.model.TestDifficultLevel;
import semantic.portal.tests.repository.TestDifficultLevelRepository;
import semantic.portal.tests.services.tests.TestDifficultLevelService;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

import static semantic.portal.tests.enums.ThesesClassEnum.*;

@Service
public class TestDifficultLevelServiceImpl implements TestDifficultLevelService {
    private Map<DifficultLevelEnum, TestDifficultLevel> levelsMap = new HashMap<>();

    private final TestDifficultLevelRepository testDifficultLevelRepository;

    public TestDifficultLevelServiceImpl(TestDifficultLevelRepository testDifficultLevelRepository) {
        this.testDifficultLevelRepository = testDifficultLevelRepository;
    }

    @PostConstruct
    public void post() {
        List<TestDifficultLevel> levels = testDifficultLevelRepository.findAll();
        if (levels.isEmpty()) {
            levels = getDefaultLevels();
            testDifficultLevelRepository.saveAll(levels);
        }

        levelsMap = levels.stream()
                .collect(Collectors.toMap(TestDifficultLevel::getTestDifficultId, e -> e));
    }

    @Override
    public TestDifficultLevel getTestDifficultLevel(DifficultLevelEnum difficultLevelEnum) {
        return levelsMap.get(difficultLevelEnum);
    }

    // TODO: 19.10.2020
    @Override
    public Collection<TestDifficultLevel> updateTestDifficult(List<TestDifficultLevel> difficultLevel) {
        for (TestDifficultLevel l : difficultLevel) {
            TestDifficultLevel level = testDifficultLevelRepository.save(l);
            levelsMap.put(level.getTestDifficultId(), level);
        }
        return levelsMap.values();
    }

    @Override
    public Collection<TestDifficultLevel> getCurrentLevels() {
        return levelsMap.values().stream()
                .sorted(Comparator.comparing(TestDifficultLevel::getTestDifficultId))
                .collect(Collectors.toList());
    }

    private List<TestDifficultLevel> getDefaultLevels() {
        TestDifficultLevel easy = TestDifficultLevel.builder()
                .testDifficultId(DifficultLevelEnum.EASY)
                .oneAnswerCount(5)
                .oneAnswerThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .oneAnswerByDefinitionCount(3)
                .oneAnswerByDefinitionThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .severalAnswerCount(5)
                .severalAnswerThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .matchCount(4)
                .matchThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .demoCodeCount(0)
                .demoCodeThesisTypes(Lists.newArrayList(DEMO_CODE.getValue(), ESSENCE.getValue()))
                .unorderedListCount(0)
                .unorderedListThesisTypes(Lists.newArrayList(LIST_ITEM.getValue()))
                .build();

        TestDifficultLevel medium = TestDifficultLevel.builder()
                .testDifficultId(DifficultLevelEnum.MEDIUM)
                .oneAnswerCount(2)
                .oneAnswerThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .oneAnswerByDefinitionCount(2)
                .oneAnswerByDefinitionThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .severalAnswerCount(0)
                .severalAnswerThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .matchCount(2)
                .matchThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .demoCodeCount(2)
                .demoCodeThesisTypes(Lists.newArrayList(DEMO_CODE.getValue(), ESSENCE.getValue()))
                .unorderedListCount(0)
                .unorderedListThesisTypes(Lists.newArrayList(LIST_ITEM.getValue()))
                .build();

        TestDifficultLevel hard = TestDifficultLevel.builder()
                .testDifficultId(DifficultLevelEnum.HARD)
                .oneAnswerCount(0)
                .oneAnswerThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .oneAnswerByDefinitionCount(0)
                .oneAnswerByDefinitionThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .severalAnswerCount(0)
                .severalAnswerThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .matchCount(3)
                .matchThesisTypes(Lists.newArrayList(ESSENCE.getValue()))
                .demoCodeCount(0)
                .demoCodeThesisTypes(Lists.newArrayList(DEMO_CODE.getValue(), ESSENCE.getValue()))
                .unorderedListCount(0)
                .unorderedListThesisTypes(Lists.newArrayList(LIST_ITEM.getValue()))
                .build();

        return Lists.newArrayList(easy, medium, hard);
    }
}
