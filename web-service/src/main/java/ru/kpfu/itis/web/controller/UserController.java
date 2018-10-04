package ru.kpfu.itis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.web.dto.UserDto;
import ru.kpfu.itis.web.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String renderRegisterUserPage() {
        return "main";
    }

    @PostMapping("/register")
    public String registerUser(UserDto userDto) {
        userService.registerUser(userDto);
        return "success";
    }

    @GetMapping("/users")
    public String renderAllUsersPage(Model model) {
        List<UserDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }
}
