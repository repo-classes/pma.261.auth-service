package store.auth;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import store.account.AccountOut;

@Service
public class JwtService {

    @Value("${store.jwt.secretKey}")
    private String secretKey;

    public String generate(AccountOut account, long duration) {

        Date now = new Date();

        String jwt = Jwts.builder()
            .header()
            .and()
            .id(account.id())
            .issuer("Insper::PMA")
            .claims(Map.of(
                "email", account.email()
            ))
            .signWith(getKey())
            .subject(account.name())
            .notBefore(now)
            .expiration(new Date(now.getTime() + duration)) // in miliseconds
            .compact();
        return jwt;
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }
    
}
