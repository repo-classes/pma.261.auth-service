package store.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.account.AccountController;
import store.account.AccountIn;
import store.account.AccountOut;

@Service
public class AuthService {

    @Autowired
    private AccountController accountController;

    public void register(RegisterIn in) {

        // record the input into account
        accountController.create(AccountIn.builder()
            .name(in.name())
            .email(in.email())
            .password(in.password())
            .build()
        );

    }

    public TokenOut login(String email, String password) {
        // search account
        final AccountOut account = accountController.findByEmailAndPassword(
            AccountIn.builder()
            .email(email)
            .password(password)
            .build()
        ).getBody();

        // create token
        return null;
    }

    
}
