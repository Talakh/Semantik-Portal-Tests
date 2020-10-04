package semantic.portal.tests.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class TestResultDto {
    private long questionsCount;
    private long correctAnswersCount;
    private boolean isPassed;
    private double percent;
    private String branch;
    private List<TopicToRepeat> topicsToRepeat;


    @Builder
    @Data
    @EqualsAndHashCode
    public static class TopicToRepeat {
        private String domainUrl;
        private String domainName;
    }
}
