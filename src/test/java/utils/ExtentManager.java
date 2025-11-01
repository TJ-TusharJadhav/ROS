package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;


public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports createInstance(String className) {

        if (extent != null) {
            return extent; // Already created for this class
        }

        String today = java.time.LocalDate.now().format(
                java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")
        );

        String baseDir = System.getProperty("user.dir") + "/reports/" + today + "/" + className + "/";
        File folder = new File(baseDir);
        if (!folder.exists()) folder.mkdirs();

        String baseFileName = className + ".html";
        File reportFile = new File(baseDir + baseFileName);

        int counter = 1;
        while (reportFile.exists()) {
            reportFile = new File(baseDir + className + "(" + counter + ").html");
            counter++;
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(reportFile.getAbsolutePath());
        spark.config().setDocumentTitle("Automation Test Report");
        spark.config().setReportName("Automation Execution Report");
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Company Name", "Shivalik Group");
        extent.setSystemInfo("Project Name", "ROS");
        extent.setSystemInfo("Tester Name", "Tushar Jadhav");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        System.out.println("✅ Extent Report File: " + reportFile.getAbsolutePath());
        return extent;
    }
}

