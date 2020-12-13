package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotesPage extends BasePage {
    @FindBy(id = "noteTitle-Row")
    private WebElement firstNoteTitle;
    @FindBy(id = "noteDescription-Row")
    private WebElement firstNoteDescription;
    @FindBy(id = "note-title")
    private WebElement noteTitle;
    @FindBy(id = "note-description")
    private WebElement noteDescription;
    @FindBy(id = "noteSubmit")
    private WebElement noteSubmit;
    @FindBy(id = "nav-notes-tab")
    private WebElement tabHeader;
    @FindBy(id = "modal-notes")
    private WebElement buttonForModal;
    @FindBy(id = "delete-note")
    private WebElement deleteButton;
    @FindBy(id = "edit-note")
    private WebElement editButton;

    public NotesPage(WebDriver driver) {
        super(driver);
        waitAndClick(tabHeader);
        waitFor(buttonForModal);
    }

    String getFirstNoteTitle() {
        return firstNoteTitle.getText();
    }

    String getFirstNoteDescription() {
        return firstNoteDescription.getText();
    }

    void createNote(String title, String description) {
        waitAndClick(buttonForModal);
        waitFor(noteTitle);
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        noteSubmit.submit();
    }

    void modifyNote(String title, String description){
        waitAndClick(editButton);
        waitFor(noteTitle);
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
        noteSubmit.submit();
    }

    void deleteFirstNote(){
        deleteButton.submit();
    }
}
