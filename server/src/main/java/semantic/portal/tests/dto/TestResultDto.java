package semantic.portal.tests.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestResultDto {
    private int questionsCount;
    private int correctAnswersCount;
    private boolean isPassed;
    private int mark;
    private List<TopicToRepeat> topicsToRepeat;


    @Data
    private static class TopicToRepeat {
        private String conceptId;
        private String name;
        private String topicName;
    }
}
