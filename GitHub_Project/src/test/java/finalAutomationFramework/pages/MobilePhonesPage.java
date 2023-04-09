package finalAutomationFramework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RegExUtils.replaceAll;

public class MobilePhonesPage extends BasePage {

    @FindBy(xpath = "(//span[contains(text(), 'Діагональ екрана')]/../..//a)[4]")
    private WebElement fiveAndHalfToSixInchesScreenSizeCheckbox;

    @FindBy(xpath = "//h1[contains(@class, 'catalog-heading')]")
    private WebElement headingText;

    @FindBy(xpath = "(//div[@class='goods-tile__inner'])[1]")
    private WebElement firstPhone;

    @FindBy(xpath = "//input[@formcontrolname='min']")
    private WebElement minPrice;

    @FindBy(xpath = "//input[@formcontrolname='max']")
    private WebElement maxPrice;

    @FindBy(xpath = "//span[@class='goods-tile__price-value']")
    private List<WebElement> pricesList;

    @FindBy(xpath = "(//div[@data-filter-name='23777']//ul)[1]/li/a")
    private List<WebElement> screenSizesList;

    public MobilePhonesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 5.55\" - 6\" screen size checkbox")
    public MobilePhonesPage clickCheckbox() {
        wait.until(ExpectedConditions.visibilityOf(fiveAndHalfToSixInchesScreenSizeCheckbox));
        fiveAndHalfToSixInchesScreenSizeCheckbox.click();
        wait.until(ExpectedConditions.or(
                ExpectedConditions.attributeContains(fiveAndHalfToSixInchesScreenSizeCheckbox, "class", "checked"),
                ExpectedConditions.attributeContains(fiveAndHalfToSixInchesScreenSizeCheckbox, "class", "active"))
        );
        return this;
    }

    public String getHeadingText() {
        jse.executeScript("window.scrollBy(0,-250)", "");
        return headingText.getText();
    }

    @Step("Click on 1st phone picture")
    public PhoneProductPage clickFirstPhone() {
        jse.executeScript("arguments[0].scrollIntoView();", firstPhone);
        firstPhone.click();
        return new PhoneProductPage(driver);
    }

    @Step("Set {0} grn as minimal price and {1} grn as maximal price")
    public MobilePhonesPage setMinAndMaxPrice(int min, int max) {
        minPrice.clear();
        minPrice.sendKeys(String.valueOf(min), Keys.TAB, String.valueOf(max), Keys.ENTER);
        return this;
    }

    @Step("Get list of price")
    public List<Integer> getPricesList() {
        wait.until(ExpectedConditions.visibilityOfAllElements(pricesList));
        List<Integer> stringList = new ArrayList<>();
        for (WebElement el : pricesList) {
            String str = el.getText().replace(" ", "");
            String finalString = str.substring(0, str.length() - 1);
            stringList.add(Integer.parseInt(finalString));
        }
        return stringList;
    }

    public List<WebElement> getScreenSizesList() {
        return this.screenSizesList;
    }

    public List<List<Double>> getScreenSizesValuesList() {

        List<List<Double>> screenSizesValuesList = new ArrayList<>();

        for (int i = 0; i < screenSizesList.size(); i++) {
            String screenSizeStr = screenSizesList.get(i).getText();

            if (i == 0) {
                List<Double> fromToScreenSizesValues = new ArrayList<>();
                String digits = screenSizeStr.replaceAll("[^0-9.]", "");
                Double from = 0.0;
                Double to = Double.parseDouble(digits);
                fromToScreenSizesValues.add(from);
                fromToScreenSizesValues.add(to);
                screenSizesValuesList.add(fromToScreenSizesValues);
            } else if (i == screenSizesList.size() - 1) {
                List<Double> fromToScreenSizesValues = new ArrayList<>();
                String digits = screenSizeStr.replaceAll("[^0-9.]", "");
                Double from = Double.parseDouble(digits);
                Double to = Double.MAX_VALUE;
                fromToScreenSizesValues.add(from);
                fromToScreenSizesValues.add(to);
                screenSizesValuesList.add(fromToScreenSizesValues);
            } else {
                List<Double> fromToScreenSizesValues = new ArrayList<>();
                String[] fromAndToScreenSizes = screenSizeStr.split("\"");
                String fromDigits = fromAndToScreenSizes[0].replaceAll("[^0-9.]", "");
                String toDigits = fromAndToScreenSizes[1].replaceAll("[^0-9.]", "");
                fromToScreenSizesValues.add(Double.parseDouble(fromDigits));
                fromToScreenSizesValues.add(Double.parseDouble(toDigits));
                screenSizesValuesList.add(fromToScreenSizesValues);
            }
        }
        return screenSizesValuesList;
    }
}