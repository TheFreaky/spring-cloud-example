package ru.kpfu.itis.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.user.model.User;
import ru.kpfu.itis.user.service.AdminService;
import ru.kpfu.itis.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AdminService adminService;


    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping("/admin/{username}/ban")
    public ResponseEntity<Void> banUser(@PathVariable("username") String username) {
        adminService.banUser(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/{username}/unban")
    public ResponseEntity<Void> unbanUser(@PathVariable("username") String username) {
        adminService.unbanUser(username);
        return ResponseEntity.ok().build();
    }
}
