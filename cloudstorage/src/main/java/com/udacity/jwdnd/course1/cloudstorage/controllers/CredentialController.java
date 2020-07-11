package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserCredentialVO;
import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteVO;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private Logger logger = LoggerFactory.getLogger(CredentialController.class);

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential")
    public String credentialSubmit(
            @ModelAttribute("userCredentialVO") UserCredentialVO userCredentialVO,
            Authentication authentication,
            Model model
    ) {

        String username = (String) authentication.getPrincipal();

        Boolean isSuccess = this.credentialService.insertOrUpdateCredential(
                userCredentialVO, username);

        return "redirect:/result?isSuccess=" + isSuccess;
    }

    @GetMapping("/credential")
    public String credentialDeletion(
            @ModelAttribute("userCredentialVO") UserCredentialVO userCredentialVO,
            @RequestParam(required = false, name = "credentialId") Integer credentialId,
            Authentication authentication,
            Model model
    ) {

        this.logger.error("CredentialId: " + credentialId.toString());

        Boolean isSuccess = this.credentialService.deleteCredential(credentialId);

        return "redirect:/result?isSuccess=" + isSuccess;
    }
}
