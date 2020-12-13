package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

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

    public int createUser(User user){
        user.setSalt(createSalt());
        user.setPassword(hashPassword(user));
        int insert = userMapper.insert(user);
        System.out.println(user);
        return insert;
    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }

    private String hashPassword(User user) {
        return hashService.getHashedValue(user.getPassword(), user.getSalt());
    }

    private String createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}
