package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCredentialsLogic extends TestWebApp{
    @Test
    void pageLoadsWithoutCredentials(){
        signupAndLogin("credential");

        driver.get(baseUrl + "/home");
        assertThrows(org.openqa.selenium.NoSuchElementException.class,
                ()-> new CredentialsPage(driver).getFirstCredentialUrl());
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

    @Test
    void credentialModification(){
        signupAndLogin("credentialModification");

        driver.get(baseUrl + "/home");
        CredentialsPage page = new CredentialsPage(driver);
        String url = "url";
        String user = "user";
        String password = "password";
        page.insertCredential(url, user, password);

        driver.get(baseUrl + "/home");
        page = new CredentialsPage(driver);
        page.openEditDialog();
        assertEquals(url,page.readUrlInput());
        assertEquals(user,page.readUsernameInput());
        assertEquals(password,page.readPasswordInput());

        String newUser = "new user";
        String newPassword = "new Password";
        page.updateCredential(url,newUser,newPassword);

        driver.get(baseUrl + "/home");
        page = new CredentialsPage(driver);
        assertEquals(url,page.getFirstCredentialUrl());
        assertEquals(newUser,page.getFirstCredentialUsername());
        assertNotEquals(newPassword,page.getFirstCredentialPassword());
    }

    @Test
    public void credentialDeletion(){
        signupAndLogin("credentialDeletion");

        driver.get(baseUrl + "/home");
        CredentialsPage page = new CredentialsPage(driver);
        String url = "url";
        String user = "user";
        String password = "password";
        page.insertCredential(url, user, password);

        driver.get(baseUrl + "/home");
        page = new CredentialsPage(driver);
        page.deleteFirstCredential();

        driver.get(baseUrl + "/home");
        assertThrows(org.openqa.selenium.NoSuchElementException.class,
                ()-> new CredentialsPage(driver).getFirstCredentialUrl());

    }
}
