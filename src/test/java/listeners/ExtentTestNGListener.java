package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;
import utils.ExtentManager;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private long suiteStartTime;

    @Override
    public void onStart(ISuite suite) {
        extent = ExtentManager.getInstance();
        suiteStartTime = System.currentTimeMillis();
    }

    @Override
    public void onFinish(ISuite suite) {
        long totalDuration = (System.currentTimeMillis() - suiteStartTime) / 1000;
        long hours = totalDuration / 3600;
        long minutes = (totalDuration % 3600) / 60;
        long seconds = totalDuration % 60;

        String durationFormatted =
                (hours > 0 ? hours + " hr " : "") +
                (minutes > 0 ? minutes + " min " : "") +
                seconds + " sec";

        extent.setSystemInfo("Total Duration of All Test Cases", durationFormatted);
        extent.flush();

        System.out.println("✅ Extent report generated successfully after suite finish.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip(result.getThrowable());
    }

    // Ensures report flush even when suite events don't fire (single-class runs)
    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            System.out.println("⚙️ Extent report flushed at test context finish.");
        }
    }
}
