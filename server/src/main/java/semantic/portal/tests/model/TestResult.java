package semantic.portal.tests.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "test_Result")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestResult {

    @Id
    @GeneratedValue
    @Column(length = 4096)
    private UUID testResultId;

    @Column
    private long questionsCount;

    @Column
    private long correctAnswersCount;

    @Column
    private boolean passed;

    @Column
    private double percent;

    @Column
    private String grade;

    @Column
    private String branch;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TopicToRepeat> topicsToRepeat;

    @Column(name = "user_id")
    private UUID userId;

}
