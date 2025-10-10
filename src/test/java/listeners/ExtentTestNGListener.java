package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;
import utils.ExtentManager;

public class ExtentTestNGListener implements ITestListener {

    private static ThreadLocal<ExtentReports> extentThread = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String suiteName = result.getTestContext().getSuite().getName();
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        ExtentReports extent = ExtentManager.createInstance(suiteName, className, methodName);
        extentThread.set(extent);

        ExtentTest test = extent.createTest(className + " - " + methodName);
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("✅ Test passed");
        extentThread.get().flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());
        extentThread.get().flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip(result.getThrowable());
        extentThread.get().flush();
    }
}
