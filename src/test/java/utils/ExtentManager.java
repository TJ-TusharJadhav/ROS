package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    // Pass className when creating instance
    public static ExtentReports getInstance(String className) {
        if (extent == null) {
            createInstance(className);
        }
        return extent;
    }

    public static ExtentReports createInstance(String className) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "TestReport_" + className + "_" + timestamp + ".html";
        String reportPath = System.getProperty("user.dir") + "/reports/" + reportName;

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Test Execution Report: " + className);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add system info (optional)
        extent.setSystemInfo("Company Name", "Shivalik Group");
        extent.setSystemInfo("Project Name", "ROS");
        extent.setSystemInfo("Tester Name", "Tushar Jadhav");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }
}
