package semantic.portal.tests.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConceptDto {
    private int id;
    private String concept;
    private String domain;
    private String clazz;
    private int rating;
    //todo type
    private Object forms;
    //todo possible values
    private int searchConceptsCalled;
    //todo type
    private Object parsed;
    //todo type
    private Object definition;
    //todo possible values
    private int getDefinitionCalled;
    //todo is boolean?
    private int isAspect;
    //todo type
    private Object aspectOf;
    @JsonProperty("found_count")
    private int foundCount;

}
