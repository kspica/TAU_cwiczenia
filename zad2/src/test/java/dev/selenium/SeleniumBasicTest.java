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
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumBasicTest {

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
    public void eightComponents() {

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));
        WebElement dropdownSelect = driver.findElement(By.name("my-select"));
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        WebElement readOnlyInput = driver.findElement(By.name("my-readonly"));
        WebElement passwordInput = driver.findElement(By.name("my-password"));
        WebElement textArea = driver.findElement(By.name("my-textarea"));

        Select dropdownSel = new Select(dropdownSelect);


        assertEquals(4, dropdownSel.getOptions().size());
        assertFalse(disabledInput.isEnabled());
        assertTrue(readOnlyInput.isEnabled());
        assertEquals("password", passwordInput.getAttribute("type"));
        assertEquals(3, Integer.valueOf(textArea.getAttribute("rows")));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        WebElement header = driver.findElement(By.tagName("h1"));
        String messageValue = message.getText();
        String headerValue = header.getText();


        assertEquals("Received!", messageValue);
        assertEquals("Form submitted", headerValue);

    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
