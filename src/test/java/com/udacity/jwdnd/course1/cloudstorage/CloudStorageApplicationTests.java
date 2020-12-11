package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private String baseUrl;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.edgedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new EdgeDriver();
		baseUrl = String.format("http://localhost:%d",port);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get(baseUrl + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getLoginPageFromAnywhereElse() {
		driver.get(baseUrl);
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(baseUrl+"/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getCssWithoutLogin() {
		String bootstrapCss = baseUrl + "/css/bootstrap.min.css";
		driver.get(bootstrapCss);
		Assertions.assertEquals(bootstrapCss, driver.getCurrentUrl());
	}

}
