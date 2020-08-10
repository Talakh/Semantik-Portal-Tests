package semantic.portal.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DidacticDto {
    private int id;
    @JsonProperty("from_id")
    private int fromId;
    @JsonProperty("to_id")
    private int toId;
    @JsonProperty("CF")
    private double cf;
    //todo mb int?
    @JsonProperty("CF_alt")
    private double cfAlt;
    //todo mb boolean?
    private int called;
}
