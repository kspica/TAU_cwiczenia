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

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumShopTest {


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
    public void cartTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("https://www.saucedemo.com/");
        login(driver);
        WebElement addToCartFirst = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        WebElement addToCartSecond = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        WebElement addToCartThird = driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));

        addToCartFirst.click();
        addToCartSecond.click();
        addToCartThird.click();

        WebElement cartCounter = driver.findElement(By.className("shopping_cart_badge"));

        assertEquals("3",cartCounter.getText());

        WebElement removeFromCartFirst = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeFromCartFirst.click();

        assertEquals("2",cartCounter.getText());

        WebElement goToCart = driver.findElement(By.className("shopping_cart_link"));
        goToCart.click();

        List<WebElement> elements = driver.findElements(By.className("cart_item"));
        WebElement cartTitle = driver.findElement(By.className("title"));

        assertEquals(2, elements.size());
        assertEquals("Your Cart", cartTitle.getText());

        WebElement checkout = driver.findElement(By.name("checkout"));
        checkout.click();

        WebElement checkoutTitle = driver.findElement(By.className("title"));

        assertEquals("Checkout: Your Information", checkoutTitle.getText());
        fillForm(driver);

        WebElement continueButton = driver.findElement(By.name("continue"));
        continueButton.click();


        WebElement cartValue = driver.findElement(By.className("summary_total_label"));
        WebElement checkoutOverview = driver.findElement(By.className("title"));

        assertEquals("Checkout: Overview", checkoutOverview.getText());
        assertTrue( cartValue.getText().contains("28.06"));

        WebElement finish = driver.findElement(By.name("finish"));
        finish.click();

        WebElement success = driver.findElement(By.className("complete-header"));
        assertEquals("Thank you for your order!", success.getText());

    }

    private void fillForm(WebDriver driver) {
        WebElement firstName = driver.findElement(By.name("firstName"));
        WebElement lastName = driver.findElement(By.name("lastName"));
        WebElement postalCode = driver.findElement(By.name("postalCode"));

        firstName.sendKeys("John");
        lastName.sendKeys("Smith");
        postalCode.sendKeys("12345");
    }

    public void login(WebDriver driver) {
        String login = "standard_user";
        String password = "secret_sauce";

        WebElement loginInput = driver.findElement(By.name("user-name"));
        WebElement passwordInput = driver.findElement(By.name("password"));

        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);

        WebElement buttonLogin = driver.findElement(By.name("login-button"));
        buttonLogin.click();

    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
