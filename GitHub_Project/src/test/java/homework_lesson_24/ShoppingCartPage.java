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

    public void checkItemsQuantity() {
        Assert.assertEquals(purchases.size(), getProductsCount());
    }


    public void checkShoppingCartContent() {
        List<String> purchasesTitles = new ArrayList<>();
        String productsStr = BaseTest.getProp();
        List<String> productsForSearch = new ArrayList<>(Arrays.asList(productsStr.split(",")));
        for (WebElement e : purchases) {
            purchasesTitles.add(e.getAttribute("title"));
        }

        for (int i = 0; i < purchasesTitles.size(); i++) {
            Assert.assertTrue(purchasesTitles.get(i).contains(productsForSearch.get(productsForSearch.size() - (i + 1))));
        }
    }

}
