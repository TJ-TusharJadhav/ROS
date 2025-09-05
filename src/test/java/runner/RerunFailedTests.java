package runner;

import org.testng.TestNG;

import java.util.Collections;

public class RerunFailedTests {
    public static void main(String[] args) {
        System.out.println("Re-running failed test cases...");

        TestNG testng = new TestNG();

        // TestNG generates test-output/testng-failed.xml after a run
        String failedXml = "test-output/testng-failed.xml";

        testng.setTestSuites(Collections.singletonList(failedXml));
        testng.run();

        System.out.println("Failed test cases re-execution completed.");
    }
}
