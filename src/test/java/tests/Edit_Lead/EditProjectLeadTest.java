package tests.Edit_Lead;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.PhoneNumber;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)
public class EditProjectLeadTest extends BaseTest {
public String phone;

	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.captureforCRM(page, result, phone);
		}


    @DataProvider(name = "leadData")
    public Object[][] getLeadData() {
        return new Object[][] {
            {"Gift 4", "Aarav", "Sharma", "Interested in real estate"},
            {"Farmland", "Roshan", "Jadhav", "Looking for investment in 5 BHK"},
            {"Universal", "Rohan", "Patel",  "Wants quick callback"},
            {"The Circle", "Simran", "Joshi",  "Needs brochure"},
            
            {"Farmland", "Karan", "Mehta", "Prefers email communication"},
            {"The Circle", "Neha", "Desai", "Requested site visit"},
            {"Universal", "Raj", "Verma", "Interested in 2BHK"},
            {"Farmland", "Isha", "Gupta", "Budget under 50L"},
            {"Gift 4", "Arjun", "Mehta",  "Asked for EMI options"},
            
            {"Farmland", "Amit", "Gupta", "Interested in resale"},
            {"The Circle", "Sneha", "Reddy", "Family shifting soon"},
            {"Farmland", "Vikram", "Joshi", "Needs 3BHK apartment"},
            {"Farmland", "Pooja", "Nair", "Asked for price list"},
            {"Farmland", "Ankit", "Verma", "Looking for villa"},
            {"Farmland", "Deepika", "Rao", "Wants rental options"},
            {"Universal", "Raj", "Bansal", "Needs property documents"},
            {"Universal", "Ayesha", "Siddiqui", "Interested in duplex"},
            {"Universal", "Karan", "Malhotra", "Wants farmhouse"},
            {"Universal", "Meera", "Chawla", "Looking for commercial plot"},
            {"Farmland", "Neha", "Desai", "Requires site visit"},
            
            {"Farmland", "Aditya", "Patil", "Wants ready-to-move flat"},
            {"The Circle", "Ritu", "Jain", "Interested in joint venture"},
            {"Farmland", "Farhan", "Ansari", "Wants coastal property"},
            
            {"Farmland", "Shruti", "Deshmukh", "Needs farmhouse land"},
            {"The Circle", "Manish", "Thakur", "Wants studio apartment"},
        };
    }
    @DataProvider(name = "AdditionalleadData")
    public Object[][] getAdditionalleadData() {
        return new Object[][] {
{"Gift 4", "Arjun", "Mehta", "Edit: Interested in real estate",
"Farmland", "+91", "CP", "Tushar Bhai", "Mumbai", "Immediate", "Hot", "Below 50 Lakh", "500 Sq.Ft", "Residential", "2 Bhk", "Buy"},

{"Universal", "Aarav", "Sharma", "Interested in real estate",
"The Circle", "+91", "CP", "Tushar Bhai", "Delhi", "In 3 Months", "Cold", "50 Lakh to 1 Cr", "750 Sq.Ft", "Residential", "3 Bhk", "Invest"},

{"Gift 4", "Priya", "Khan", "Looking for investment",
"Farmland", "+91", "CP", "Tushar Bhai", "Bangalore", "In 6 Months", "Warm", "1 Cr to 2 Cr", "1000 Sq.Ft", "Residential", "4 Bhk", "Lease"},

{"Universal", "Rohan", "Patel", "Wants quick callback",
"The Circle", "+91", "CP", "Tushar Bhai", "Hyderabad", "In 1 Year +", "Hot", "2 Cr to 5 Cr", "1200 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},

{"Gift 4", "Simran", "Joshi", "Needs brochure",
"Farmland", "+91", "CP", "Tushar Bhai", "Pune", "Immediate", "Cold", "5 Cr to 10 Cr", "1500 Sq.Ft", "Residential", "Weekend Villa", "Buy"},

{"Universal", "Karan", "Mehta", "Prefers email communication",
"The Circle", "+91", "CP", "Tushar Bhai", "Chennai", "In 3 Months", "Warm", "10 Cr to 15 Cr", "1800 Sq.Ft", "Commercial", "Office", "Invest"},

{"Gift 4", "Neha", "Desai", "Requested site visit",
"Farmland", "+91", "CP", "Tushar Bhai", "Ahmedabad", "In 6 Months", "Hot", "Below 50 Lakh", "2000 Sq.Ft", "Commercial", "Showroom", "Lease"},

{"Universal", "Raj", "Verma", "Interested in 2BHK",
"The Circle", "+91", "CP", "Tushar Bhai", "Kolkata", "In 1 Year +", "Cold", "1 Cr to 2 Cr", "2200 Sq.Ft", "Commercial", "Shop", "Pre-Lease"},

{"Gift 4", "Isha", "Gupta", "Budget under 50L",
"Farmland", "+91", "CP", "Tushar Bhai", "Dubai", "Immediate", "Warm", "2 Cr to 5 Cr", "2500 Sq.Ft", "Residential", "2 Bhk", "Buy"},

{"Universal", "Arjun", "Mehta", "Asked for EMI options",
"The Circle", "+91", "CP", "Tushar Bhai", "Abu Dhabi", "In 3 Months", "Hot", "5 Cr to 10 Cr", "3000 Sq.Ft", "Residential", "3 Bhk", "Invest"},

{"Gift 4", "Priya", "Sharma", "Wants luxury property",
"Farmland", "+91", "CP", "Tushar Bhai", "Sharjah", "In 6 Months", "Cold", "10 Cr to 15 Cr", "3500 Sq.Ft", "Residential", "4 Bhk", "Lease"},

{"Universal", "Rohan", "Desai", "Interested in office space",
"The Circle", "+91", "CP", "Tushar Bhai", "Singapore", "In 1 Year +", "Warm", "Below 50 Lakh", "4000 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},

{"Gift 4", "Kavita", "Iyer", "Student housing required",
"Farmland", "+91", "CP", "Tushar Bhai", "Kuala Lumpur", "Immediate", "Hot", "1 Cr to 2 Cr", "4500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},

{"Universal", "Sameer", "Khan", "Prefers WhatsApp updates",
"The Circle", "+91", "CP", "Tushar Bhai", "Bangkok", "In 3 Months", "Cold", "2 Cr to 5 Cr", "5000 Sq.Ft", "Commercial", "Office", "Lease"},

{"Gift 4", "Neha", "Kulkarni", "Wants loan assistance",
"Farmland", "+91", "CP", "Tushar Bhai", "Jakarta", "In 6 Months", "Warm", "5 Cr to 10 Cr", "5500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},

{"Universal", "Amit", "Gupta", "Interested in resale",
"The Circle", "+91", "CP", "Tushar Bhai", "Bali", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "6000 Sq.Ft", "Commercial", "Shop", "Buy"},

{"Gift 4", "Sneha", "Reddy", "Family shifting soon",
"Farmland", "+91", "CP", "Tushar Bhai", "London", "Immediate", "Cold", "Below 50 Lakh", "6500 Sq.Ft", "Residential", "2 Bhk", "Invest"},

{"Universal", "Vikram", "Joshi", "Needs 3BHK apartment",
"The Circle", "+91", "CP", "Tushar Bhai", "Manchester", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "7000 Sq.Ft", "Residential", "3 Bhk", "Lease"},

{"Gift 4", "Pooja", "Nair", "Asked for price list",
"Farmland", "+91", "CP", "Tushar Bhai", "Paris", "In 6 Months", "Hot", "1 Cr to 2 Cr", "7500 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},

{"Universal", "Ankit", "Verma", "Looking for villa",
"The Circle", "+91", "CP", "Tushar Bhai", "Berlin", "In 1 Year +", "Cold", "2 Cr to 5 Cr", "8000 Sq.Ft", "Residential", "5 Bhk", "Buy"},

{"Gift 4", "Deepika", "Rao", "Wants rental options",
"Farmland", "+91", "CP", "Tushar Bhai", "Frankfurt", "Immediate", "Warm", "5 Cr to 10 Cr", "8500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},

{"Universal", "Raj", "Bansal", "Needs property documents",
"The Circle", "+91", "CP", "Tushar Bhai", "Zurich", "In 3 Months", "Hot", "10 Cr to 15 Cr", "9000 Sq.Ft", "Commercial", "Office", "Lease"},

{"Gift 4", "Ayesha", "Siddiqui", "Interested in duplex",
"Farmland", "+91", "CP", "Tushar Bhai", "Geneva", "In 6 Months", "Cold", "Below 50 Lakh", "9500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},

{"Universal", "Karan", "Malhotra", "Wants farmhouse",
"The Circle", "+91", "CP", "Tushar Bhai", "New York", "In 1 Year +", "Warm", "50 Lakh to 1 Cr", "10000 Sq.Ft", "Commercial", "Shop", "Buy"},

{"Gift 4", "Meera", "Chawla", "Looking for commercial plot",
"Farmland", "+91", "CP", "Tushar Bhai", "Los Angeles", "Immediate", "Hot", "1 Cr to 2 Cr", "120 Sq.Ft", "Residential", "2 Bhk", "Invest"},

{"Universal", "Neha", "Desai", "Requires site visit",
"The Circle", "+91", "CP", "Tushar Bhai", "San Francisco", "In 3 Months", "Cold", "2 Cr to 5 Cr", "150 Sq.Ft", "Residential", "3 Bhk", "Lease"},

{"Gift 4", "Aditya", "Patil", "Wants ready-to-move flat",
"Farmland", "+91", "CP", "Tushar Bhai", "Toronto", "In 6 Months", "Warm", "5 Cr to 10 Cr", "200 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},

{"Universal", "Ritu", "Jain", "Interested in joint venture",
"The Circle", "+91", "CP", "Tushar Bhai", "Vancouver", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "250 Sq.Ft", "Residential", "5 Bhk", "Buy"},

{"Gift 4", "Farhan", "Ansari", "Wants coastal property",
"Farmland", "+91", "CP", "Tushar Bhai", "Sydney", "Immediate", "Cold", "Below 50 Lakh", "300 Sq.Ft", "Residential", "Weekend Villa", "Invest"},

{"Universal", "Shruti", "Deshmukh", "Needs farmhouse land",
"The Circle", "+91", "CP", "Tushar Bhai", "Melbourne", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "350 Sq.Ft", "Commercial", "Office", "Lease"},

{"Gift 4", "Manish", "Thakur", "Wants studio apartment",
"Farmland", "+91", "CP", "Tushar Bhai", "Tokyo", "In 6 Months", "Hot", "1 Cr to 2 Cr", "400 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            
         };
    }

    @Test(dataProvider = "leadData",priority = 1,retryAnalyzer = RetryAnalyzer.class)
    public void EditLeadForBasicProjectLead(String project, String fname, String lname, String remarks) throws InterruptedException {
    
    String email = fname + lname + "@yopmail.com";
    editProjectLead.EditLeadWithBasic(project, fname, lname, email, remarks);
    editProjectLead.validateLeadWithBasicInfo(fname, lname, project, email, remarks);
    }

    @Test(dataProvider = "AdditionalleadData",priority = 2,retryAnalyzer = RetryAnalyzer.class)
    public void EditLeadForAdditionalProjectLeadTest(
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