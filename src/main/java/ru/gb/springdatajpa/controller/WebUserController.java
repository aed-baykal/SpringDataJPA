package ru.gb.springdatajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdatajpa.service.UserService;

@Controller
@RequestMapping("/admin")
public class WebUserController {

    private final UserService userService;

    public WebUserController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/app/admin/all
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("web_users", userService.findAll());
        return "user_list";
    }

}
