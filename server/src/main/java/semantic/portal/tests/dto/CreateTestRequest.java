package semantic.portal.tests.dto;

import lombok.Data;
import semantic.portal.tests.enums.DifficultLevelEnum;

@Data
public class CreateTestRequest {
    private BranchChildDto branch;
    private DifficultLevelEnum difficultLevel;
}
