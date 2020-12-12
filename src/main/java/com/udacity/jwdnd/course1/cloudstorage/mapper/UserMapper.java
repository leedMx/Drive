package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Insert("INSERT INTO users(username,salt,password,firstname,lastname) " +
            "VALUES(#{username},#{salt},#{password},#{firstname},#{lastname})")
    @Options(useGeneratedKeys = true,keyProperty = "userid")
    int insert(User user);

    @Select("SELECT * FROM users WHERE username=#{username}")
    User getUser(String username);
}
