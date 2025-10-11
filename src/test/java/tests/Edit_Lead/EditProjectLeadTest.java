package tests.Edit_Lead;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.PhoneNumber;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)
public class EditProjectLeadTest extends BaseTest {
	
	
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}


    @DataProvider(name = "leadData")
    public Object[][] getLeadData() {
        return new Object[][] {
            {"Farmland", "Aarav", "Sharma", "Interested in real estate"},
            {"The Circle", "Roshan", "Jadhav", "Looking for investment in 5 BHK"},
            {"SPlus", "Rohan", "Patel",  "Wants quick callback"},
            {"Curv", "Simran", "Joshi",  "Needs brochure"},
            
            {"Farmland", "Karan", "Mehta", "Prefers email communication"},
            {"The Circle", "Neha", "Desai", "Requested site visit"},
            {"SPlus", "Raj", "Verma", "Interested in 2BHK"},
            {"Curv", "Isha", "Gupta", "Budget under 50L"},
            {"Skyview", "Arjun", "Mehta",  "Asked for EMI options"},
            
            {"Farmland", "Amit", "Gupta", "Interested in resale"},
            {"The Circle", "Sneha", "Reddy", "Family shifting soon"},
            {"SPlus", "Vikram", "Joshi", "Needs 3BHK apartment"},
            {"Curv", "Pooja", "Nair", "Asked for price list"},
            {"Skyview", "Ankit", "Verma", "Looking for villa"},
            {"Crown", "Deepika", "Rao", "Wants rental options"},
            {"Parkview 2", "Raj", "Bansal", "Needs property documents"},
            {"Harmony", "Ayesha", "Siddiqui", "Interested in duplex"},
            {"Crown", "Karan", "Malhotra", "Wants farmhouse"},
            {"Skyview", "Meera", "Chawla", "Looking for commercial plot"},
            {"Curv", "Neha", "Desai", "Requires site visit"},
            
            {"Farmland", "Aditya", "Patil", "Wants ready-to-move flat"},
            {"The Circle", "Ritu", "Jain", "Interested in joint venture"},
            {"SPlus", "Farhan", "Ansari", "Wants coastal property"},
            
            {"Farmland", "Shruti", "Deshmukh", "Needs farmhouse land"},
            {"The Circle", "Manish", "Thakur", "Wants studio apartment"},
        };
    }
    @DataProvider(name = "AdditionalleadData")
    public Object[][] getAdditionalleadData() {
        return new Object[][] {
              {"Harmony", "Arjun", "Mehta", "Edit: Interested in real estate", 
              "Universal", "+33", "CP", "Tushar Bhai", "Mumbai", "Immediate", "Hot", "Below 50 Lakh", "500 Sq.Ft", "Residential", "2 Bhk", "Buy"}, 
            
            {"Universal", "Aarav", "Sharma", "Interested in real estate",
             "Harmony", "+36", "CP", "Tushar Bhai", "Delhi", "In 3 Months", "Cold", "50 Lakh to 1 Cr", "750 Sq.Ft", "Residential", "3 Bhk", "Invest"},
            
            {"Farmland", "Priya", "Khan", "Looking for investment",
             "Gift 4", "+40", "CP", "Tushar Bhai", "Bangalore", "In 6 Months", "Warm", "1 Cr to 2 Cr", "1000 Sq.Ft", "Residential", "4 Bhk", "Lease"},
            
            {"Gift 4", "Rohan", "Patel", "Wants quick callback",
             "Farmland", "+41", "CP", "Tushar Bhai", "Hyderabad", "In 1 Year +", "Hot", "2 Cr to 5 Cr", "1200 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
            
            {"SPlus", "Simran", "Joshi", "Needs brochure",
             "Skyview", "+43", "CP", "Tushar Bhai", "Pune", "Immediate", "Cold", "5 Cr to 10 Cr", "1500 Sq.Ft", "Residential", "Weekend Villa", "Buy"},
            
            
            {"Skyview", "Karan", "Mehta", "Prefers email communication",
             "SPlus", "+45", "CP", "Tushar Bhai", "Chennai", "In 3 Months", "Warm", "10 Cr to 15 Cr", "1800 Sq.Ft", "Commercial", "Office", "Invest"},
            
            {"Greenview", "Neha", "Desai", "Requested site visit",
             "Harmony", "+46", "CP", "Tushar Bhai", "Ahmedabad", "In 6 Months", "Hot", "Below 50 Lakh", "2000 Sq.Ft", "Commercial", "Showroom", "Lease"},
            
            {"Harmony", "Raj", "Verma", "Interested in 2BHK",
             "Greenview", "+420", "CP", "Tushar Bhai", "Kolkata", "In 1 Year +", "Cold", "1 Cr to 2 Cr", "2200 Sq.Ft", "Commercial", "Shop", "Pre-Lease"},
            
            {"Edge", "Isha", "Gupta", "Budget under 50L",
             "Parkview 2", "+48", "CP", "Tushar Bhai", "Dubai", "Immediate", "Warm", "2 Cr to 5 Cr", "2500 Sq.Ft", "Residential", "2 Bhk", "Buy"},
            
            {"Parkview 2", "Arjun", "Mehta", "Asked for EMI options",
             "Edge", "+51", "CP", "Tushar Bhai", "Abu Dhabi", "In 3 Months", "Hot", "5 Cr to 10 Cr", "3000 Sq.Ft", "Residential", "3 Bhk", "Invest"},
            
            {"Park Lane", "Priya", "Sharma", "Wants luxury property",
             "Wave", "+53", "CP", "Tushar Bhai", "Sharjah", "In 6 Months", "Cold", "10 Cr to 15 Cr", "3500 Sq.Ft", "Residential", "4 Bhk", "Lease"},
            
            {"Wave", "Rohan", "Desai", "Interested in office space",
             "Park Lane", "+54", "CP", "Tushar Bhai", "Singapore", "In 1 Year +", "Warm", "Below 50 Lakh", "4000 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
            
            {"Farmland", "Kavita", "Iyer", "Student housing required",
             "The Circle", "+56", "CP", "Tushar Bhai", "Kuala Lumpur", "Immediate", "Hot", "1 Cr to 2 Cr", "4500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            {"The Circle", "Sameer", "Khan", "Prefers WhatsApp updates",
             "Farmland", "+57", "CP", "Tushar Bhai", "Bangkok", "In 3 Months", "Cold", "2 Cr to 5 Cr", "5000 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"Park Lane", "Neha", "Kulkarni", "Wants loan assistance",
             "Curv", "+58", "CP", "Tushar Bhai", "Jakarta", "In 6 Months", "Warm", "5 Cr to 10 Cr", "5500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            {"Curv", "Amit", "Gupta", "Interested in resale",
             "Park Lane", "+81", "CP", "Tushar Bhai", "Bali", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "6000 Sq.Ft", "Commercial", "Shop", "Buy"},
            
            {"The Circle", "Sneha", "Reddy", "Family shifting soon",
             "Farmland", "+82", "CP", "Tushar Bhai", "London", "Immediate", "Cold", "Below 50 Lakh", "6500 Sq.Ft", "Residential", "2 Bhk", "Invest"},
            
            {"Farmland", "Vikram", "Joshi", "Needs 3BHK apartment",
             "The Circle", "+94", "CP", "Tushar Bhai", "Manchester", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "7000 Sq.Ft", "Residential", "3 Bhk", "Lease"},
            
            {"SPlus", "Pooja", "Nair", "Asked for price list",
             "Universal", "+351", "CP", "Tushar Bhai", "Paris", "In 6 Months", "Hot", "1 Cr to 2 Cr", "7500 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
            
            {"Universal", "Ankit", "Verma", "Looking for villa",
             "SPlus", "+352", "CP", "Tushar Bhai", "Berlin", "In 1 Year +", "Cold", "2 Cr to 5 Cr", "8000 Sq.Ft", "Residential", "5 Bhk", "Buy"},
            
            {"Curv", "Deepika", "Rao", "Wants rental options",
             "Park Lane", "+353", "CP", "Tushar Bhai", "Frankfurt", "Immediate", "Warm", "5 Cr to 10 Cr", "8500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            {"Park Lane", "Raj", "Bansal", "Needs property documents",
             "Curv", "+354", "CP", "Tushar Bhai", "Zurich", "In 3 Months", "Hot", "10 Cr to 15 Cr", "9000 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"Crown", "Ayesha", "Siddiqui", "Interested in duplex",
             "Farmland", "+355", "CP", "Tushar Bhai", "Geneva", "In 6 Months", "Cold", "Below 50 Lakh", "9500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            {"Farmland", "Karan", "Malhotra", "Wants farmhouse",
             "Crown", "+356", "CP", "Tushar Bhai", "New York", "In 1 Year +", "Warm", "50 Lakh to 1 Cr", "10000 Sq.Ft", "Commercial", "Shop", "Buy"},
            
            {"Park Lane", "Meera", "Chawla", "Looking for commercial plot",
             "Wave", "+357", "CP", "Tushar Bhai", "Los Angeles", "Immediate", "Hot", "1 Cr to 2 Cr", "120 Sq.Ft", "Residential", "2 Bhk", "Invest"},
            
            {"Wave", "Neha", "Desai", "Requires site visit",
             "Park Lane", "+421", "CP", "Tushar Bhai", "San Francisco", "In 3 Months", "Cold", "2 Cr to 5 Cr", "150 Sq.Ft", "Residential", "3 Bhk", "Lease"},
            
            
            {"Farmland", "Aditya", "Patil", "Wants ready-to-move flat",
             "SPlus", "+359", "CP", "Tushar Bhai", "Toronto", "In 6 Months", "Warm", "5 Cr to 10 Cr", "200 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
            
            {"SPlus", "Ritu", "Jain", "Interested in joint venture",
             "Farmland", "+380", "CP", "Tushar Bhai", "Vancouver", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "250 Sq.Ft", "Residential", "5 Bhk", "Buy"},
            
            {"Gift 4", "Farhan", "Ansari", "Wants coastal property",
             "Universal", "+385", "CP", "Tushar Bhai", "Sydney", "Immediate", "Cold", "Below 50 Lakh", "300 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            
            {"Universal", "Shruti", "Deshmukh", "Needs farmhouse land",
             "Gift 4", "+386", "CP", "Tushar Bhai", "Melbourne", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "350 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"The Circle", "Manish", "Thakur", "Wants studio apartment",
             "Farmland", "+387", "CP", "Tushar Bhai", "Tokyo", "In 6 Months", "Hot", "1 Cr to 2 Cr", "400 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            
            
         };
    }

    @Test(dataProvider = "leadData",priority = 1)
    public void addLeadForBasicProjectLead(String project, String fname, String lname, String remarks) throws InterruptedException {
    
    String email = fname + lname + "@yopmail.com";
    editProjectLead.EditLeadWithBasic(project, fname, lname, email, remarks);
    editProjectLead.validateLeadWithBasicInfo(fname, lname, project, email, remarks);
    }

    @Test(dataProvider = "AdditionalleadData",priority = 2)
    public void addLeadForAdditionalProjectLeadTest(
            String project, String fname, String lname, String remarks,
            String secondproject, String addCountryCode, String referralType, String referralName, String location, String buyingTime,
            String priority, String budget, String area, String projectCat, String unitType, String leadType) throws InterruptedException {
        
    	String additionalPhone = PhoneNumber.generateUniquePhoneNumber(addCountryCode);
        String email = fname + lname + "@yopmail.com";

        editProjectLead.EditLeadWithAdditional(
            project, fname, lname, email, remarks,
            secondproject, addCountryCode, additionalPhone, referralType, referralName,
            location, buyingTime, priority, budget, area, projectCat, unitType, leadType
        );

        editProjectLead.validateLeadWithAdditionalInfo(
            fname, lname, project, email, remarks,  
            addCountryCode, additionalPhone, referralType, referralName,
            buyingTime, priority, budget, area,
            projectCat, unitType, leadType, location, secondproject
        );
    }

}
