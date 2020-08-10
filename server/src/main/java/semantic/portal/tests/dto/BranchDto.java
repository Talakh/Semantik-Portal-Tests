package semantic.portal.tests.dto;

import lombok.Data;

import java.util.List;

@Data
public class BranchDto {
    private List<ConceptDto> concepts;
    private List<RelationsDto> relations;
    private List<DidacticDto> didactic;
}
