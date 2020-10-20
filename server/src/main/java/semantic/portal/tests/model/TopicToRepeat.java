package semantic.portal.tests.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode
@Entity
@Table(name = "topic_to_repeat")
@AllArgsConstructor
@NoArgsConstructor
public class TopicToRepeat {

    @Id
    @GeneratedValue
    @Column(length = 4096)
    private UUID testResultId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DomainUrl> domainUrl;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<DomainName> domainName;

}
