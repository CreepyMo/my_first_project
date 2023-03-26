package homework_lesson_24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCartPage extends BasePage {
    @FindBy(xpath = "//a[@class='cart-product__title']")
    private List<WebElement> purchases;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getPurchases() {
        return purchases;
    }


}
