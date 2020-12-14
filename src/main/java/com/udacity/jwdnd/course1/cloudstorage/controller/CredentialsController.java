package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialsController {
    CredentialsService service;

    public CredentialsController(CredentialsService credentialsService) {
        this.service = credentialsService;
    }

    @PostMapping("/credential")
    String insert(CredentialModel credential, Authentication authentication,
                  Model model){
        String user = authentication.getName();
        if (service.insertOrUpdate(credential,user) < 1)
            model.addAttribute("error",
                    "Error saving the credential");
        return "result";
    }
}
