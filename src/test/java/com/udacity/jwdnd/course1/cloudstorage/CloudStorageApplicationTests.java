package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	private final String password = "123";
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
	public void loginMustBeAvailable() {
		assertAvailable("/login");
	}

	@Test
	public void noAuthenticationMustRedirect() {
		driver.get(baseUrl);
		assertEquals("Login", driver.getTitle());
		driver.get(baseUrl+"/home");
		assertEquals("Login", driver.getTitle());
	}

	@Test
	public void cssMustBeAvailable() {
		assertAvailable("/css/bootstrap.min.css");
	}

	@Test
	public void signUpMustBeAccessible(){
		assertAvailable("/signup");
	}

	private void assertAvailable(String suffix) {
		String url = baseUrl + suffix;
		driver.get(url);
		assertEquals(url, driver.getCurrentUrl());
	}

	@Test
    public void signupShowsSuccessMessage(){
		driver.get(baseUrl+"/signup");
		SignupPage page = new SignupPage(driver);
		page.signUp("fn","ln","deleteMe", password);
		page.readSuccess();
    }

	@Test
    public void testSignupShowsNoErrorMessage(){
		driver.get(baseUrl+"/signup");
		SignupPage page = new SignupPage(driver);
		assertThrows(org.openqa.selenium.NoSuchElementException.class,
				()-> page.readError());
    }

	@Test
	public void testDuplicateSignupShowsError(){
		SignupPage page;

		driver.get(baseUrl+"/signup");
		page = new SignupPage(driver);
		page.signUp("fn", "ln","duplicate", password);

		driver.get(baseUrl+"/signup");
		page = new SignupPage(driver);
		page.signUp("fn", "ln","duplicate", password);

		page.readError();
	}
}
