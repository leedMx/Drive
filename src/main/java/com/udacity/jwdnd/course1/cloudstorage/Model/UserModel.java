package com.udacity.jwdnd.course1.cloudstorage.Model;

import lombok.Data;

@Data
public class UserModel {
    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;
}
