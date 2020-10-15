package semantic.portal.tests.services.api;

import semantic.portal.tests.security.SemanticLoginDto;

public interface LoginApiService {
    SemanticLoginDto getSemanticLogin(String login, String psw);
}
