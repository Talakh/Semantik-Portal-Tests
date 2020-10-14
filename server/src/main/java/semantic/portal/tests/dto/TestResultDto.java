package semantic.portal.tests.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
public class TestResultDto {
    private long questionsCount;
    private long correctAnswersCount;
    private boolean passed;
    private double percent;
    private String grade;
    private String branch;
    private List<TopicToRepeat> topicsToRepeat;


    @Builder
    @Data
    @EqualsAndHashCode
    public static class TopicToRepeat {
        private Set<String> domainUrl;
        private Set<String> domainName;
    }
}
