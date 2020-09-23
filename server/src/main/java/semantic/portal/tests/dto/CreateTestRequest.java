package semantic.portal.tests.dto;

import lombok.Data;
import semantic.portal.tests.enums.DifficultLevelEnum;

@Data
public class CreateTestRequest {
    private String branch;
    private DifficultLevelEnum difficultLevel;
}
