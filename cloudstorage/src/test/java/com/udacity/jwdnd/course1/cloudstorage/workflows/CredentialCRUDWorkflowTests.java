package com.udacity.jwdnd.course1.cloudstorage.workflows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialCRUDWorkflowTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() throws InterruptedException {

        this.driver = new ChromeDriver();
        this.webDriverWait = new WebDriverWait (driver, 1000);

        this.signupAndLoginUser();

        this.insertNewCredential();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    public void createAndDeleteCredential() {

        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.xpath("//th[text()='www.google.com']"));
        });

        WebElement deleteBtn = this.driver.findElement(
                By.xpath("//*[@id=\"credentialTable\"]/tbody/tr/td[1]/a"));

        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteBtn));

        deleteBtn.click();

        this.backToHomeFromResultPage();

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            this.driver.findElement(By.xpath("//th[text()='www.google.com']"));
        });
    }

    @Test
    @Order(2)
    public void updateCredential() {

        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.xpath("//th[text()='www.google.com']"));
        });

        WebElement editBtn = this.driver.findElement(
                By.xpath("//*[@id='credentialTable']/tbody/tr/td[1]/button"));

        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(editBtn));

        editBtn.click();

        WebElement urlInputField = this.driver.findElement(By.id("credential-url"));

        this.webDriverWait.until(ExpectedConditions.visibilityOf(urlInputField));

        urlInputField.clear();
        urlInputField.sendKeys("www.github.com");

        WebElement usernameInputField = this.driver.findElement(By.id("credential-username"));

        usernameInputField.clear();
        usernameInputField.sendKeys("test");

        WebElement credentialForm = this.driver.findElement(By.id("credential-form"));

        credentialForm.submit();

        this.backToHomeFromResultPage();

        Assertions.assertDoesNotThrow(() -> {
            this.driver.findElement(By.xpath("//th[text()='www.github.com']"));
        });
    }

    /**
     * Private functions
     */

    private void insertNewCredential() {

        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement credentialsTab = this.driver.findElement(By.id("nav-credentials-tab"));

        credentialsTab.click();

        WebElement credentialCreationBtn = driver.findElement(By.id("credential-creation-btn"));

        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(credentialCreationBtn));

        credentialCreationBtn.click();

        WebElement urlInputField = this.driver.findElement(By.id("credential-url"));

        this.webDriverWait.until(ExpectedConditions.visibilityOf(urlInputField));

        urlInputField.sendKeys("www.google.com");

        WebElement usernameInputField = this.driver.findElement(By.id("credential-username"));

        usernameInputField.sendKeys("test");

        WebElement passwordInputField = this.driver.findElement(By.id("credential-password"));

        passwordInputField.sendKeys("test123");

        WebElement credentialForm = this.driver.findElement(By.id("credential-form"));

        credentialForm.submit();

        this.backToHomeFromResultPage();
    }

    private void backToHomeFromResultPage() {

        Assertions.assertEquals("Result", driver.getTitle());

        WebElement backToHomeBtn = this.driver.findElement(By.id("back-to-home-from-success"));

        backToHomeBtn.click();

        this.webDriverWait.until(ExpectedConditions.titleContains("Home"));

        Assertions.assertEquals("Home", driver.getTitle());

        WebElement credentialsTab = this.driver.findElement(By.id("nav-credentials-tab"));

        credentialsTab.click();
    }

    private void loginUser() {

        driver.get("http://localhost:" + this.port + "/login");

        WebElement usrnameField = driver.findElement(By.id("inputUsername"));

        usrnameField.sendKeys("byu00");

        WebElement pwdField = driver.findElement(By.id("inputPassword"));

        pwdField.sendKeys("test1234");

        WebElement submitButton = driver.findElement(By.id("loginBtn"));

        Assertions.assertEquals("Login", submitButton.getText());

        submitButton.click();
    }

    private void signupUser() {

        this.driver.get("http://localhost:" + this.port + "/signup");

        WebElement fNameField = this.driver.findElement(By.id("inputFirstName"));

        fNameField.sendKeys("Hello");

        WebElement lNameField = this.driver.findElement(By.id("inputLastName"));

        lNameField.sendKeys("World");

        WebElement usrnameField = driver.findElement(By.id("inputUsername"));

        usrnameField.sendKeys("byu00");

        WebElement pwdField = driver.findElement(By.id("inputPassword"));

        pwdField.sendKeys("test1234");

        WebElement submitButton = driver.findElement(By.id("signupBtn"));

        submitButton.click();
    }

    private void signupAndLoginUser() {

        this.signupUser();

        this.loginUser();
    }
}


