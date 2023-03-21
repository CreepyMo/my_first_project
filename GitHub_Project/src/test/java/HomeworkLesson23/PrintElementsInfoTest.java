package HomeworkLesson23;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrintElementsInfoTest {

    @Test
    public void printProductsInfo() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        driver.get("https://rozetka.com.ua");

        do {
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            driver.findElements(By.xpath("//rz-goods-sections//section[@class='main-goods ng-star-inserted']"));
        }
        while(driver.findElements(By.xpath("//rz-goods-sections//section[@class='main-goods ng-star-inserted']")).size() < 10);

        List<WebElement> mostDiscussedProducts = driver.findElements(By.xpath("//h2[text()=' Найбільш обговорювані товари ']/following-sibling::ul/li"));

        for (int i = 1; i <= mostDiscussedProducts.size(); i++) {
            String productName = driver.findElement(By.xpath("//h2[text()=' Найбільш обговорювані товари ']/following-sibling::ul/li[" + i + "]//a[@class='tile__title']")).getAttribute("title");
            String productPriceStr = driver.findElement(By.xpath("//h2[text()=' Найбільш обговорювані товари ']/following-sibling::ul/li[" + i + "]" + "//div[@class='tile__price tile__price_color_red ng-star-inserted']")).getText();
            String adjustedPriceStr = productPriceStr.replaceAll("[^0-9]", "");
            int productPrice = Integer.parseInt(adjustedPriceStr);
            System.out.printf("%s коштує %d гривень. \n", productName, productPrice);
        }
        driver.quit();
    }


}
