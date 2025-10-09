package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;

import listeners.ExtentTestNGListener;
import pages.*;
import utils.ConfigManager;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    private final String storagePath = "auth.json";
    private final String loginUrl = ConfigManager.getLoginUrl();
    private final String protectedUrl = ConfigManager.getProtectedUrl();
    private final String environment = ConfigManager.getEnvironment();

    protected AddFundLeadPage addfundLead;
    protected AddProjectLeadPage addprojectLead;
    protected LoginPage login;
    protected EditProjectLeadPage editProjectLead;
    protected EditFundLeadPage editfundLead;
    protected ProjectFollowUpPage projectFollowUp;
    protected FurnitureFollowUpsPage FurnitureFollowUps;
    protected FundFollowUpsPage FundFollowUps;
    protected AddLandLeadPage addLandLead;
    protected AddFurnitureLeadPage addFurnitureLead;
    protected Faker faker;

    @BeforeSuite
    public void setup() throws InterruptedException, IOException {
        System.out.println("🔧 Running tests on environment: " + environment);
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext context;

        if (Files.exists(Paths.get(storagePath))) {
            Browser.NewContextOptions options = new Browser.NewContextOptions()
                    .setStorageStatePath(Paths.get(storagePath));
            context = browser.newContext(options);
            System.out.println("✅ Loaded saved auth state from " + storagePath);
        } else {
            context = browser.newContext();
            page = context.newPage();
            page.navigate(loginUrl);
            System.out.println("Please complete login manually. Press ENTER when done...");
            System.in.read();

            page.navigate(protectedUrl);
            page.waitForLoadState();

            context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get(storagePath)));
            System.out.println("✅ Saved new auth state to " + storagePath);
        }

        page = context.newPage();
        page.navigate(protectedUrl);

        login = new LoginPage(page);
        addfundLead = new AddFundLeadPage(page);
        addprojectLead = new AddProjectLeadPage(page);
        editProjectLead = new EditProjectLeadPage(page);
        editfundLead = new EditFundLeadPage(page);
        projectFollowUp = new ProjectFollowUpPage(page);
        FurnitureFollowUps = new FurnitureFollowUpsPage(page);
        FundFollowUps = new FundFollowUpsPage(page);
        addLandLead = new AddLandLeadPage(page);
        addFurnitureLead = new AddFurnitureLeadPage(page);
        faker = new Faker();
        ExtentTestNGListener.page = page;
    }

    @AfterSuite
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}
