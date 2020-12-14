package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.FileModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FileMapper {
    @Insert("INSERT INTO files(filename,contentType,fileSize,userId,fileData)" +
            "VALUES(#{fileName},#{contentType},#{fileSize},#{userId},#{fileData})")
    int insert(FileModel file);

    @Select("SELECT * FROM files WHERE userId=#{userId}")
    List<FileModel> filesForId(Integer userId);

    @Select("SELECT * FROM files WHERE userId=#{userId} AND fileId=#{fileId}")
    FileModel getFileIdForUser(Integer fileId, Integer userId);

    @Delete("DELETE FROM files WHERE userId=#{userId} AND fileId=#{fileId}")
    int deleteFileIdForUser(Integer fileId, Integer userId);

    @Select("SELECT * FROM files WHERE userId=#{userId} AND fileName=#{fileName}")
    List<FileModel> getByFilename(Integer userId, String fileName);
}