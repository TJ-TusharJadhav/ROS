package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    public static ExtentReports createInstance(String suiteName, String className, String methodName) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String reportName = className + "_" + methodName + "_" + timeStamp + ".html";
        String reportPath = System.getProperty("user.dir") + "/reports/" + today + "/" + suiteName + "/" + className + "/" + reportName;

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Automation Execution Report");
        spark.config().setTheme(Theme.STANDARD);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Company Name", "Shivalik Group");
        extent.setSystemInfo("Project Name", "ROS");
        extent.setSystemInfo("Tester Name", "Tushar Jadhav");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        System.out.println("✅ Extent Report for Test: " + reportPath);
        return extent;
    }
}
