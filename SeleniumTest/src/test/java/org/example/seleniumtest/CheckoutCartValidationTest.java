package org.example.seleniumtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class CheckoutCartValidationTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.prestashop.com/#/en/front");
        Thread.sleep(5000);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAddAndVerifyCartQuantity() {
        WebElement product = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".product-miniature .thumbnail-container")));
        product.click();

        WebElement addToCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary.add-to-cart")));
        addToCart.click();

        WebElement proceedToCart = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#blockcart-modal a.btn.btn-primary")));
        proceedToCart.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-items")));
        WebElement quantityInput = driver.findElement(By.cssSelector(".cart-items input.js-cart-line-product-quantity"));
        String quantity = quantityInput.getAttribute("value");
        assert quantity.equals("1");
    }
}
