package homework_lesson_24;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchField;
    @FindBy(xpath = "//button[contains(@class, 'search-form__submit')]")
    private WebElement findButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public ProductListingPage searchProducts(String product) {
        searchField.sendKeys(product);
        findButton.click();
        return new ProductListingPage(driver);
    }


}
