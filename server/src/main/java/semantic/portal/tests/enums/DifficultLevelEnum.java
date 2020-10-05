package semantic.portal.tests.enums;

import lombok.Getter;

@Getter
public enum DifficultLevelEnum {
    LOW(5,3, 2, 0,0),
    MEDIUM(2,2, 2, 2,2),
    HIGH(2,2, 2, 3,3);
    private int oneAnswerCount;
    private int oneAnswerByDefinitionCount;
    private int demoCodeCount;
    private int severalAnswerCount;
    private int matchCount;

    DifficultLevelEnum(int oneAnswerCount,
                       int oneAnswerByDefinitionCount,
                       int demoCodeCount,
                       int severalAnswerCount,
                       int matchCount) {
        this.oneAnswerCount = oneAnswerCount;
        this.oneAnswerByDefinitionCount = oneAnswerByDefinitionCount;
        this.demoCodeCount = demoCodeCount;
        this.severalAnswerCount = severalAnswerCount;
        this.matchCount = matchCount;
    }
}
