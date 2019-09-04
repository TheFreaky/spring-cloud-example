package ru.kpfu.itis.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kpfu.itis.auth.config.properties.JwtProperties;
import ru.kpfu.itis.auth.repository.UserTokenRepository;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtProperties jwtProperties;
    private final UserTokenRepository userTokenRepository;
    // We use auth manager to validate the user credentials
    private final AuthenticationManager authManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtProperties jwtProperties, UserTokenRepository userTokenRepository) {
        this.authManager = authManager;
        this.jwtProperties = jwtProperties;
        this.userTokenRepository = userTokenRepository;

        // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
        // In our case, we use "/auth". So, we need to override the defaults.
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtProperties.getUrl(), HttpMethod.POST.name()));
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // 1. Get credentials from request
        UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);

        // 2. Create auth object (contains credentials) which will be used by auth manager
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                creds.getUsername(), creds.getPassword(), Collections.emptyList());

        // 3. Authentication manager authenticate the user, and use UserDetialsServiceImpl::loadUserByUsername() method to load the user.
        return authManager.authenticate(authToken);

    }

    // Upon successful authentication, generate a token.
    // The 'auth' passed to successfulAuthentication() is the current authenticated user.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication auth) {

        long now = System.currentTimeMillis();
        Date expiration = new Date(now + jwtProperties.getExpiration() * 1000);
        String token = Jwts.builder()
                .setSubject(auth.getName())
                // Convert to list of strings.
                // This is important because it affects the way we get them back in the Gateway.
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(expiration)  // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret().getBytes())
                .compact();

        userTokenRepository.save(auth.getName(), expiration.getTime());

        // Add token to header
        response.addHeader(jwtProperties.getHeader(), jwtProperties.getPrefix() + token);
    }

    @Data
    private static class UserCredentials {
        private String username;
        private String password;
    }
}
