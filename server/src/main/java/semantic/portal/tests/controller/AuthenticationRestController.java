package semantic.portal.tests.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semantic.portal.tests.dto.UserDto;
import semantic.portal.tests.model.User;
import semantic.portal.tests.security.jwt.JwtTokenProvider;
import semantic.portal.tests.services.api.impl.LoginApiServiceImpl;
import semantic.portal.tests.services.security.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final LoginApiServiceImpl loginApiService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, LoginApiServiceImpl loginApiService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.loginApiService = loginApiService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody UserDto userDto) {

        if (loginApiService.getSemanticLogin(userDto.getUsername(), userDto.getPassword()).getLogin()) {
            if (userService.findByUsername(userDto.getUsername()) == null) {
                userService.register(userDto.toUser());
            }
            try {
                String username = userDto.getUsername();

                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userDto.getPassword()));
                User user = userService.findByUsername(username);

                if (user == null) {
                    throw new UsernameNotFoundException("User with username: " + username + " not found");
                }
                String token = jwtTokenProvider.createToken(username, user.getRoles());
                Map<Object, Object> response = new HashMap<>();
                response.put("username", username);
                response.put("token", token);
                return ResponseEntity.ok(response);
            } catch (AuthenticationException e) {
                throw new BadCredentialsException("Invalid username or password");
            }
        } else
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
