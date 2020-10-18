package semantic.portal.tests.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "domain_url")
@NoArgsConstructor
@AllArgsConstructor
public class DomainUrl {

    @Id
    @GeneratedValue()
    @Column(length = 4096)
    private UUID id;

    @Column
    private String domainUrl;

}
