package com.udacity.jwdnd.course1.cloudstorage;

import org.h2.mvstore.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(id = "logout")
    private WebElement logout;

    HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    void logout() {
        logout.submit();
    }
}
