package playwright;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class GetCountryNameList {
    static Playwright playwright;
	static Browser browser;
    BrowserContext mobileContext;
    Page page;

    @BeforeClass
    public void setUpMobileEmulation() throws InterruptedException{
    	 playwright = Playwright.create();
         browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
         mobileContext = browser.newContext();
         page = mobileContext.newPage();
         String URL= "https://admin-core-development.realestateos.io/login";
        
        page.navigate(URL);
        page.fill("#phone", "8378845340");
        page.click("//button[@type='submit']");
        Thread.sleep(20000);
    }
    @Test
public void verifyNonRegisterMobileNumberErrorMessageOnMobile() throws InterruptedException {
    page.getByText("Add User").click();
    Thread.sleep(1000);
    page.click("//div[@class='country-dropdown-toggle ']");
    Thread.sleep(1000);

    String countrySelector = "//div[@class='country-dropdown-menu']";
    Locator countryOptions = page.locator(countrySelector);
    List<String> countryNames = countryOptions.allTextContents();

    System.out.println("--- All Country Names ---");

    for (String block : countryNames) {
        // Split on '+' because each country entry starts with '+'
        String[] entries = block.split("\\+");
        for (String entry : entries) {
            entry = entry.trim();
            if (entry.isEmpty()) continue;

            // The first part is country code, followed by the name
            // Example: "376 Andorra"
            String[] parts = entry.split(" ", 2);
            if (parts.length == 2) {
                String code = "+" + parts[0].trim();
                String name = parts[1].trim();
                System.out.printf("%-25s %s%n", code, name); // aligned columns
            } else {
                System.out.println(entry);
            }
        }
    }

    System.out.println("-------------------------");
}


    }
    

