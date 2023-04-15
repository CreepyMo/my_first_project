package homework_lesson_24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage {
    @FindBy(xpath = "//div[@class='product-about__block ng-star-inserted']//button[@aria-label='Купити']")
    private WebElement addToCartButton;
    @FindBy(xpath = "//button[@class='modal__close']")
    private WebElement closeMiniCartButton;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        productsCount ++;
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//a[@class='cart-product__title']"), 0));
        return new ShoppingCartPage(driver);
    }

    public void addToCartAndContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        productsCount ++;
        wait.until(ExpectedConditions.elementToBeClickable(closeMiniCartButton));
        closeMiniCartButton.click();
    }
}
