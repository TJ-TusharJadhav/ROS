package playwright;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;

public class EventRegistrationTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void setupClass() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) // set true false
        );
    }

    @AfterClass
    public void tearDownClass() {
        browser.close();
        playwright.close();
    }

    
	@BeforeMethod
    public void setUp() {
        context = browser.newContext();
        page = context.newPage();
    }

    
	@AfterMethod
    public void tearDown() {
        context.close();
    }

    // DataProvider with 1000 sets of data
    @DataProvider(name = "registrationData")
    public Object[][] getData() {
        Object[][] data = new Object[100][4]; // 1000 rows, 4 fields

        for (int i = 0; i < 100; i++) {
            data[i][0] = "FirstName" + i;      // firstName
            data[i][1] = "LastName" + i;       // lastName
            data[i][2] = "user" + i + "@yopmail.com"; // email
            data[i][3] = "987654" + String.format("%04d", i); // phone
        }
        return data;
    }

    @Test(dataProvider = "registrationData")
    public void eventRegistration(String firstName, String lastName, String email, String phone) {
        // Open page
        page.navigate("https://staging.shivalikgroup.com/event/registration/68a55c36b65795eada58eada");

        // Handle cookie popup if appears
        if (page.locator("text=Yes, I Accept All Cookies").isVisible()) {
            page.locator("text=Yes, I Accept All Cookies").click();
        }

        // Fill form
        page.fill("#firstName", firstName);
        page.fill("#lastName", lastName);
        page.fill("#email", email);
        page.fill("#phoneNumber", phone);

        // Select dropdown
        page.selectOption("#identitySelection", new SelectOption().setLabel("Land Owner"));

        // Submit form
        page.click("button[type='submit']");

        // Optional small wait for submission (Playwright auto-waits but you can add buffer)
//        page.waitForTimeout(500);
    }
}
