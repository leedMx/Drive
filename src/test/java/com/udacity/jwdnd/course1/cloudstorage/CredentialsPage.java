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
    @FindBy(id="editCredential")
    private WebElement edit;
    @FindBy(id = "credentialModalLabel")
    private WebElement modal;
    @FindBy(id = "deleteCredential")
    private WebElement deleteFirstCredential;

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
        updateCredential(url,user,password);
    }

    public void openEditDialog() {
        waitAndClick(edit);
        waitFor(modal);
    }

    public String readUrlInput() {
        return inputUrl.getAttribute("value");
    }

    public String readUsernameInput() {
        return inputUsername.getAttribute("value");
    }

    public String readPasswordInput() {
        return inputPassword.getAttribute("value");
    }

    public void updateCredential(String url, String user, String password) {
        inputUrl.clear();
        inputUrl.sendKeys(url);
        inputUsername.clear();
        inputUsername.sendKeys(user);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        submit.submit();
    }

    public void deleteFirstCredential() {
        deleteFirstCredential.submit();
    }
}
