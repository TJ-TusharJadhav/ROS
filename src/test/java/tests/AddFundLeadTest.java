package tests;

import java.nio.file.Paths;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import base.BaseTest;
import utils.AllureListener;
import utils.TestUtils;

@Listeners(AllureListener.class)
public class AddFundLeadTest extends BaseTest {
	@AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        if (ITestResult.FAILURE == result.getStatus()) {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/" + result.getName() + "_failed.png"))
                    .setFullPage(true));
        } else {
	    	Locator closeBtn = page.locator("//button[contains(@class,'mantine-Modal-close')]");
	    	if (closeBtn.isVisible()) {
	    	    closeBtn.click();
	    	    System.out.println("Modal close button clicked!");
	    	} else {
	    	    System.out.println("Modal close button not found, skipping...");
	    	}

//	        Thread.sleep(1000);
	        page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");

	        Thread.sleep(1000);
        }
        }

    @DataProvider(name = "leadData")
    public Object[][] getLeadData() {
        return new Object[][] {
            // Format: { project, fname, lname, country, source, subSource, remarks }
            {"Fund", "Aarav", "Sharma", "+93", "Event", "GIHED", "Interested in real estate"},
            {"Fund", "Priya", "Khan", "+20", "Event", "Property Event", "Looking for investment"},
            {"Fund", "Rohan", "Patel", "+27", "Event", "Investor Meet", "Wants quick callback"},
            {"Fund", "Simran", "Joshi", "+30", "Event", "Channel Partner Event", "Needs brochure"},
            
            {"Fund", "Karan", "Mehta", "+31", "Digital", "Website", "Prefers email communication"},
            {"Fund", "Neha", "Desai", "+32", "Digital", "Facebook", "Requested site visit"},
            {"Fund", "Raj", "Verma", "+33", "Digital", "Instagram", "Interested in 2BHK"},
            {"Fund", "Isha", "Gupta", "+34", "Digital", "LinkedIn", "Budget under 50L"},
            {"Fund", "Arjun", "Mehta", "+49", "Digital", "SMS", "Asked for EMI options"},
            {"Fund", "Priya", "Sharma", "+52", "Digital", "YouTube", "Wants luxury property"},
            {"Fund", "Rohan", "Desai", "+55", "Digital", "IVRS", "Interested in office space"},
            {"Fund", "Kavita", "Iyer", "+62", "Digital", "Google", "Student housing required"},
            {"Fund", "Sameer", "Khan", "+63", "Digital", "Email", "Prefers WhatsApp updates"},
            {"Fund", "Neha", "Kulkarni", "+64", "Digital", "WhatsApp", "Wants loan assistance"},
            
            {"Fund", "Amit", "Gupta", "+66", "Walkin", "Website", "Interested in resale"},
            {"Fund", "Sneha", "Reddy", "+81", "Walkin", "Facebook", "Family shifting soon"},
            {"Fund", "Vikram", "Joshi", "+82", "Walkin", "Instagram", "Needs 3BHK apartment"},
            {"Fund", "Pooja", "Nair", "+86", "Walkin", "LinkedIn", "Asked for price list"},
            {"Fund", "Ankit", "Verma", "+90", "Walkin", "SMS", "Looking for villa"},
            {"Fund", "Deepika", "Rao", "+91", "Walkin", "YouTube", "Wants rental options"},
            {"Fund", "Raj", "Bansal", "+92", "Walkin", "IVRS", "Needs property documents"},
            {"Fund", "Ayesha", "Siddiqui", "+93", "Walkin", "Google", "Interested in duplex"},
            {"Fund", "Karan", "Malhotra", "+94", "Walkin", "Email", "Wants farmhouse"},
            {"Fund", "Meera", "Chawla", "+95", "Walkin", "WhatsApp", "Looking for commercial plot"},
            {"Fund", "Neha", "Desai", "+98", "Walkin", "Direct-Walkin", "Requires site visit"},
            
            {"Fund", "Aditya", "Patil", "+211", "Print Media", "News Paper", "Wants ready-to-move flat"},
            {"Fund", "Ritu", "Jain", "+213", "Print Media", "Hoardings", "Interested in joint venture"},
            {"Fund", "Farhan", "Ansari", "+216", "Print Media", "Leaflets", "Wants coastal property"},
            
            {"Fund", "Shruti", "Deshmukh", "+65", "Channel Sales", "Channel Partner", "Needs farmhouse land"},
            {"Fund", "Manish", "Thakur", "+91", "Channel Sales", "ACP", "Wants studio apartment"},
        };
    }

    @Test(dataProvider = "leadData")
    public void addLeadForFund(String project, String fname, String lname, String country, String source, String subSource, String remarks) throws InterruptedException {
    String phone = TestUtils.generateUniquePhoneNumber();
    String email = fname + lname + "@yopmail.com";
    Thread.sleep(1000);
    addfundLead.addLead(project, fname, lname, country, phone, email, source, subSource, remarks);
    addfundLead.validateLead(fname, lname, project, source);
    addfundLead.validateLeadWithOtherDetails(email, country, phone, remarks, source, subSource);
    }

}
