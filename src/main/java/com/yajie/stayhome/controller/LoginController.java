package com.yajie.stayhome.controller;

import com.yajie.stayhome.model.User;
import com.yajie.stayhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lenovo
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;



    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("pass") String password,
                          HttpServletRequest request,
                          Model model) {
        User user = userService.getUserByUsernameAndPassword(username, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
            if (user.getRole() == 1) {
                return "redirect:index";
            } else {
                return "redirect:owner-index";
            }
        }
        model.addAttribute("error", "用户不存在");
        return "login";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "index";
    }

}
