package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String redirectToHome(Model model){
        model.addAttribute("error",
                "This is not a valid URL");
        return "result";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
