package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.nio.file.Paths;
import java.util.Arrays;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import base.BaseTest;
import utils.TestUtils;

public class EditFundLeadTest extends BaseTest {
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
	    if (ITestResult.FAILURE == result.getStatus()) {
	        Object[] parameters = result.getParameters();
	        String testData = "";
	        if (parameters != null && parameters.length > 0) {
	            testData = "_" + String.join("_", 
	                Arrays.stream(parameters)
	                      .map(Object::toString)
	                      .toArray(String[]::new)
	            );
	        }
	        page.screenshot(new Page.ScreenshotOptions()
	                .setPath(Paths.get("screenshots/" + result.getName() + testData + "_failed.png"))
	                .setFullPage(true));

	        System.out.println("❌ Screenshot saved for failed test with data: " + Arrays.toString(parameters));
	    } else {
	        Locator closeBtn = page.locator("//button[contains(@class,'mantine-Modal-close')]");
	        if (closeBtn.isVisible()) {
	            closeBtn.click();
	            System.out.println("Modal close button clicked!");
	        } else {
	            System.out.println("Modal close button not found, skipping...");
	        }

	        page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
	        Thread.sleep(1000);
	    }
	}


    @DataProvider(name = "leadData")
    public Object[][] getLeadData() {
        return new Object[][] {
            {"Universal", "Aarav", "Sharma", "Interested in real estate"},
        };
        }
    
        @DataProvider(name = "AdditionalleadData")
        public Object[][] getAdditionalleadData() {
            return new Object[][] {
            	{"Universal", "Arjun", "Mehta", "Interested in real estate", 
                 "+33", "CP", "Tushar Bhai", "Mumbai", "Immediate", "Hot", "Below 50 Lakh", "500 Sq.Ft", "Residential", "2 Bhk", "Buy"}, 
                 };
    }

    @Test(dataProvider = "leadData")
    public void addLeadForBasicFundLead(String project, String fname, String lname, String remarks) throws InterruptedException {
    String email = fname + lname + "@yopmail.com";
    editfundLead.editLeadWithBasic(project, fname, lname, email, remarks);
    editfundLead.validateLeadWithBasicInfo(fname, lname, project, email, remarks);
    }
    
    @Test(dataProvider = "AdditionalleadData")
    public void addLeadForAdditionalFundLeadTest(
            String project, String fname, String lname,  String remarks,
             String addCountryCode, String referralType, String referralName, String location, String buyingTime,
            String priority, String budget, String area, String projectCat, String unitType, String leadType) throws InterruptedException {
       
        String additionalPhone = TestUtils.generateUniquePhoneNumber();
        String email = fname + lname + "@yopmail.com";

        editfundLead.editLeadWithAdditional(
            project, fname, lname, email, remarks,
             addCountryCode, additionalPhone, referralType, referralName,
            location, buyingTime, priority, budget, area, projectCat, unitType, leadType
        );
        editfundLead.validateLeadWithAdditionalInfo(fname, lname, project, email,  remarks, 
        		addCountryCode, additionalPhone, referralType, referralName,
                buyingTime, priority, budget, area,
                projectCat, unitType, leadType, location);
        
    }

}
