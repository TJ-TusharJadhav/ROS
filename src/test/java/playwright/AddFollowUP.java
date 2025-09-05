package playwright;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddFollowUP {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    public void login() {
        page.navigate("https://admin-core-development.realestateos.io/login");

        // Enter phone number
        page.fill("#phone", "8378845340");
        page.click("//button[@type='submit']");

        // Wait for Verify button
        Locator verifyButton = page.locator("button:has-text('Verify')");
        verifyButton.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));

        // Click Verify
        verifyButton.click();
    }

    @Test
    public void followUP() throws InterruptedException {
        login();
        page.click("(//p[@data-size='sm'])[4]");
        page.click("//p[contains(text(), 'Mobile no')]");
        page.click("button:has-text('Add Lead Follow up')");
        page.click("button:has-text('New Inquiry')");
        page.click("button:has-text('Discussion')");
        page.click("//td[@data-date='2025-09-03']");
        page.click("//input[@type='time']");
        
        Thread.sleep(5000);



    }

    @AfterClass
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
