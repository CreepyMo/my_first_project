package homework_lesson_24;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void initBrowser() {
        WebDriverManager.chromedriver().arch64().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("incognito");
        options.addArguments("disable-notifications");
        driver = new ChromeDriver(options);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }

    public MainPage openRozetka() {
        driver.get("https://rozetka.com.ua/ua/");
        return new MainPage(driver);
    }
    private static String getPropValue(String propName) {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/products.properties");
            prop.load(fis);
        }
        catch (IOException ignored) {}
        return prop.getProperty(propName);
    }
    public static String getProp() {
        return Objects.isNull(System.getProperty("products"))
                ? getPropValue("products")
                : System.getProperty("products");
    }

}
