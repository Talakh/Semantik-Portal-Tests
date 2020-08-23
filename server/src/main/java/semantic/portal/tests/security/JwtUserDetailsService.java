package semantic.portal.tests.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import semantic.portal.tests.model.User;
import semantic.portal.tests.security.jwt.JwtUser;
import semantic.portal.tests.security.jwt.JwtUserFactory;
import semantic.portal.tests.services.security.UserService;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userService.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + userName + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", userName);
        return jwtUser;
    }
}
