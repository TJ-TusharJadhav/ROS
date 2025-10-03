package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    // Always returns single instance
    public static ExtentReports getInstance(String suiteName) {
        if (extent == null) {
            createInstance(suiteName);
        }
        return extent;
    }

    public static ExtentReports createInstance(String suiteName) {
        // Timestamped file name (unique per run)
        String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
        String reportName = "ExtentReport_" + suiteName + "_" + timestamp + ".html";
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Suite Report");
        spark.config().setReportName("Test Execution Report: " + suiteName);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add system info
        extent.setSystemInfo("Company Name", "Shivalik Group");
        extent.setSystemInfo("Project Name", "ROS");
        extent.setSystemInfo("Tester Name", "Tushar Jadhav");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }
}
