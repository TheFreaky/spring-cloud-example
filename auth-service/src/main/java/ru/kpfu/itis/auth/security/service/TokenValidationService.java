package ru.kpfu.itis.auth.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.auth.config.properties.JwtProperties;

@Service
public class TokenValidationService {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean isTokenValid(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        //ToDo check username in black list
        //FIXME temp
        if (username.equals("admin")) {
            throw new UsernameNotFoundException("User is blocked");
        } else {
            return true;
        }
    }
}
