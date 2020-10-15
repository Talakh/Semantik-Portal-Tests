package semantic.portal.tests.services.security;

import semantic.portal.tests.model.JwtRequest;
import semantic.portal.tests.model.Role;
import semantic.portal.tests.model.User;
import semantic.portal.tests.security.SemanticLoginDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void register(User user, Role role);

    List<User> getAll();

    User findByUsername(String username);

    void checkUser(JwtRequest authenticationRequest, SemanticLoginDto semanticLoginDto);

    User findByEmail(String email);

    User findById(UUID id);

    void delete(UUID id);

}
