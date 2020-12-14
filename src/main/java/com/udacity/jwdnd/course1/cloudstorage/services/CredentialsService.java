package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService {
    private UserService userService;
    private CredentialMapper mapper;
    private EncryptionService encryption;

    public CredentialsService(
            UserService userService, CredentialMapper credentialMapper,
            EncryptionService encryptionService) {
        this.userService = userService;
        this.mapper = credentialMapper;
        this.encryption = encryptionService;
    }

    public List<CredentialModel> getCredentials(String username) {
        return mapper.getCredentials(userService.getId(username));
    }

    public int insertOrUpdate(CredentialModel credential, String username) {
        String key = encryption.createKey();
        credential.setUserId(userService.getId(username));
        credential.setPassword(encryptedPassword(credential, key));
        credential.setKey(key);
        if (credential.getCredentialId() == null)
            return mapper.insert(credential);
        return mapper.update(credential);
    }

    private String encryptedPassword(CredentialModel credential, String key) {
        String password = credential.getPassword();
        return encryption.encryptValue(password, key);
    }

    public int delete(Integer credentialId, String username) {
        return mapper.delete(credentialId,userService.getId(username));
    }
}
