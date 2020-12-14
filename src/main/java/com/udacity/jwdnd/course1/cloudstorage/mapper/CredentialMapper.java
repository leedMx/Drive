package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CredentialMapper {
    @Insert("INSERT INTO credentials(url,username,key,password,userid)" +
            "VALUES(#{url},#{username},#{key},#{password},#{userId})")
    int insert(CredentialModel credential);

    @Select("SELECT * FROM credentials WHERE userId=#{userId}")
    List<CredentialModel> getCredentials(Integer userId);

    @Update("UPDATE credentials SET url=#{url},username=#{username}," +
            "key=#{key}, password=#{password}" +
            "WHERE credentialId=#{credentialId} AND userId=#{userId}")
    int update(CredentialModel credential);

    @Delete("DELETE FROM credentials WHERE " +
            "credentialId=#{credentialId} AND userId=#{userId}")
    int delete(Integer credentialId, Integer userId);
}
