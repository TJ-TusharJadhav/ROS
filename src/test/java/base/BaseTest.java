package base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import pages.*;
import utils.ConfigManager;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    // AuthState per environment
    private final String environment = ConfigManager.getEnvironment();
    private final String storagePath = "auth-" + environment + ".json";

    private final String loginUrl = ConfigManager.getLoginUrl();
    private final String protectedUrl = ConfigManager.getProtectedUrl();

    // Page Objects
    protected AddFundLeadPage addfundLead;
    protected AddCPLeadPage addCPLead;
    protected AddProjectLeadPage addprojectLead;
    protected LoginPage login;
    protected EditProjectLeadPage editProjectLead;
    protected EditFundLeadPage editfundLead;
    protected EditFurnitureLeadPage editFurnitureLead;
    protected ProjectFollowUpPage projectFollowUp;
    protected FurnitureFollowUpsPage FurnitureFollowUps;
    protected FundFollowUpsPage FundFollowUps;
    protected AddLandLeadPage addLandLead;
    protected AddFurnitureLeadPage addFurnitureLead;
    protected Faker faker;

    @BeforeSuite
    public void setup() throws InterruptedException, IOException {
        System.out.println("Running tests on environment: " + environment);

        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext context;

        if (Files.exists(Paths.get(storagePath))) {
            // Reuse saved auth state
            Browser.NewContextOptions options = new Browser.NewContextOptions()
                    .setStorageStatePath(Paths.get(storagePath));
            context = browser.newContext(options);
            System.out.println("Loaded saved auth state from " + storagePath);
        } else {
            // No auth state → manual login required
            context = browser.newContext();
            page = context.newPage();
            page.navigate(loginUrl);

            System.out.println("Please complete login manually (enter OTP if prompted).");
            System.out.println("Press ENTER here once login is successful...");
            System.in.read();  // wait for user

            // Navigate to protected page to confirm login
            page.navigate(protectedUrl);
            page.waitForLoadState();

            // Save AuthState for future runs
            context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get(storagePath)));
            System.out.println("✅ Saved new auth state to " + storagePath);
        }

        // Open a new page in the authenticated context
        page = context.newPage();
        page.navigate(protectedUrl);

        // Initialize page objects
        login = new LoginPage(page);
        addfundLead = new AddFundLeadPage(page);
        addCPLead = new AddCPLeadPage(page);
        addprojectLead = new AddProjectLeadPage(page);
        editProjectLead = new EditProjectLeadPage(page);
        editfundLead = new EditFundLeadPage(page);
        editFurnitureLead = new EditFurnitureLeadPage(page);
        projectFollowUp = new ProjectFollowUpPage(page);
        FurnitureFollowUps = new FurnitureFollowUpsPage(page);
        FundFollowUps = new FundFollowUpsPage(page);
        addLandLead = new AddLandLeadPage(page);
        addFurnitureLead = new AddFurnitureLeadPage(page);
        faker = new Faker();
    }

    @AfterSuite
    public void tearDown() {
        browser.close();
        playwright.close();
        
    }
}
