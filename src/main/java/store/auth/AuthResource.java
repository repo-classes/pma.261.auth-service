package store.auth;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import store.account.AccountOut;

@RestController
public class AuthResource implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<Void> login(LoginIn in) {
        final TokenOut out = authService.login(in.email(), in.password());
        return ResponseEntity
            .ok()
            .header(
                HttpHeaders.SET_COOKIE,
                buildTokenCookie(out.token(), authService.getDuration()).toString()
            )
            .build()
        ;
    }

    @Override
    public ResponseEntity<Void> register(RegisterIn in) {
        authService.register(in);
        return ResponseEntity.created(null).build();
    }

    @Override
    public ResponseEntity<AccountOut> whoIAm() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'whoIAm'");
    }

    @Override
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }

    private ResponseCookie buildTokenCookie(String content, Long duration) {
        return ResponseCookie.from(AuthController.AUTH_COOKIE_TOKEN, content)
            .httpOnly(authService.getHttpOnly())
            .sameSite("None")
            .secure(true)
            .path("/")
            .maxAge(Duration.ofMillis(duration))
            .build();
    }

}
