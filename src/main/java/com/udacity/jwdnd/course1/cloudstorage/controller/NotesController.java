package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class NotesController {
    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("/note")
    public String addNote(NoteModel noteModel, Authentication authentication,
                          Model model){
        String user = authentication.getName();
        if (notesService.insertOrUpdate(noteModel, user) < 1)
             model.addAttribute("error",
                     "Error inserting or updating note");
        return "result";
    }

    @PostMapping("/deleteNote")
    public String deleteNote(@RequestParam("noteId")Integer id,
                             Authentication authentication,
                             Model model){
        String user = authentication.getName();
        if (notesService.deleteNote(id,user) < 1)
            model.addAttribute("error",
                    "Note doesn't exist, or doesn't belong to you.");
        return "result";
    }
}
