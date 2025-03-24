package org.example.seleniumtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginUserTest {
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
    void testLoginWithValidCredentials() {
        WebElement signInLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("user-info")));
        signInLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        driver.findElement(By.name("email")).sendKeys("testuser@email.com");
        driver.findElement(By.name("password")).sendKeys("0`FO)hDu1e!/");

        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit-login")));
        loginButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account")));
    }
}
