package semantic.portal.tests.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Builder
@Data
@EqualsAndHashCode
public class TopicToRepeatDto {

    private Set<String> domainUrl;
    private Set<String> domainName;

}
