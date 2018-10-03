package ru.kpfu.itis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.kpfu.itis.web.dto.UserDto;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    private String renderRegisterUserPage(UserDto userDto) {
        messageService.registerUser(userDto);
        return "success";
    }

    private String renderAllUsersPage(Model model) {
        List<UserDto> users = userService.getAll();
        model.addAttribute("users", users);
        return "users";
    }
}
