package org.example.seleniumtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class InterfaceValidationScreenshotTest {
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
    void captureContactPageScreenshot() throws IOException {
        WebElement contactLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact us")));
        contactLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("from")));

        File dir = new File("screenshots");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File("screenshots/contact_page.png"));
    }
}
