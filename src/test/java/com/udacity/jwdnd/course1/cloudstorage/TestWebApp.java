package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestWebApp {
    @LocalServerPort
    protected int port;
    protected WebDriver driver;
    protected String baseUrl;
    protected static final String PASSWORD = "123";

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.edgedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new EdgeDriver();
        baseUrl = String.format("http://localhost:%d", port);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
}
