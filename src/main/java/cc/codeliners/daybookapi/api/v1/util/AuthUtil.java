package cc.codeliners.daybookapi.api.v1.util;

import org.springframework.stereotype.Component;

@Component
public class AuthUtil {
    private final JwtTokenProvider jwtTokenProvider;

    public AuthUtil(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
}
