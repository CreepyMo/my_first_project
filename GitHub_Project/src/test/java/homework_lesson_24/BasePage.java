package homework_lesson_24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected static int productsCount = 0;
    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
        this.driver = driver;
    }
    public int getProductsCount() {
        return productsCount;
    }
}
