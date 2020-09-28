package semantic.portal.tests.enums;

import lombok.Getter;

@Getter
public enum DifficultLevelEnum {
    LOW(7,3,0,0),
    MEDIUM(4,2,2,2),
    HIGH(2,2,3,3);
    private int oneCorrectAnswerCount;
    private int oneCorrectAnswerByDefinitionCount;
    private int severalCorrectAnswerCount;
    private int matchCount;

    DifficultLevelEnum(int oneCorrectAnswerCount, int oneCorrectAnswerByDefinitionCount,
                       int severalCorrectAnswerCount, int matchCount) {
        this.oneCorrectAnswerCount = oneCorrectAnswerCount;
        this.oneCorrectAnswerByDefinitionCount = oneCorrectAnswerByDefinitionCount;
        this.severalCorrectAnswerCount = severalCorrectAnswerCount;
        this.matchCount = matchCount;
    }
}
