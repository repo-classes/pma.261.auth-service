package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import store.account.AccountOut;

@RestController
public class AuthResource implements AuthController {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<TokenOut> login(LoginIn in) {
        authService.login(in.email(), in.password());
        throw new UnsupportedOperationException("Unimplemented method 'login'");
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
    
}
