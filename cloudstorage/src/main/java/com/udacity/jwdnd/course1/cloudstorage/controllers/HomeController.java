package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteVO;
import com.udacity.jwdnd.course1.cloudstorage.models.UserVO;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String getHomepage(
            @ModelAttribute("userNoteVO") UserNoteVO userNoteVO,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("noteList", new ArrayList<>());

        model.addAllAttributes(data);

        return "home";
    }

    @GetMapping("/logout")
    public String logOut(
            @ModelAttribute("userVo") UserVO userVo,
            Model model
    ) {

        this.logger.error("logout");

        return this.loginPage(userVo, false, true, model);
    }

    @GetMapping("/login")
    public String loginPage(
            @ModelAttribute("userVo") UserVO userVo,
            @RequestParam(required = false, name = "error") Boolean errorValue,
            @RequestParam(required = false, name = "loggedOut") Boolean loggedOut,
            Model model
    ) {

        Boolean hasError = (errorValue == null)? false : errorValue;
        Boolean isLoggedOut = (loggedOut == null)? false : loggedOut;

        Map<String, Object> data = new HashMap<>();

        data.put("toLogin", true);
        data.put("loginSuccessfully", false);
        data.put("hasError", hasError);
        data.put("isLoggedOut", isLoggedOut);

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
        data.put("hasError", false);

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

            data.put("toSignUp", true);
            data.put("signupSuccessfully", false);
            data.put("hasError", true);

            model.mergeAttributes(data);

            return "signup";
        } else {

            Map<String, Object> data = new HashMap<>();

            data.put("toLogin", true);
            data.put("loginSuccessfully", false);
            data.put("hasError", false);
            data.put("isLoggedOut", false);

            model.addAllAttributes(data);

            return "login";
        }
    }

    @GetMapping("/result")
    public String showResult(
            Authentication authentication,
            @RequestParam(required = false, name = "isSuccess") Boolean isSuccess,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("isSuccess", isSuccess);

        model.addAllAttributes(data);

        return "result";
    }
}
