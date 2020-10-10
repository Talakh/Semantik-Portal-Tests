package semantic.portal.tests.enums;

import lombok.Getter;

@Getter
public enum DifficultLevelEnum {
    LOW(5,3, 2, 0,0, 2),
    MEDIUM(2,2, 2, 2,2, 2),
    HIGH(2,2, 2, 3,3, 2);
    private int oneAnswerCount;
    private int oneAnswerByDefinitionCount;
    private int demoCodeCount;
    private int severalAnswerCount;
    private int matchCount;
    private int unorderedListCount;

    DifficultLevelEnum(int oneAnswerCount,
                       int oneAnswerByDefinitionCount,
                       int demoCodeCount,
                       int severalAnswerCount,
                       int matchCount,
                       int unorderedListCount) {
        this.oneAnswerCount = oneAnswerCount;
        this.oneAnswerByDefinitionCount = oneAnswerByDefinitionCount;
        this.demoCodeCount = demoCodeCount;
        this.severalAnswerCount = severalAnswerCount;
        this.matchCount = matchCount;
        this.unorderedListCount = unorderedListCount;
    }
}
