package semantic.portal.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerCheckDto {
    private boolean isCorrect;
    private UUID correctId;
    private List<UUID> correctIds;
    private Map<UUID, UUID> correctAnswerMap;

}
