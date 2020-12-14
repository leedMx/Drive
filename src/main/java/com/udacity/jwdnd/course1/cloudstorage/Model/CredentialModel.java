package com.udacity.jwdnd.course1.cloudstorage.Model;

import lombok.Data;

@Data
public class CredentialModel {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userId;
}
