package com.udacity.jwdnd.course1.cloudstorage.workflows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteCRUDWorkflowTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
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

    private void signupAndLoginUser() {

        this.signupUser();

        this.loginUser();
    }

    @BeforeEach
    public void beforeEach() {

        this.driver = new ChromeDriver();
        this.webDriverWait = new WebDriverWait (driver, 1000);

        this.signupAndLoginUser();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void createNote() throws InterruptedException {

        this.driver.get("http://localhost:" + this.port + "/home");

        WebElement notesTab = this.driver.findElement(By.id("nav-notes-tab"));

        notesTab.click();

        List<WebElement> notes = this.driver.findElements(By.className("note-elements"));

        this.webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("note-creation-btn")));

        WebElement noteCreationBtn = driver.findElement(By.id("note-creation-btn"));

        Assertions.assertNotNull(noteCreationBtn);

        noteCreationBtn.click();

        WebElement noteTitleField = this.driver.findElement(By.id("note-title"));

        this.webDriverWait.until(ExpectedConditions.visibilityOf(noteTitleField));

        noteTitleField.sendKeys("Hello");

        WebElement noteDescriptionField = this.driver.findElement(By.id("note-description"));

        noteDescriptionField.sendKeys("Hello World");

        WebElement noteForm = this.driver.findElement(By.id("note-form"));

        noteForm.submit();

        notesTab = this.driver.findElement(By.id("nav-notes-tab"));

        this.webDriverWait.until(ExpectedConditions.visibilityOf(notesTab));

        notesTab.click();

        List<WebElement> newNotes = this.driver.findElements(By.className("note-elements"));

        Assertions.assertEquals(notes.size() + 1, newNotes.size());
    }
}
