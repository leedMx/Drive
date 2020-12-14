package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCredentialsLogic extends TestWebApp{
    @Test
    void pageLoadsWithoutCredentials(){
        signupAndLogin("credential");

        driver.get(baseUrl + "/home");
        CredentialsPage page = new CredentialsPage(driver);
        assertThrows(org.openqa.selenium.NoSuchElementException.class,
                ()->page.getFirstCredentialUrl());
    }

    @Test
    void credentialCreation(){
        signupAndLogin("credentialCreation");

        driver.get(baseUrl + "/home");
        CredentialsPage page = new CredentialsPage(driver);
        String url = "url";
        String user = "user";
        String password = "password";
        page.insertCredential(url, user, password);

        driver.get(baseUrl + "/home");
        page = new CredentialsPage(driver);
        assertEquals(url,page.getFirstCredentialUrl());
        assertEquals(user,page.getFirstCredentialUsername());
        assertNotEquals(password,page.getFirstCredentialPassword());
    }
}
