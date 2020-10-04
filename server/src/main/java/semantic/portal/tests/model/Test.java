package semantic.portal.tests.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import semantic.portal.tests.enums.TestTypeEnum;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue()
    @Column(length = 4096)
    private UUID id;

    @Lob
    private String question;

    @Column
    private String domainUrl;

    @Column
    private String domainName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private List<Question> matchQuestion;

    @Column(length = 4096)
    private TestTypeEnum type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    @Column(length = 4096)
    private List<Answer> answers;

    @Column
    private UUID userAnswerId;

    @ElementCollection
    private List<UUID> userAnswerIds;

    @Column
    private Boolean answerResult;

    @Column(name = "attempt_id")
    private UUID attemptId;
}
