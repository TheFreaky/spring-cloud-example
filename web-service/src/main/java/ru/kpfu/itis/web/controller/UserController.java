package ru.kpfu.itis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.web.dto.UserDto;
import ru.kpfu.itis.web.service.UserService;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/login")
    public String authUser(HttpServletResponse response, UserDto userDto) {
        String authHeaderValue = userService.loginUser(userDto);
        response.setHeader("Authorization", authHeaderValue);
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String renderLoginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String renderAllUsersPage(@RequestHeader(value="Authorization", required = false) String authHeaderValue, Model model) {
        List<UserDto> users = userService.getAll(authHeaderValue);
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/error")
    public String renderErrorPage() {
        return "error";
    }
}
