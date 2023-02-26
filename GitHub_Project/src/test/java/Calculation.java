import org.testng.Assert;
import org.testng.annotations.*;

@Test
public class Calculation {

    @Parameters(value = "Preparations")
    public void preparation(String param) throws InterruptedException {
        System.out.println(param);
        Thread.sleep(5000);
    }

    @Test(dataProvider = "getDataForAdd", dataProviderClass = Data.class, priority = 3)
    public void adding(int a, int b, int c) {
        Assert.assertEquals(a+b, c, "Oops... Adding is incorrect.");
    }

    @Test(dataProvider = "getDataForSubtract", dataProviderClass = Data.class, priority = 5)
    public void subtraction(int a, int b, int c) {
        Assert.assertEquals(a-b, c, "Oops... Subtraction is incorrect.");
    }

    @Test(dataProvider = "getDataForMultiply", dataProviderClass = Data.class, priority = 7)
    public void multiplication(int a, int b, int c) {
        Assert.assertEquals(a*b, c, "Oops... Multiplication is incorrect."); }

    @Test(dataProvider = "getDataForDivide", dataProviderClass = Data.class, priority = 9)
    public void division(int a, int b, int c) {
        Assert.assertEquals(a/b, c, "Oops... Division is incorrect.");
    }


    }


