package ru.kpfu.itis.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.auth.security.service.TokenValidationService;

@RestController
@RequestMapping("/token/validate")
public class TokenValidationController {

    @Autowired
    private TokenValidationService tokenValidationService;

    @GetMapping
    public ResponseEntity validateToken(@RequestParam("token") String token) {
        if (tokenValidationService.isTokenValid(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
