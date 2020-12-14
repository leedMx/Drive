package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.UserModel;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserMapper userMapper;
    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return userMapper.getUser(username) == null;
    }

    public int createUser(UserModel user){
        user.setSalt(hashService.createSalt());
        user.setPassword(hashPassword(user));
        int insert = userMapper.insert(user);
        return insert;
    }

    public UserModel getUser(String username){
        return userMapper.getUser(username);
    }

    private String hashPassword(UserModel user) {
        return hashService.getHashedValue(user.getPassword(), user.getSalt());
    }

    public Integer getId(String username) {
        return userMapper.getUser(username).getUserid();
    }
}
