package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;
import utils.ExtentManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ExtentTestNGListener implements ITestListener {

    // Cache reports per (class + method)
    private static Map<String, ExtentReports> reportMap = new ConcurrentHashMap<>();
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // String suiteName = result.getTestContext().getSuite().getName();
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        // Unique key for each test method
        String key = className + "_" + methodName;

        // Create report only once per method
        reportMap.putIfAbsent(key, ExtentManager.createInstance( className, methodName));

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
        String suiteName = result.getTestContext().getSuite().getName();
        String className = result.getTestClass().getRealClass().getSimpleName();
        String methodName = result.getMethod().getMethodName();

        String key = suiteName + "_" + className + "_" + methodName;
        reportMap.get(key).flush();
    }
}