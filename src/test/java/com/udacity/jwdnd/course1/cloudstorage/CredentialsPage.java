package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CredentialsPage extends BasePage{
    @FindBy(id="nav-credentials-tab")
    private WebElement tabHeader;
    @FindBy(id="modal-credentials")
    private WebElement buttonForModal;

    public CredentialsPage(WebDriver driver) {
        super(driver);
        waitAndClick(tabHeader);
        waitFor(buttonForModal);
    }
}
