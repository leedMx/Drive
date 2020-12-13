package com.udacity.jwdnd.course1.cloudstorage.Model;

import lombok.Data;

@Data
public class NoteModel {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;
}
