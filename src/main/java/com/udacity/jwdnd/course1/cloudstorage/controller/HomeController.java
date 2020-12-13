package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
    private FilesService filesService;

    public HomeController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/home")
    String get(Model model,Authentication auth){
        model.addAttribute("files",filesService.filesOf(auth.getName()));
        return "home";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("fileUpload") MultipartFile fileUpload,
                         Model model, Authentication authentication){
        if (fileUpload.isEmpty())
            model.addAttribute("error","You must specify a file.");
        else
            try {
                filesService.upload(fileUpload, authentication.getName());
            } catch (Exception ex){
                model.addAttribute("error",ex.getMessage());
            }
        return "result";
    }
}
