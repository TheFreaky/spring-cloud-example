package ru.kpfu.itis.web.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.web.dto.UserDto;
import ru.kpfu.itis.web.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
        response.addCookie(new Cookie("auth", Base64.encodeBase64String(authHeaderValue.getBytes())));
        return "redirect:/ui/users";
    }

    @GetMapping("/login")
    public String renderLoginPage() {
        return "login";
    }

    @GetMapping("/users")
    public String renderAllUsersPage(@CookieValue(value = "auth", required = false) String authValue, Model model) {
        List<UserDto> users = userService.getAll(new String(Base64.decodeBase64(authValue.getBytes())));
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/error")
    public String renderErrorPage() {
        return "error";
    }
}
