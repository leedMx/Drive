package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    @FindBy (id="success")WebElement success;
    @FindBy (id="error")WebElement error;
    @FindBy (id="inputFirstName")WebElement firstname;
    @FindBy (id="inputLastName")WebElement lastname;
    @FindBy (id="inputUsername")WebElement username;
    @FindBy (id="inputPassword")WebElement password;
    @FindBy (id="submit")WebElement submit;
    WebDriver driver;
    SignupPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    void signUp(String firstname,String lastname, String user, String password){
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.username.sendKeys(user);
        this.password.sendKeys(password);
        submit.submit();
    }

    String readSuccess(){
        return success.getText();
    }

    String readError(){
        return error.getText();
    }
}
