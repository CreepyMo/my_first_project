import org.testng.annotations.DataProvider;

public class Data {

    @DataProvider
    public static Object[][] getDataForAdd() {
        return new Object[][] {
                {1,2,3},
                {2,4,6},
                {9,0,9}
        };
    }
    @DataProvider
    public static Object[][] getDataForSubtract() {
        return new Object[][] {
                {10,-8,18},
                {0,9,-9},
                {123,11,112}
        };
    }
    @DataProvider
    public static Object[][] getDataForMultiply() {
        return new Object[][] {
                {2,120,240},
                {2,1,2},
                {0,9,0}
        };
    }
    @DataProvider
    public static Object[][] getDataForDivide() {
        return new Object[][] {
                {10,2,5},
                {9,9,1},
                {125,5,25}
        };
    }
}
