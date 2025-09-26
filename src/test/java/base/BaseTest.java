package base;

import org.testng.annotations.AfterMethod;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import pages.AddFundLeadPage;
import pages.AddFurnitureLeadPage;
import pages.AddLandLeadPage;
import pages.AddProjectLeadPage;
import pages.EditFundLeadPage;
import pages.EditProjectLeadPage;
import pages.LoginPage;
import pages.ProjectFollowUpPage;
import utils.AllureListener;
import utils.RetryListener;

//@Listeners(RetryListener.class)
public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    // Page objects available to all test classes
    protected AddFundLeadPage addfundLead;
    protected AddProjectLeadPage addprojectLead;
    protected LoginPage login;
    protected EditProjectLeadPage editProjectLead;
    protected EditFundLeadPage editfundLead;
    protected ProjectFollowUpPage projectFollowUp;
    protected AddLandLeadPage addLandLead;
    protected AddFurnitureLeadPage addFurnitureLead;
    protected Faker faker;

    private final String storagePath = "auth.json";
    private final String devUrl = "https://admin-core-staging.realestateos.io/login";
    private final String protectedUrl = "https://admin-core-staging.realestateos.io/users";

    @BeforeSuite
    public void setup() throws InterruptedException, IOException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext context;

        //  Check if auth.json already exists
        if (Files.exists(Paths.get(storagePath))) {
            // Reuse saved session
            Browser.NewContextOptions options = new Browser.NewContextOptions()
                    .setStorageStatePath(Paths.get(storagePath));
            context = browser.newContext(options);
            System.out.println("==> Loaded saved auth state from " + storagePath);
        } else {
            // No storage state, perform login manually & save session
            context = browser.newContext();
            page = context.newPage();
            page.navigate(devUrl);

            System.out.println("==> Please complete login manually (with OTP).");
            System.out.println("==> After login, you will be redirected. Press ENTER in console to continue...");
            System.in.read();

            // Verify login works by visiting a protected page
            page.navigate(protectedUrl);
            page.waitForLoadState();

            // Save storage state for future test runs
            context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get(storagePath)));
            System.out.println("==> Saved new auth state to " + storagePath);
        }

        // Open page with authenticated context
        page = context.newPage();
        page.navigate(protectedUrl);

        // Instantiate Page Objects
        login = new LoginPage(page);
        addfundLead = new AddFundLeadPage(page);
        addprojectLead = new AddProjectLeadPage(page);
        editProjectLead = new EditProjectLeadPage(page);
        editfundLead = new EditFundLeadPage(page);
        projectFollowUp = new ProjectFollowUpPage(page);
        addLandLead = new AddLandLeadPage(page);
        addFurnitureLead= new AddFurnitureLeadPage(page);
        AllureListener.setPage(page);
        faker = new Faker();
    }

	@AfterSuite
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}