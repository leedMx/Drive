package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.FileModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FileMapper {
    @Insert("INSERT INTO files(filename,contentType,fileSize,userId,fileData)" +
            "VALUES(#{fileName},#{contentType},#{fileSize},#{userId},#{data})")
    int insert(FileModel file);

    @Select("SELECT * FROM files WHERE userId=#{userId}")
    List<FileModel> filesForId(Integer userId);
}
