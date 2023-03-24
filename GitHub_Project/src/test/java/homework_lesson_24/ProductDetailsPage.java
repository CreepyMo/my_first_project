package homework_lesson_24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {
    @FindBy(xpath = "//div[@class='product-about__block ng-star-inserted']//button[@aria-label='Купити']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//button[@class='modal__close']")
    private WebElement closeMiniCartButton;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage addToCart() {
        addToCartButton.click();
        productsCount++;
        return new ShoppingCartPage(driver);
    }

    public void addToCartAndContinue() {
        addToCartButton.click();
        productsCount++;
        closeMiniCartButton.click();
    }
}
