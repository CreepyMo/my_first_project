import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {


    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Testing of " + iTestResult.getMethod().getMethodName() + " starts.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Testing of " + iTestResult.getMethod().getMethodName() + " is completed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Testing starts.");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Testing is completed.");
    }
}
