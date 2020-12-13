package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class TestSecurityLogic extends TestWebApp {

    @Test
    public void loginMustBeAvailable() {
        assertAvailable("/login");
    }

    @Test
    public void noAuthenticationMustRedirect() {
        driver.get(baseUrl);
        assertEquals("Login", driver.getTitle());
        driver.get(baseUrl + "/home");
        assertEquals("Login", driver.getTitle());
    }

    @Test
    public void cssMustBeAvailable() {
        assertAvailable("/css/bootstrap.min.css");
    }

    @Test
    public void signUpMustBeAccessible() {
        assertAvailable("/signup");
    }

    private void assertAvailable(String suffix) {
        String url = baseUrl + suffix;
        driver.get(url);
        assertEquals(url, driver.getCurrentUrl());
    }

    @Test
    public void signupShowsSuccessMessage() {
        driver.get(baseUrl + "/signup");
        SignupPage page = new SignupPage(driver);
        page.signUp("fn", "ln", "deleteMe", PASSWORD);
        page.readSuccess();
    }

    @Test
    public void testSignupShowsNoErrorMessage() {
        driver.get(baseUrl + "/signup");
        SignupPage page = new SignupPage(driver);
        assertThrows(org.openqa.selenium.NoSuchElementException.class,
                () -> page.readError());
    }

    @Test
    public void testDuplicateSignupShowsError() {
        SignupPage page;

        driver.get(baseUrl + "/signup");
        page = new SignupPage(driver);
        page.signUp("fn", "ln", "duplicate", PASSWORD);

        driver.get(baseUrl + "/signup");
        page = new SignupPage(driver);
        page.signUp("fn", "ln", "duplicate", PASSWORD);
        page.readError();
    }

    @Test
    public void loginShowsErrorWhenInvalid() {
        driver.get(baseUrl + "/login");
        LoginPage page = new LoginPage(driver);
        page.logIn("username", "Wrong Password");
        page.readError();
    }

    @Test
    public void successfulLoginRedirectsToHome() {
        driver.get(baseUrl + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signUp("fn", "ln",
                "successful", PASSWORD);

        driver.get(baseUrl + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn("successful", PASSWORD);

        String expected = baseUrl + "/home";
        String actual = driver.getCurrentUrl();
        assertEquals(expected,actual);
    }

    @Test
    public void successfulLogoutRedirectsToLogin() {
        driver.get(baseUrl + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signUp("fn", "ln",
                "logout", PASSWORD);

        driver.get(baseUrl + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn("logout", PASSWORD);

        driver.get(baseUrl + "/home");
        HomePage homePage = new HomePage(driver);
        homePage.logout();

        String expected = baseUrl + "/login";
        String actual = driver.getCurrentUrl();
        assertEquals(expected,actual);
    }

}
