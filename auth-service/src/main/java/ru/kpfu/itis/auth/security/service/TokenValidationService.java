package ru.kpfu.itis.auth.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.auth.config.properties.JwtProperties;
import ru.kpfu.itis.auth.repository.UserBlacklistRepository;

@Service
@RequiredArgsConstructor
public class TokenValidationService {

    private final JwtProperties jwtProperties;
    private final UserBlacklistRepository userBlacklistRepository;

    public boolean isTokenValid(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        if (userBlacklistRepository.isPresent(username)) {
            throw new AccessDeniedException("User is blocked");
        }
        return true;
    }
}
