package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    String get(){
        return "signup";
    }

    @PostMapping
    String post(@ModelAttribute UserModel user, Model model){
        if (userService.isUsernameAvailable(user.getUsername())) {
            createUser(user, model);
            return "redirect:/login?signup=true";
        }
        else
            model.addAttribute("signupError","Username not available");
        return "signup";
    }

    private void createUser(@ModelAttribute UserModel user, Model model) {
        if (userService.createUser(user) > 0)
            model.addAttribute("signupSuccess", "success");
        else
            model.addAttribute("signupError","error creating user");
    }
}
