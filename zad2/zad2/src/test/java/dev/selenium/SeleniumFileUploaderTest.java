package dev.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumFileUploaderTest {

    WebDriver driver;

    @BeforeEach
    public void setup() throws Exception {
        String browser = "Edge";
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new Exception("Incorrect Browser");
        }
    }

    @Test
    public void fileUploaderTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://the-internet.herokuapp.com/upload");


        WebElement uploadElement = driver.findElement(By.id("file-upload"));

        String fileAbsolutePath = getAbsolutePath();
        uploadElement.sendKeys(fileAbsolutePath);

        WebElement uploadFile = driver.findElement(By.id("file-submit"));
        uploadFile.click();

        WebElement successHeader = driver.findElement(By.tagName("h3"));
        WebElement fileName = driver.findElement(By.id("uploaded-files"));

        assertEquals("File Uploaded!", successHeader.getText());
        assertEquals("testFile.pdf", fileName.getText());

    }

    private String getAbsolutePath() {
        String relativePath = "src/test/resources/testFile.pdf"; // Zmień na nazwę swojego pliku
        File file = new File(relativePath);
        return file.getAbsolutePath();
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
