package com.udacity.jwdnd.course1.cloudstorage.workflows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginAndSignupWorkflowTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void homeNotAccessibleWithoutLoggingin() {

        this.driver.get("http://localhost:" + this.port + "/home");

        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
	public void loginWithInvalidCredentials() throws InterruptedException {

		driver.get("http://localhost:" + this.port + "/login");

		Assertions.assertEquals("Login", driver.getTitle());

		Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.id("invalidMessage"));
        });

        WebElement usrnameField = driver.findElement(By.id("inputUsername"));

        usrnameField.sendKeys("byu00");

        WebElement pwdField = driver.findElement(By.id("inputPassword"));

        pwdField.sendKeys("test123");

        WebElement submitButton = driver.findElement(By.id("loginBtn"));

        Assertions.assertEquals("Login", submitButton.getText());

        submitButton.click();

        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.id("invalidMessage"));
        });
    }

    private void signupWorkflow() {

        this.driver.get("http://localhost:" + this.port + "/signup");

        Assertions.assertEquals("Sign Up", driver.getTitle());

        Assertions.assertDoesNotThrow(() -> {

            WebElement fNameField = this.driver.findElement(By.id("inputFirstName"));

            fNameField.sendKeys("Hello");

            WebElement lNameField = this.driver.findElement(By.id("inputLastName"));

            lNameField.sendKeys("World");

            WebElement usrnameField = driver.findElement(By.id("inputUsername"));

            usrnameField.sendKeys("byu00");

            WebElement pwdField = driver.findElement(By.id("inputPassword"));

            pwdField.sendKeys("test1234");

            WebElement submitButton = driver.findElement(By.id("signupBtn"));

            Assertions.assertEquals("Sign Up", submitButton.getText());

            submitButton.click();
        });
    }

    private void loginWorkflow() {

        driver.get("http://localhost:" + this.port + "/login");

        Assertions.assertEquals("Login", driver.getTitle());

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.id("invalidMessage"));
        });

        WebElement usrnameField = driver.findElement(By.id("inputUsername"));

        usrnameField.sendKeys("byu00");

        WebElement pwdField = driver.findElement(By.id("inputPassword"));

        pwdField.sendKeys("test1234");

        WebElement submitButton = driver.findElement(By.id("loginBtn"));

        Assertions.assertEquals("Login", submitButton.getText());

        submitButton.click();

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.id("invalidMessage"));
        });
    }

    @Test
    public void signupAndLoginWorkflow() {

        this.signupWorkflow();

        this.loginWorkflow();

        this.driver.get("http://localhost:" + this.port + "/home");

        Assertions.assertEquals("Home", driver.getTitle());

        WebElement logoutButton = driver.findElement(By.id("logoutBtn"));

        Assertions.assertEquals("Logout", logoutButton.getText());

        logoutButton.click();

        this.driver.get("http://localhost:" + this.port + "/home");

        Assertions.assertEquals("Login", driver.getTitle());
    }
}
