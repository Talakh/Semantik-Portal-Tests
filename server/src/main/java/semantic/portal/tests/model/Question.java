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
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue()
    @Column(length = 4096)
    private UUID id;

    @Column(length = 8000)
    String question;

    @Column(length = 4096)
    private UUID answerId;
}
