package semantic.portal.tests.security.jwt;

import javax.naming.AuthenticationException;

public class JwtAuthenticationException  extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
