package semantic.portal.tests.dto.security;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
    private String email;
}