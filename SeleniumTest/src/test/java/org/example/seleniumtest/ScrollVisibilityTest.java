package org.example.seleniumtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ScrollVisibilityTest {
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
    void testScrollAndVisibility() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer")));
        assert footer.isDisplayed();

        js.executeScript("window.scrollTo(0, 0);");

        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#_desktop_logo")));
        assert logo.isDisplayed();
    }
}
