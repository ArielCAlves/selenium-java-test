package org.example.seleniumtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.time.Duration;
import java.util.Base64;

public class LoginWithCookiesTest {
    WebDriver driver;
    WebDriverWait wait;
    File cookieFile = new File("cookies.data");

    @BeforeEach
    void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.prestashop.com/#/en/front");

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".user-info")));

        driver.manage().deleteAllCookies();

        if (cookieFile.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(cookieFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length == 5) {
                    String name = parts[0];
                    String value = new String(Base64.getDecoder().decode(parts[1]));
                    String path = parts[2];
                    boolean isSecure = Boolean.parseBoolean(parts[3]);
                    String domain = parts[4];
                    Cookie cookie = new Cookie.Builder(name, value).domain(domain).path(path).isSecure(isSecure).build();
                    try {
                        driver.manage().addCookie(cookie);
                    } catch (Exception ignored) {}
                }
            }
            br.close();
            driver.navigate().refresh();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("framelive")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".user-info")));
        }
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLoginAndSaveCookies() throws IOException, InterruptedException {
        if (!cookieFile.exists()) {
            WebElement signIn = wait.until(ExpectedConditions.elementToBeClickable(By.className("user-info")));
            signIn.click();

            WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
            email.sendKeys("testuser@example.com");

            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("0`FO)hDu1e!/");

            WebElement submitLogin = driver.findElement(By.id("submit-login"));
            submitLogin.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("account")));

            BufferedWriter bw = new BufferedWriter(new FileWriter(cookieFile));
            for (Cookie ck : driver.manage().getCookies()) {
                String encodedValue = Base64.getEncoder().encodeToString(ck.getValue().getBytes());
                bw.write(ck.getName() + ";" + encodedValue + ";" + ck.getPath() + ";" + ck.isSecure() + ";" + ck.getDomain());
                bw.newLine();
            }
            bw.close();
        }

        WebElement userIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account")));
        Assertions.assertTrue(userIcon.isDisplayed());
    }
}
