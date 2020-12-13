package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNotesLogic extends TestWebApp {
    @Test
    public void notesLoadsEmpty(){
        signupAndLogin("notes");

        driver.get(baseUrl + "/home");
        NotesPage page = new NotesPage(driver);
        assertThrows(org.openqa.selenium.NoSuchElementException.class,
                ()->page.getFirstNoteTitle());
    }

    @Test
    public void noteCreation(){
        signupAndLogin("noteCreation");

        driver.get(baseUrl + "/home");
        NotesPage page = new NotesPage(driver);

        String title = "Sample";
        String description = "Sample Description";
        page.createNote(title, description);

        driver.get(baseUrl + "/home");
        page = new NotesPage(driver);
        assertEquals(title, page.getFirstNoteTitle());
        assertEquals(description, page.getFirstNoteDescription());
    }

    @Test
    public void noteDeletion(){
        signupAndLogin("noteDeletion");

        driver.get(baseUrl + "/home");
        NotesPage page = new NotesPage(driver);

        String title = "Sample";
        String description = "Sample Description";
        page.createNote(title, description);

        driver.get(baseUrl + "/home");
        page = new NotesPage(driver);
        page.deleteFirstNote();

        driver.get(baseUrl + "/home");
        NotesPage test = new NotesPage(driver);
        assertThrows(org.openqa.selenium.NoSuchElementException.class,
                ()->test.getFirstNoteTitle());
    }

    @Test
    public void noteModification(){
        String username = "noteModification";
        signupAndLogin(username);

        driver.get(baseUrl + "/home");
        NotesPage page = new NotesPage(driver);
        page.createNote("Sample", "Sample Description");

        driver.get(baseUrl + "/home");
        page = new NotesPage(driver);
        String title = "new title";
        String description = "new Description";
        page.modifyNote(title, description);

        driver.get(baseUrl + "/home");
        page = new NotesPage(driver);
        assertEquals(title, page.getFirstNoteTitle());
        assertEquals(description, page.getFirstNoteDescription());
    }

}
