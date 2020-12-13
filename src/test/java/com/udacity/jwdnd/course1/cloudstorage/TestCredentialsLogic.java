package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;

public class TestCredentialsLogic extends TestWebApp{
    @Test
    void credentialsLoadEmpty(){
        signupAndLogin("noteModification");

        driver.get(baseUrl + "/home");
        CredentialsPage page = new CredentialsPage(driver);

    }
}
