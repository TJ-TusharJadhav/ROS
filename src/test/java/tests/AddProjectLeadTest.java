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
public class AddProjectLeadTest extends BaseTest {
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
            {"New Project 60", "Aarav", "Sharma", "+93", "Event", "GIHED", "Interested in real estate"},
            {"New Project 60", "Priya", "Khan", "+20", "Event", "Property Event", "Looking for investment"},
            {"New Project 60", "Rohan", "Patel", "+27", "Event", "Investor Meet", "Wants quick callback"},
            {"New Project 60", "Simran", "Joshi", "+30", "Event", "Channel Partner Event", "Needs brochure"},
            
//            {"New Project 60", "Karan", "Mehta", "+31", "Digital", "Website", "Prefers email communication"},
//            {"New Project 60", "Neha", "Desai", "+32", "Digital", "Facebook", "Requested site visit"},
//            {"New Project 60", "Raj", "Verma", "+33", "Digital", "Instagram", "Interested in 2BHK"},
//            {"New Project 60", "Isha", "Gupta", "+34", "Digital", "LinkedIn", "Budget under 50L"},
//            {"New Project 60", "Arjun", "Mehta", "+49", "Digital", "SMS", "Asked for EMI options"},
//            {"New Project 60", "Priya", "Sharma", "+52", "Digital", "YouTube", "Wants luxury property"},
//            {"New Project 60", "Rohan", "Desai", "+55", "Digital", "IVRS", "Interested in office space"},
//            {"New Project 60", "Kavita", "Iyer", "+62", "Digital", "Google", "Student housing required"},
//            {"New Project 60", "Sameer", "Khan", "+63", "Digital", "Email", "Prefers WhatsApp updates"},
//            {"New Project 60", "Neha", "Kulkarni", "+64", "Digital", "WhatsApp", "Wants loan assistance"},
//            
//            {"New Project 60", "Amit", "Gupta", "+66", "Walkin", "Website", "Interested in resale"},
//            {"New Project 60", "Sneha", "Reddy", "+81", "Walkin", "Facebook", "Family shifting soon"},
//            {"New Project 60", "Vikram", "Joshi", "+82", "Walkin", "Instagram", "Needs 3BHK apartment"},
//            {"New Project 60", "Pooja", "Nair", "+86", "Walkin", "LinkedIn", "Asked for price list"},
//            {"New Project 60", "Ankit", "Verma", "+90", "Walkin", "SMS", "Looking for villa"},
//            {"New Project 60", "Deepika", "Rao", "+91", "Walkin", "YouTube", "Wants rental options"},
//            {"New Project 60", "Raj", "Bansal", "+92", "Walkin", "IVRS", "Needs property documents"},
//            {"New Project 60", "Ayesha", "Siddiqui", "+93", "Walkin", "Google", "Interested in duplex"},
//            {"New Project 60", "Karan", "Malhotra", "+94", "Walkin", "Email", "Wants farmhouse"},
//            {"New Project 60", "Meera", "Chawla", "+95", "Walkin", "WhatsApp", "Looking for commercial plot"},
//            {"New Project 60", "Neha", "Desai", "+98", "Walkin", "Direct-Walkin", "Requires site visit"},
            
//            {"New Project 60", "Aditya", "Patil", "+211", "Print Media", "News Paper", "Wants ready-to-move flat"},
//            {"New Project 60", "Ritu", "Jain", "+213", "Print Media", "Hoardings", "Interested in joint venture"},
//            {"New Project 60", "Farhan", "Ansari", "+216", "Print Media", "Leaflets", "Wants coastal property"},
//            
//            {"New Project 60", "Shruti", "Deshmukh", "+65", "Channel Sales", "Channel Partner", "Needs farmhouse land"},
//            {"New Project 60", "Manish", "Thakur", "+91", "Channel Sales", "ACP", "Wants studio apartment"},
        };
    }
    @DataProvider(name = "AdditionalleadData")
    public Object[][] getAdditionalleadData() {
        return new Object[][] {
            // Format: { project, fname, lname, country, source, subSource, remarks }
//        	 Event Source
            {"New Project 60", "Arjun", "Mehta", "+234", "Event", "GIHED", "Interested in real estate", 
             "New Project 65", "+33", "CP", "Tushar Bhai", "Mumbai", "Immediate", "Hot", "Below 50 Lakh", "500 Sq.Ft", "Residential", "2 Bhk", "Buy"}, 
            
            {"New Project 60", "Aarav", "Sharma", "+93", "Event", "GIHED", "Interested in real estate",
             "New Project 65", "+36", "CP", "Tushar Bhai", "Delhi", "In 3 Months", "Cold", "50 Lakh to 1 Cr", "750 Sq.Ft", "Residential", "3 Bhk", "Invest"},
            
            {"New Project 60", "Priya", "Khan", "+20", "Event", "Property Event", "Looking for investment",
             "New Project 65", "+40", "CP", "Tushar Bhai", "Bangalore", "In 6 Months", "Warm", "1 Cr to 2 Cr", "1000 Sq.Ft", "Residential", "4 Bhk", "Lease"},
            
            {"New Project 60", "Rohan", "Patel", "+27", "Event", "Investor Meet", "Wants quick callback",
             "New Project 65", "+41", "CP", "Tushar Bhai", "Hyderabad", "In 1 Year +", "Hot", "2 Cr to 5 Cr", "1200 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
            
            {"New Project 60", "Simran", "Joshi", "+30", "Event", "Channel Partner Event", "Needs brochure",
             "New Project 65", "+43", "CP", "Tushar Bhai", "Pune", "Immediate", "Cold", "5 Cr to 10 Cr", "1500 Sq.Ft", "Residential", "Weekend Villa", "Buy"},
            
            
            {"New Project 60", "Karan", "Mehta", "+31", "Digital", "Website", "Prefers email communication",
             "New Project 65", "+45", "CP", "Tushar Bhai", "Chennai", "In 3 Months", "Warm", "10 Cr to 15 Cr", "1800 Sq.Ft", "Commercial", "Office", "Invest"},
            
            {"New Project 60", "Neha", "Desai", "+32", "Digital", "Facebook", "Requested site visit",
             "New Project 65", "+46", "CP", "Tushar Bhai", "Ahmedabad", "In 6 Months", "Hot", "Below 50 Lakh", "2000 Sq.Ft", "Commercial", "Showroom", "Lease"},
            
            {"New Project 60", "Raj", "Verma", "+33", "Digital", "Instagram", "Interested in 2BHK",
             "New Project 65", "+420", "CP", "Tushar Bhai", "Kolkata", "In 1 Year +", "Cold", "1 Cr to 2 Cr", "2200 Sq.Ft", "Commercial", "Shop", "Pre-Lease"},
            
            {"New Project 60", "Isha", "Gupta", "+34", "Digital", "LinkedIn", "Budget under 50L",
             "New Project 65", "+48", "CP", "Tushar Bhai", "Dubai", "Immediate", "Warm", "2 Cr to 5 Cr", "2500 Sq.Ft", "Residential", "2 Bhk", "Buy"},
            
            {"New Project 60", "Arjun", "Mehta", "+49", "Digital", "SMS", "Asked for EMI options",
             "New Project 65", "+51", "CP", "Tushar Bhai", "Abu Dhabi", "In 3 Months", "Hot", "5 Cr to 10 Cr", "3000 Sq.Ft", "Residential", "3 Bhk", "Invest"},
            
            {"New Project 60", "Priya", "Sharma", "+52", "Digital", "YouTube", "Wants luxury property",
             "New Project 65", "+53", "CP", "Tushar Bhai", "Sharjah", "In 6 Months", "Cold", "10 Cr to 15 Cr", "3500 Sq.Ft", "Residential", "4 Bhk", "Lease"},
            
            {"New Project 60", "Rohan", "Desai", "+55", "Digital", "IVRS", "Interested in office space",
             "New Project 65", "+54", "CP", "Tushar Bhai", "Singapore", "In 1 Year +", "Warm", "Below 50 Lakh", "4000 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
            
            {"New Project 60", "Kavita", "Iyer", "+62", "Digital", "Google", "Student housing required",
             "New Project 65", "+56", "CP", "Tushar Bhai", "Kuala Lumpur", "Immediate", "Hot", "1 Cr to 2 Cr", "4500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            {"New Project 60", "Sameer", "Khan", "+63", "Digital", "Email", "Prefers WhatsApp updates",
             "New Project 65", "+57", "CP", "Tushar Bhai", "Bangkok", "In 3 Months", "Cold", "2 Cr to 5 Cr", "5000 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"New Project 60", "Neha", "Kulkarni", "+64", "Digital", "WhatsApp", "Wants loan assistance",
             "New Project 65", "+58", "CP", "Tushar Bhai", "Jakarta", "In 6 Months", "Warm", "5 Cr to 10 Cr", "5500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            {"New Project 60", "Amit", "Gupta", "+66", "Walkin", "Website", "Interested in resale",
             "New Project 65", "+81", "CP", "Tushar Bhai", "Bali", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "6000 Sq.Ft", "Commercial", "Shop", "Buy"},
            
            {"New Project 60", "Sneha", "Reddy", "+81", "Walkin", "Facebook", "Family shifting soon",
             "New Project 65", "+82", "CP", "Tushar Bhai", "London", "Immediate", "Cold", "Below 50 Lakh", "6500 Sq.Ft", "Residential", "2 Bhk", "Invest"},
            
            {"New Project 60", "Vikram", "Joshi", "+82", "Walkin", "Instagram", "Needs 3BHK apartment",
             "New Project 65", "+94", "CP", "Tushar Bhai", "Manchester", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "7000 Sq.Ft", "Residential", "3 Bhk", "Lease"},
            
            {"New Project 60", "Pooja", "Nair", "+86", "Walkin", "LinkedIn", "Asked for price list",
             "New Project 65", "+351", "CP", "Tushar Bhai", "Paris", "In 6 Months", "Hot", "1 Cr to 2 Cr", "7500 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
            
            {"New Project 60", "Ankit", "Verma", "+90", "Walkin", "SMS", "Looking for villa",
             "New Project 65", "+352", "CP", "Tushar Bhai", "Berlin", "In 1 Year +", "Cold", "2 Cr to 5 Cr", "8000 Sq.Ft", "Residential", "5 Bhk", "Buy"},
            
            {"New Project 60", "Deepika", "Rao", "+91", "Walkin", "YouTube", "Wants rental options",
             "New Project 65", "+353", "CP", "Tushar Bhai", "Frankfurt", "Immediate", "Warm", "5 Cr to 10 Cr", "8500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            {"New Project 60", "Raj", "Bansal", "+92", "Walkin", "IVRS", "Needs property documents",
             "New Project 65", "+354", "CP", "Tushar Bhai", "Zurich", "In 3 Months", "Hot", "10 Cr to 15 Cr", "9000 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"New Project 60", "Ayesha", "Siddiqui", "+93", "Walkin", "Google", "Interested in duplex",
             "New Project 65", "+355", "CP", "Tushar Bhai", "Geneva", "In 6 Months", "Cold", "Below 50 Lakh", "9500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            {"New Project 60", "Karan", "Malhotra", "+94", "Walkin", "Email", "Wants farmhouse",
             "New Project 65", "+356", "CP", "Tushar Bhai", "New York", "In 1 Year +", "Warm", "50 Lakh to 1 Cr", "10000 Sq.Ft", "Commercial", "Shop", "Buy"},
            
            {"New Project 60", "Meera", "Chawla", "+95", "Walkin", "WhatsApp", "Looking for commercial plot",
             "New Project 65", "+357", "CP", "Tushar Bhai", "Los Angeles", "Immediate", "Hot", "1 Cr to 2 Cr", "120 Sq.Ft", "Residential", "2 Bhk", "Invest"},
            
            {"New Project 60", "Neha", "Desai", "+98", "Walkin", "Direct-Walkin", "Requires site visit",
             "New Project 65", "+421", "CP", "Tushar Bhai", "San Francisco", "In 3 Months", "Cold", "2 Cr to 5 Cr", "150 Sq.Ft", "Residential", "3 Bhk", "Lease"},
            
            
//            {"New Project 60", "Aditya", "Patil", "+211", "Print Media", "News Paper", "Wants ready-to-move flat",
//             "New Project 65", "+359", "CP", "Tushar Bhai", "Toronto", "In 6 Months", "Warm", "5 Cr to 10 Cr", "200 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
//            
//            {"New Project 60", "Ritu", "Jain", "+213", "Print Media", "Hoardings", "Interested in joint venture",
//             "New Project 65", "+380", "CP", "Tushar Bhai", "Vancouver", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "250 Sq.Ft", "Residential", "5 Bhk", "Buy"},
//            
//            {"New Project 60", "Farhan", "Ansari", "+216", "Print Media", "Leaflets", "Wants coastal property",
//             "New Project 65", "+385", "CP", "Tushar Bhai", "Sydney", "Immediate", "Cold", "Below 50 Lakh", "300 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
//            
//            
//            {"New Project 60", "Shruti", "Deshmukh", "+65", "Channel Sales", "Channel Partner", "Needs farmhouse land",
//             "New Project 65", "+386", "CP", "Tushar Bhai", "Melbourne", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "350 Sq.Ft", "Commercial", "Office", "Lease"},
//            
//            {"New Project 60", "Manish", "Thakur", "+91", "Channel Sales", "ACP", "Wants studio apartment",
//             "New Project 65", "+387", "CP", "Tushar Bhai", "Tokyo", "In 6 Months", "Hot", "1 Cr to 2 Cr", "400 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            
            
         };
    }

    @Test(dataProvider = "leadData")
    public void addLeadForBasicProjectLead(String project, String fname, String lname, String country, String source, String subSource, String remarks) throws InterruptedException {
    String phone = TestUtils.generateUniquePhoneNumber();
    String email = fname + lname + "@yopmail.com";
    Thread.sleep(1000);
    addprojectLead.addLeadWithBasic(project, fname, lname, country, phone, email, source, subSource, remarks);
    addprojectLead.validateLeadWithBasicInfo(fname, lname, project, source, email, country, phone, remarks, subSource);
    }

    @Test(dataProvider = "AdditionalleadData")
    public void addLeadForAdditionalProjectLeadTest(
            String project, String fname, String lname, String country, String source, String subSource, String remarks,
            String secondproject, String addCountryCode, String referralType, String referralName, String location, String buyingTime,
            String priority, String budget, String area, String projectCat, String unitType, String leadType) throws InterruptedException {
       
        String phone = TestUtils.generateUniquePhoneNumber();
        String additionalPhone = TestUtils.generateUniquePhoneNumber();
        String email = fname + lname + "@yopmail.com";

        addprojectLead.addLeadWithAdditional(
            project, fname, lname, country, phone, email, source, subSource, remarks,
            secondproject, addCountryCode, additionalPhone, referralType, referralName,
            location, buyingTime, priority, budget, area, projectCat, unitType, leadType
        );

        addprojectLead.validateLeadWithAdditionalInfo(
            fname, lname, project, source, email, country, phone, remarks, subSource, 
            addCountryCode, additionalPhone, referralType, referralName,
            buyingTime, priority, budget, area,
            projectCat, unitType, leadType, location, secondproject
        );
    }

}
