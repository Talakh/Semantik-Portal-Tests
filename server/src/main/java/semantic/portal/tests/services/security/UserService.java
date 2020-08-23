package semantic.portal.tests.services.security;

import semantic.portal.tests.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(UUID id);

    void delete(UUID id);

}
