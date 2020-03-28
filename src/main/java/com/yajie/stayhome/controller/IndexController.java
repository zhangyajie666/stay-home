package com.yajie.stayhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Lenovo
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String defaultRequest() {
        return "index";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }
}
