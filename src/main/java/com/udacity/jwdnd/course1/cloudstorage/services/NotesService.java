package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.NoteModel;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private UserService userService;
    private NoteMapper noteMapper;

    public NotesService(UserService userService, NoteMapper noteMapper) {
        this.userService = userService;
        this.noteMapper = noteMapper;
    }

    public List<NoteModel> getNotes(String username){
        return noteMapper.getNotes(userService.getId(username));
    }

    public int insertOrUpdate(NoteModel note, String username){
        note.setUserId(userService.getId(username));
        if (note.getNoteId() == null)
            return noteMapper.insert(note);
        return noteMapper.update(note);
    }

    public int deleteNote(Integer id, String username) {
        return noteMapper.delete(id,userService.getId(username));
    }
}
