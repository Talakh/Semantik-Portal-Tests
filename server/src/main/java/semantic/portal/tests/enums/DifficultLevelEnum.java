package semantic.portal.tests.enums;

import lombok.Getter;

@Getter
public enum DifficultLevelEnum {
    EASY(5,3, 0, 0,0, 0),
    MEDIUM(2,2, 0, 2,2, 0),
    HARD(0,0, 0, 0,3, 0);
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
