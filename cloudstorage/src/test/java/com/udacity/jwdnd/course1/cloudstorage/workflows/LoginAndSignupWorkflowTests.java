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

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginAndSignupWorkflowTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait driverWait;

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
	public void login() throws InterruptedException {

		driver.get("http://localhost:" + this.port + "/login");

		Assertions.assertEquals("Login", driver.getTitle());

		Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.id("invalidMessage"));
        });

        WebElement usrnameField = driver.findElement(By.id("inputUsername"));

        Thread.sleep(1000);

        usrnameField.sendKeys("byu00");

        Thread.sleep(1000);

        WebElement pwdField = driver.findElement(By.id("inputPassword"));

        Thread.sleep(1000);

        pwdField.sendKeys("test123");

        Thread.sleep(1000);

        WebElement submitButton = driver.findElement(By.id("loginBtn"));

        Assertions.assertEquals("Login", submitButton.getText());

        Thread.sleep(3000);

        submitButton.click();

        Thread.sleep(1000);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.id("invalidMessage"));
        });
    }
}
