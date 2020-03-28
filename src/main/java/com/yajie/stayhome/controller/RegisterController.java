package com.yajie.stayhome.controller;

import com.yajie.stayhome.model.User;
import com.yajie.stayhome.service.RoleService;
import com.yajie.stayhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

/**
 * @author Lenovo
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("register")
    public String doRegister(@RequestParam("username") String username,
                             @RequestParam("phone") String phone,
                             @RequestParam("email") String email,
                             @RequestParam("passwd") String passwd,
                             @RequestParam("repass") String repass,
                             @RequestParam("role") String role,
                             Model model) {
        if (userService.getUserByUsername(username) != null) {
            model.addAttribute("error", "用户已存在");
            return "register";
        }
        User user = new User(username, phone, email, passwd,
                roleService.getRoleByName(role).getId(), new Timestamp(System.currentTimeMillis()));
        userService.addUser(user);
        return "login";
    }
}
