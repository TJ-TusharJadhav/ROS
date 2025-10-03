package listeners;

import org.testng.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import utils.ExtentManager;

import java.util.Set;
import java.util.stream.Collectors;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static Page page;  // set from BaseTest
    private static long totalDuration = 0;

    @Override
    public void onStart(ISuite suite) {
        // 🔹 Collect all test class names in this suite
        Set<String> classNames = suite.getAllMethods().stream()
                .map(m -> m.getRealClass().getSimpleName())
                .collect(Collectors.toSet());

        String reportName;
        if (classNames.size() == 1) {
            // Only one class → use class name
            reportName = classNames.iterator().next();
        } else {
            // Multiple classes → use suite name
            reportName = suite.getName();
        }

        extent = ExtentManager.getInstance(reportName);
    }

    @Override
    public void onFinish(ISuite suite) {
        long hours = totalDuration / 3600;
        long minutes = (totalDuration % 3600) / 60;
        long seconds = totalDuration % 60;

        String durationFormatted = 
            (hours > 0 ? hours + " hr " : "") +
            (minutes > 0 ? minutes + " min " : "") +
            seconds + " sec ";

        extent.setSystemInfo("Total Duration of All Test Cases", durationFormatted);

        extent.flush();
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
        totalDuration += duration;
        test.get().log(Status.PASS, "Test Passed | Execution Time: " + duration + " seconds");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long duration = (result.getEndMillis() - result.getStartMillis()) / 1000;
        totalDuration += duration;
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable() + " | Execution Time: " + duration + " seconds");

        if (page != null) {
            byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            String base64Screenshot = java.util.Base64.getEncoder().encodeToString(screenshotBytes);
            test.get().addScreenCaptureFromBase64String(base64Screenshot, result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        long duration = (result.getEndMillis() - result.getStartMillis()) / 1000;
        totalDuration += duration;
        test.get().log(Status.SKIP, "Test Skipped | Execution Time: " + duration + " seconds");
    }

    // Empty overrides
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
