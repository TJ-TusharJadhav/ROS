package playwright;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Login {
	static Playwright playwright;
	static Browser browser;
    BrowserContext mobileContext;
    Page page;

    @BeforeClass
    public void setUpMobileEmulation() {
    	 playwright = Playwright.create();
         browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
         mobileContext = browser.newContext();
         page = mobileContext.newPage();
    }
    @Test
    public void verifyNonRegisterMobileNumberErrorMessageOnMobile() throws InterruptedException {
        String URL= "https://admin-core-development.realestateos.io/login";
        
//        page.context().clearCookies();
//        page.evaluate("() => { localStorage.clear(); sessionStorage.clear(); }");
        
        page.navigate(URL);
        page.fill("#phone", "8378845340");
        page.click("//button[@type='submit']");
        Thread.sleep(50000);
    }


}
