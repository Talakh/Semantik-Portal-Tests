package semantic.portal.tests.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "username", length = 4096)
    private String username;

    @Column(name = "first_name", length = 4096)
    private String firstName;

    @Column(name = "last_name", length = 4096)
    private String lastName;

    @Column(name = "email", length = 4096)
    private String email;

    @Column(name = "password", length = 4096)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}