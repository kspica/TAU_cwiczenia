package dev.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumLoginTest {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @Test
    public void validLoginAndPasswordTest() {
        String login = "mngr600570";
        String password = "EvAtAhE";

        driver.get("https://demo.guru99.com/V4/index.php");

        String title = driver.getTitle();
        assertEquals("Guru99 Bank Home Page", title);

        WebElement loginInput = driver.findElement(By.name("uid"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);

        WebElement buttonLogin = driver.findElement(By.name("btnLogin"));
        buttonLogin.click();

        WebElement tdWitMngrId = driver.findElement(By.xpath("//td[contains(text(), 'Manger Id :')]"));
        assertEquals("Manger Id : mngr600570", tdWitMngrId.getText());

    }

    @Test
    public void invalidLoginTest() {
        String login = "mngr600570";
        String password = "invalid";

        driver.get("https://demo.guru99.com/V4/index.php");

        WebElement loginInput = driver.findElement(By.name("uid"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);

        WebElement buttonLogin = driver.findElement(By.name("btnLogin"));
        buttonLogin.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("User or Password is not valid",alert.getText());
        alert.accept();
    }

    @Test
    public void invalidPasswordTest() {
        String login = "invalid";
        String password = "EvAtAhE";

        driver.get("https://demo.guru99.com/V4/index.php");

        WebElement loginInput = driver.findElement(By.name("uid"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);

        WebElement buttonLogin = driver.findElement(By.name("btnLogin"));
        buttonLogin.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("User or Password is not valid",alert.getText());
        alert.accept();
    }

    @Test
    public void loginAndAddNewCustomerTest() {
        String login = "mngr600570";
        String password = "EvAtAhE";

        driver.get("https://demo.guru99.com/V4/index.php");

        WebElement loginInput = driver.findElement(By.name("uid"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);

        WebElement buttonLogin = driver.findElement(By.name("btnLogin"));
        buttonLogin.click();

        WebElement tdWitMngrId = driver.findElement(By.xpath("//td[contains(text(), 'Manger Id :')]"));
        assertEquals("Manger Id : mngr600570", tdWitMngrId.getText());

        WebElement link = driver.findElement(By.xpath("//a[text()='New Customer']"));
        link.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("heading3")));

        assertEquals("Add New Customer", inputElement.getText());

        fillForm(driver);

        WebElement buttonSubmit = driver.findElement(By.name("sub"));
        buttonSubmit.click();

        WebElement inputElementAfterSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("heading3")));
        WebElement addressValue = driver.findElement(By.xpath("//td[contains(text(), 'Testowy')]"));
        assertEquals("Customer Registered Successfully!!!", inputElementAfterSuccess.getText());
        assertEquals("Testowy adress", addressValue.getText());


    }

    private void fillForm(WebDriver driver) {
        String randomEmail = generateRandomMail();

        WebElement customerName = driver.findElement(By.name("name"));
        WebElement address = driver.findElement(By.name("addr"));
        WebElement city = driver.findElement(By.name("city"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement pinNo = driver.findElement(By.name("pinno"));
        WebElement telephoneNumber = driver.findElement(By.name("telephoneno"));
        WebElement email = driver.findElement(By.name("emailid"));
        WebElement password = driver.findElement(By.name("password"));

        customerName.sendKeys("Karol");
        address.sendKeys("Testowy adress");
        city.sendKeys("Warszawa");
        state.sendKeys("Iowa");
        pinNo.sendKeys("000000");
        telephoneNumber.sendKeys("675756767");
        email.sendKeys(randomEmail);
        password.sendKeys("password");

        WebElement dateInput = driver.findElement(By.name("dob")); // Zmień na odpowiedni selektor
        dateInput.sendKeys("09.11.2000");

    }

    private String generateRandomMail() {
        Random rand = new Random();
        return "karolSpica" + rand.nextInt(100000) + "@gmail.com";
    }


    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
