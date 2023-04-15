package finalAutomationFramework.tests;

import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RozetkaTest extends BaseTest {

    @Test(description = "Check that phones filtering by screen size works correct.")
    public void checkScreenSize() {
        mobilePhonesPage = mainPage
                .clickPhonesAndElectronics()
                .clickMobilePhones()
                .clickCheckbox();
        Assert.assertEquals(mobilePhonesPage.getHeadingText(), "Мобільні телефони 5.55 - 6 Дюймів", "Mistake!");

        String screenSize = mobilePhonesPage
                .clickFirstPhone()
                .clickCharacteristics()
                .getScreenSize();
        double actualScreenSize = Double.parseDouble(screenSize);
        Assert.assertTrue(5.55 <= actualScreenSize && actualScreenSize <= 6, "Mistake!");
    }

    @Test(description = "Check that phones filtering by price works correct.", groups = {"prices"})
    public void checkPrices() {
        int minPrice = Integer.parseInt(getPropertyValue("min_price"));
        int maxPrice = Integer.parseInt(getPropertyValue("max_price"));
        List<Integer> list =  mainPage
                .clickPhonesAndElectronics()
                .clickMobilePhones()
                .setMinAndMaxPrice(minPrice, maxPrice)
                .getPricesList();
        for (Integer price : list) {
            Assert.assertTrue(minPrice <= price && price <= maxPrice, "Mistake!");
        }
    }

    @Test(description = "Check that correct checkbox is selected considering set screen size value")
    public void checkParticularScreenSize() {
        double screenSizeToTest = Double.parseDouble(getPropertyValue("screen_size"));

        mobilePhonesPage = mainPage
                .clickPhonesAndElectronics()
                .clickMobilePhones();

        List<List<Double>> screenSizesDoubles = mobilePhonesPage.getScreenSizesValuesList();

        for (int i = 0; i < screenSizesDoubles.size(); i++) {
            Double from = screenSizesDoubles.get(i).get(0);
            Double to = screenSizesDoubles.get(i).get(1);

            if (from <= screenSizeToTest && screenSizeToTest <= to) {
                mobilePhonesPage.getScreenSizesList().get(i).click();

                String screenSize = mobilePhonesPage
                        .clickFirstPhone()
                        .clickCharacteristics()
                        .getScreenSize();
                double actualScreenSize = Double.parseDouble(screenSize);
                Assert.assertTrue(from <= actualScreenSize && actualScreenSize <= to, "Mistake!");

                break;
            }
        }
    }
}