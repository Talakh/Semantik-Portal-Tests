package semantic.portal.tests.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Column(length = 4096)
    @Id
    @GeneratedValue()
    private UUID id;

    @Column(length = 4096)
    private String answer;

    @Column(length = 4096)
    private boolean correct;

    @Column(length = 4096)
    private UUID matchId;


    public static Answer createAnswer(String answer, Boolean isCorrect) {
        return Answer.builder()
                .answer(answer)
                .correct(isCorrect)
                .build();
    }
}
