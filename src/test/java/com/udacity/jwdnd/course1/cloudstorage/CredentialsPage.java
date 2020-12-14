package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CredentialsPage extends BasePage{
    @FindBy(id="nav-credentials-tab")
    private WebElement tabHeader;
    @FindBy(id="modal-credentials")
    private WebElement buttonForModal;
    @FindBy(id="credential-url-row")
    private WebElement firstCredentialUrl;
    @FindBy(id="credential-username-row")
    private WebElement firstCredentialUsername;
    @FindBy(id="credential-password-row")
    private WebElement firstCredentialPassword;
    @FindBy(id="credential-url")
    private WebElement inputUrl;
    @FindBy(id="credential-username")
    private WebElement inputUsername;
    @FindBy(id="credential-password")
    private WebElement inputPassword;
    @FindBy(id="credentialSubmit")
    private WebElement submit;

    public CredentialsPage(WebDriver driver) {
        super(driver);
        waitAndClick(tabHeader);
        waitFor(buttonForModal);
    }

    String getFirstCredentialUrl(){
        return firstCredentialUrl.getText();
    }

    String getFirstCredentialUsername(){
        return firstCredentialUsername.getText();
    }

    String getFirstCredentialPassword(){
        return firstCredentialPassword.getText();
    }

    public void insertCredential(String url, String user, String password) {
        waitAndClick(buttonForModal);
        waitFor(inputUrl);
        inputUrl.sendKeys(url);
        inputUsername.sendKeys(user);
        inputPassword.sendKeys(password);
        submit.submit();
    }
}
