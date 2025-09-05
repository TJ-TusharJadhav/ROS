package base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import pages.AddFundLeadPage;
import pages.AddProjectLeadPage;
import pages.LoginPage;
import utils.AllureListener;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
 // Page objects available to all test classes
    protected AddFundLeadPage addfundLead;
    protected AddProjectLeadPage addprojectLead;


    @BeforeClass
    public void setup() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://admin-core-development.realestateos.io/login");
        LoginPage login = new LoginPage(page);
        login.loginWithOTP("8378845340");
//        page.navigate("https://admin-core-development.realestateos.io/follow-up");
//        Thread.sleep(1000);
        
        
     // Instantiate Page Objects
        addfundLead = new AddFundLeadPage(page);
        addprojectLead = new AddProjectLeadPage(page);
        AllureListener.setPage(page);
        
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
