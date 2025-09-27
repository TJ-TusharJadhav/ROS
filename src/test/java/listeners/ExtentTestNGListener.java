package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

import utils.ExtentManager;

public class ExtentTestNGListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static Page page;  // set from BaseTest
    private static long totalDuration = 0; // accumulate test durations

    @Override
    public void onStart(ITestContext context) {
        String className = context.getAllTestMethods()[0].getRealClass().getSimpleName();
        extent = ExtentManager.getInstance(className);  
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        ExtentTest extentTest = extent.createTest(className + " : " + result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long duration = (result.getEndMillis() - result.getStartMillis()) / 1000; 
        totalDuration += duration; // ✅ add to total
        test.get().log(Status.PASS, 
            "Test Passed | Execution Time: " + duration + " seconds");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long duration = (result.getEndMillis() - result.getStartMillis()) / 1000; 
        totalDuration += duration; // ✅ add to total
        test.get().log(Status.FAIL, 
            "Test Failed: " + result.getThrowable() + " | Execution Time: " + duration + " seconds");

        if (page != null) {
            byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshotBytes);
            test.get().addScreenCaptureFromBase64String(base64Screenshot, result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        long duration = (result.getEndMillis() - result.getStartMillis()) / 1000; 
        totalDuration += duration; // ✅ add to total
        test.get().log(Status.SKIP, 
            "Test Skipped | Execution Time: " + duration + " seconds");
    }

    @Override
    public void onFinish(ITestContext context) {
        long minutes = totalDuration / 60;
        long seconds = totalDuration % 60;

        extent.setSystemInfo("Total Duration of All Test Cases", 
            minutes + " min " + seconds + " sec (" + totalDuration + " seconds)");

        extent.flush();
    }

}
