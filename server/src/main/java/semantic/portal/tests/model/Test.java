package semantic.portal.tests.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import semantic.portal.tests.enums.TestTypeEnum;

import javax.persistence.*;
import java.util.*;

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

    @Lob
    private String codeInQuestion;

    @ElementCollection
    private Set<String> domainUrl;

    @ElementCollection
    private Set<String> domainName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "test")
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
    @MapKeyColumn(name="question2Answer")
    private Map<UUID, UUID> userAnswers;

    @ElementCollection
    private List<UUID> userAnswerIds;

    @Column
    private Boolean answerResult;

    @Column(name = "attempt_id")
    private UUID attemptId;
}
