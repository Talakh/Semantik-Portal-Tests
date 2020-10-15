package semantic.portal.tests.services.security.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import semantic.portal.tests.model.JwtRequest;
import semantic.portal.tests.model.Role;
import semantic.portal.tests.model.Status;
import semantic.portal.tests.model.User;
import semantic.portal.tests.repository.RoleRepository;
import semantic.portal.tests.repository.UserRepository;
import semantic.portal.tests.security.SemanticLoginDto;
import semantic.portal.tests.services.security.UserService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user, Role role) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singletonList(role));
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("user: {} registered ", registeredUser);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void checkUser(JwtRequest authenticationRequest, SemanticLoginDto semanticLoginDto) {
        if (findByUsername(authenticationRequest.getUsername()) == null) {
            User user = new User();
            user.setUsername(authenticationRequest.getUsername());
            user.setPassword(authenticationRequest.getPassword());
            if (roleRepository.findByName(semanticLoginDto.getRole()) == null) {
                roleRepository.save(Role.builder().name(semanticLoginDto.getRole()).build());
            }
            Role roleUser = roleRepository.findByName(semanticLoginDto.getRole());
            register(user, roleUser);
        }
    }

    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
