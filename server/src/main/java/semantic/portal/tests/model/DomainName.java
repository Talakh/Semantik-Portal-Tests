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
@Table(name = "domain_name")
@NoArgsConstructor
@AllArgsConstructor
public class DomainName {

    @Id
    @GeneratedValue()
    @Column(length = 4096)
    private UUID id;

    @Column
    private String domainName;

}
