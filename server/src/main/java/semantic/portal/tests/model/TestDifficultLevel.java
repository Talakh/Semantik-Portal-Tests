package semantic.portal.tests.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import semantic.portal.tests.enums.DifficultLevelEnum;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "test_difficult_level")
@NoArgsConstructor
@AllArgsConstructor
public class TestDifficultLevel {
    @Id
    private DifficultLevelEnum testDifficultId;

    @Column
    private int oneAnswerCount;

    @Column
    @ElementCollection
    private List<String> oneAnswerThesisTypes;

    @Column
    private int oneAnswerByDefinitionCount;

    @Column
    @ElementCollection
    private List<String> oneAnswerByDefinitionThesisTypes;

    @Column
    private int severalAnswerCount;

    @Column
    @ElementCollection
    private List<String> severalAnswerThesisTypes;

    @Column
    private int matchCount;

    @Column
    @ElementCollection
    private List<String> matchThesisTypes;

    @Column
    private int demoCodeCount;

    @Column
    @ElementCollection
    private List<String> demoCodeThesisTypes;

    @Column
    private int unorderedListCount;

    @Column
    @ElementCollection
    private List<String> unorderedListThesisTypes;
}
