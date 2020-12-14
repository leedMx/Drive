package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private UserService userService;
    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialsService(UserService userService, CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<CredentialModel> getCredentials(String username){
        return credentialMapper.getCredentials(userService.getId(username));
    }

    public int insertOrUpdate(CredentialModel credential, String username){
        credential.setUserId(userService.getId(username));
        credential.setPassword(encryptedPassword(credential));
        if (credential.getCredentialId() == null)
            return credentialMapper.insert(credential);
        return credentialMapper.update(credential);
    }

    private String encryptedPassword(CredentialModel credential) {
        String key = encryptionService.createKey();
        String password = credential.getPassword();
        return encryptionService.encryptValue(password, key);
    }
}
