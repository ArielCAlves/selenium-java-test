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

public class ContactUsFormTest {
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
    void testContactUsForm() {
        WebElement contactLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Contact us")));
        contactLink.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("from")));
        WebElement emailInput = driver.findElement(By.name("from"));
        emailInput.sendKeys("email@gmail.com");

        WebElement messageInput = driver.findElement(By.name("message"));
        messageInput.sendKeys("Teste");

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input.btn.btn-primary[type='submit']")));
        submitButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
    }
}
