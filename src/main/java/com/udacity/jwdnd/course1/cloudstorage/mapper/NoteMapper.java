package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.NoteModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoteMapper {
    @Insert("INSERT INTO notes(notetitle,notedescription,userid) " +
            "VALUES (#{noteTitle},#{noteDescription},#{userId})")
    int insert(NoteModel note);

    @Update("UPDATE notes SET notetitle=#{noteTitle}," +
            "notedescription=#{noteDescription} " +
            "WHERE noteId=#{noteId} AND userId=#{userId}")
    int update(NoteModel note);

    @Select("SELECT * FROM notes WHERE userId=#{userId}")
    List<NoteModel> getNotes(Integer userId);

    @Delete("DELETE FROM notes WHERE userId=#{userId} AND noteId=#{noteId}")
    int delete(Integer noteId, Integer userId);
}
