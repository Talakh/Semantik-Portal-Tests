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
    private UUID id;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<Question> question;

    @Column
    private String branch;

    @Column
    private TestTypeEnum type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    private List<Answer> answers;
}
