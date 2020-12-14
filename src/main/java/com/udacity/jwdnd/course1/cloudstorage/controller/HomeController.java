package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    private FilesService files;
    private NotesService notes;
    private CredentialsService credentials;
    private EncryptionService encryptionService;

    public HomeController(FilesService files, NotesService notes,
                          CredentialsService credentials,
                          EncryptionService encryptionService) {
        this.files = files;
        this.notes = notes;
        this.credentials = credentials;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/home")
    String get(@ModelAttribute("emptyNote")NoteModel note,
            Model model, Authentication auth){
        String user = auth.getName();
        model.addAttribute("files", files.getFiles(user));
        model.addAttribute("notes", notes.getNotes(user));
        model.addAttribute("credentials", credentials.getCredentials(user));
        model.addAttribute("encrypt",encryptionService);
        return "home";
    }
}
