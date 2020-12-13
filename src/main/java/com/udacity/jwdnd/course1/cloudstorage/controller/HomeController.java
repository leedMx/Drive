package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    private FilesService filesService;
    private NotesService notesService;

    public HomeController(FilesService filesService, NotesService notesService) {
        this.filesService = filesService;
        this.notesService = notesService;
    }

    @GetMapping("/home")
    String get(@ModelAttribute("currentNote") NoteModel currentNote,
               Model model, Authentication auth){
        model.addAttribute("files",filesService.getFiles(auth.getName()));
        model.addAttribute("notes",notesService.getNotes(auth.getName()));
        return "home";
    }
}
