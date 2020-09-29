package semantic.portal.tests.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@Data
public class AnswerCheckDto {
    private boolean isTrue;
    private UUID correctId;
    private List<UUID> correctIds;
    private Map<UUID, Boolean> correctAnswer;

}
