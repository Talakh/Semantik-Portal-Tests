package semantic.portal.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BranchDto {
    private List<ConceptDto> concepts;
    @JsonProperty("relations")
    private List<ThesisDTO> theses;
    private List<DidacticDto> didactic;
}
