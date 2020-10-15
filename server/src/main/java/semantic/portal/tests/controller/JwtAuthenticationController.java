package semantic.portal.tests.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import semantic.portal.tests.config.JwtTokenUtil;
import semantic.portal.tests.model.JwtRequest;
import semantic.portal.tests.model.JwtResponse;
import semantic.portal.tests.security.SemanticLoginDto;
import semantic.portal.tests.services.api.LoginApiService;
import semantic.portal.tests.services.security.UserService;

import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final LoginApiService loginApiService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService jwtInMemoryUserDetailsService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, LoginApiService loginApiService, UserService userService, JwtTokenUtil jwtTokenUtil, @Qualifier("jwtUserDetailsService") UserDetailsService jwtInMemoryUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.loginApiService = loginApiService;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        SemanticLoginDto semanticLoginDto = loginApiService.getSemanticLogin(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        if (semanticLoginDto.getLogin()) {
            userService.checkUser(authenticationRequest, semanticLoginDto);
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = jwtInMemoryUserDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
