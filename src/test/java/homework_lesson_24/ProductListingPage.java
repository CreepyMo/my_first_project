package homework_lesson_24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductListingPage extends BasePage {
    @FindBy(xpath = "//ul[@rzgridlayout]/li[3]//a[@title]")
    private WebElement thirdProduct;
    public ProductListingPage(WebDriver driver) {
        super(driver);
    }
    public ProductDetailsPage proceedToProductPage() {
        thirdProduct.click();
        return new ProductDetailsPage(driver);
    }
}
