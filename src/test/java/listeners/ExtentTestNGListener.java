package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;
import utils.ExtentManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static Map<String, ExtentReports> reportMap = new ConcurrentHashMap<>();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    private static long suiteStartTime;
    private static long suiteEndTime;

    @Override
    public void onStart(ISuite suite) {
        suiteStartTime = System.currentTimeMillis();
    }

    @Override
    public void onFinish(ISuite suite) {
        suiteEndTime = System.currentTimeMillis();

        long totalDurationMillis = suiteEndTime - suiteStartTime;
        long hours = (totalDurationMillis / (1000 * 60 * 60)) % 24;
        long minutes = (totalDurationMillis / (1000 * 60)) % 60;
        long seconds = (totalDurationMillis / 1000) % 60;

        String formattedDuration = String.format("%02dh %02dm %02ds", hours, minutes, seconds);

        // Add total duration to all reports
        for (ExtentReports extent : reportMap.values()) {
            extent.setSystemInfo("Total Test Duration", formattedDuration);
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        String key = className + "_" + methodName;

        reportMap.putIfAbsent(key, ExtentManager.createInstance(className, methodName));

        ExtentReports extent = reportMap.get(key);
        ExtentTest test = extent.createTest(methodName + " - DataSet: " + getDataSetLabel(result));
        testThread.set(test);
    }

    private String getDataSetLabel(ITestResult result) {
        Object[] params = result.getParameters();
        if (params == null || params.length == 0) return "Default";
        return String.join("_",
                java.util.Arrays.stream(params)
                        .map(String::valueOf)
                        .toArray(String[]::new)
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("✅ Test passed");
        flushReport(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());
        flushReport(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip(result.getThrowable());
        flushReport(result);
    }

    private void flushReport(ITestResult result) {
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();
        String key = className + "_" + methodName;

        ExtentReports extent = reportMap.get(key);
        extent.flush();
    }
}
