package semantic.portal.tests.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TestResultDto {
    private long questionsCount;
    private long correctAnswersCount;
    private boolean passed;
    private double percent;
    private String grade;
    private String branch;
    private UUID userId;
    private List<TopicToRepeatDto> topicsToRepeat;

}
