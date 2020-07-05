package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserVO;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    private AuthorizationService authorizationService;

    public HomeController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/home")
    public String getHomepage(Model model) {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage(
            @ModelAttribute("userVo") UserVO userVo,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("toLogin", true);
        data.put("loginSuccessfully", false);

        model.addAllAttributes(data);

        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(
            @ModelAttribute("userVo") UserVO userVo,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("toSignUp", true);
        data.put("signupSuccessfully", false);

        model.addAllAttributes(data);

        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(
            @ModelAttribute("userVo") UserVO userVo,
            Model model
    ) {

        this.logger.error("Received user info from Signup Form: " + userVo.toString());

        if (!this.authorizationService.signupUser(userVo)) {

            Map<String, Object> data = new HashMap<>();

            data.put("toSignUp", false);
            data.put("signupSuccessfully", true);

            model.mergeAttributes(data);

            return "signup";
        } else {
            return this.loginPage(userVo, model);
        }
    }
}
