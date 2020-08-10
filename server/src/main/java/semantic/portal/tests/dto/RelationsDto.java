package semantic.portal.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RelationsDto {
    private int id;
    @JsonProperty("concept_id")
    private int conceptId;
    private String view;
    //todo type
    private Object thesis;
    private String clazz;
    private int rating;
    //todo type
    private Object count;
    //todo type
    private Object parsed;
    //todo mb boolean
    private int hasConcept;
    @JsonProperty("to_concept_id")
    private int toConceptId;
    @JsonProperty("to_thesis_id")
    private int toThesisId;
    @JsonProperty("to_thesis_caption")
    private String toThesisCaption;
}
