package utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {
    private static Page page;

    public static void setPage(Page pg) {
        page = pg;
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshot() {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test Failed: " + result.getName());
        if (page != null) {
            saveScreenshot();
        }
    }
}
