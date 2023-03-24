package homework_lesson_24;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RozetkaTests extends BaseTest {
    @Test
    public void simpleTest() {
        String productsStr = getProp();
        List<String> productsForSearch = new ArrayList<>(Arrays.asList(productsStr.split(",")));
        MainPage mainPage = openRozetka();

        for (int i = 0; i < productsForSearch.size(); i++) {
            ProductListingPage productListingPage = mainPage.searchProducts(productsForSearch.get(i));
            ProductDetailsPage productDetailsPage = productListingPage.proceedToProductPage();
            if (i < productsForSearch.size() - 1) {
                productDetailsPage.addToCartAndContinue();
            } else {
                ShoppingCartPage shoppingCartPage = productDetailsPage.addToCart();
                Assert.assertEquals(shoppingCartPage.getPurchases().size(), shoppingCartPage.getProductsCount());

                List<String> purchasesTitles = new ArrayList<>();
                for (WebElement e : shoppingCartPage.getPurchases()) {
                    purchasesTitles.add(e.getAttribute("title"));
                }
                for (int j = 0; j < purchasesTitles.size(); j++) {
                    Assert.assertTrue(purchasesTitles.get(j).contains(productsForSearch.get(productsForSearch.size() - (j + 1))));
                }
            }
        }
    }
}
