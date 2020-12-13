package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(className="alert-danger")WebElement error;
    @FindBy(className="alert-dark")WebElement logout;
    @FindBy(id="inputUsername")WebElement username;
    @FindBy(id="inputPassword")WebElement password;
    @FindBy(className="btn-primary")WebElement submit;

    LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    void logIn(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        submit.submit();
    }
    String readError(){
        return error.getText();
    }
}
