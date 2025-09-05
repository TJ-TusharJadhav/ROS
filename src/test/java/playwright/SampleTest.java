package playwright;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SampleTest {

	static Playwright playwright;
	static Browser browser;
    BrowserContext mobileContext;
    Page page;

    @BeforeClass
    public void setUpMobileEmulation() {
    	 playwright = Playwright.create();
         browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    	 page = browser.newPage();
}
   // for Mobile size  
//    @BeforeClass
//    public void setUpMobileContext() {
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        Browser.NewContextOptions iPhone12 = new Browser.NewContextOptions()
//            .setViewportSize(390, 844)
//            .setDeviceScaleFactor(3)
//            .setIsMobile(true)
//            .setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 14_0 like Mac OS X) " +
//                          "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0 Mobile/15E148 Safari/604.1");
//
//        mobileContext = browser.newContext(iPhone12);
//        page = mobileContext.newPage();
//    }
    @Test
    public void verifyNonRegisterMobileNumberErrorMessageOnMobile() {
        page.navigate("https://admin-core-development.realestateos.io/login");
        page.fill("#phone", "9876543210");
        page.click("//button[@type='submit']");
        page.waitForLoadState();

        Locator errorMessageLocator = page.locator(".text-sm.text-red-600.flex.items-center.gap-2");
        errorMessageLocator.waitFor();

        String actualErrorMessage = errorMessageLocator.textContent().trim();
        String expectedErrorMessage = "You are not allowed to access this page.";

        AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch.");
    }
    
    @Test
    public void VerifyWrongOTPErrorMessage() {
        page.navigate("https://admin-core-development.realestateos.io/login");
        page.fill("#phone", "8378845340");
        page.click("//button[@type='submit']");
        page.waitForSelector("//input[@autocomplete='one-time-code']");
        page.fill("//input[@autocomplete='one-time-code']", "837884");
        page.click("//button[@type='submit']");
        Locator errorMessageLocator = page.locator("#swal2-title");
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        String actualErrorMessage = errorMessageLocator.textContent().trim();
        String expectedErrorMessage = "OTP is invalid.";
        AssertJUnit.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch.");
    }

    
	@AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}