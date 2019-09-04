package ru.kpfu.itis.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.auth.security.service.TokenValidationService;

@RestController
@RequestMapping("/token/validate")
@RequiredArgsConstructor
public class TokenValidationController {

    private final TokenValidationService tokenValidationService;

    @GetMapping
    public ResponseEntity validateToken(@RequestParam("token") String token) {
        if (tokenValidationService.isTokenValid(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
