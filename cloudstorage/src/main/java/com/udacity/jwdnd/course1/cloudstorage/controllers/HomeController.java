package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String getHomepage(Model model) {
        return "home";
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @RequestMapping("/signup")
    public String getSignupPage(Model model) {

        Map<String, Object> data = new HashMap<>();

        data.put("toSignUp", true);
        data.put("signupSuccessfully", false);

        model.addAllAttributes(data);

        return "signup";
    }
}
