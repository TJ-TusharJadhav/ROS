package tests.Add_Lead;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.CountryCodeMapper;
import utils.PhoneNumber;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)

public class AddProjectLeadTest extends BaseTest {
	public String phone;
	
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		ScreenshotUtil.capture(page, result, phone);
	}
	


    @DataProvider(name = "leadData")
    public Object[][] getLeadData() {
        return new Object[][] {
            {"Gift 4", "Aarav", "Sharma", "+91", "Event", "GIHED", "Interested in real estate"},
            {"Farmland", "Priya", "Khan", "+91", "Event", "Property Event", "Looking for investment"},
            {"Universal", "Rohan", "Patel", "+91", "Event", "Investor Meet", "Wants quick callback"},
            {"The Circle", "Simran", "Joshi", "+91", "Event", "Channel Partner Event", "Needs brochure"},
            
            // {"SPlus", "Karan", "Mehta", "+91", "Digital", "Website", "Prefers email communication"},
            // {"Curv", "Neha", "Desai", "+91", "Digital", "Facebook", "Requested site visit"},
            // {"Skyview", "Raj", "Verma", "+91", "Digital", "Instagram", "Interested in 2BHK"},
            // {"Greenview", "Isha", "Gupta", "+91", "Digital", "LinkedIn", "Budget under 50L"},
            // {"Harmony", "Arjun", "Mehta", "+91", "Digital", "SMS", "Asked for EMI options"},
            // {"Edge", "Priya", "Sharma", "+91", "Digital", "YouTube", "Wants luxury property"},
            // {"Parkview 2", "Rohan", "Desai", "+91", "Digital", "IVRS", "Interested in office space"},
            // {"Trophy", "Kavita", "Iyer", "+91", "Digital", "Google", "Student housing required"},
            // {"Park Lane", "Sameer", "Khan", "+91", "Digital", "Email", "Prefers WhatsApp updates"},
            // {"Crown", "Neha", "Kulkarni", "+91", "Digital", "WhatsApp", "Wants loan assistance"},
            
            // {"Wave", "Amit", "Gupta", "+91", "Walkin", "Website", "Interested in resale"},
            // {"Greenfield", "Sneha", "Reddy", "+91", "Walkin", "Facebook", "Family shifting soon"},
            // {"SPlus", "Vikram", "Joshi", "+91", "Walkin", "Instagram", "Needs 3BHK apartment"},
            // {"Curv", "Pooja", "Nair", "+91", "Walkin", "LinkedIn", "Asked for price list"},
            // {"Skyview", "Ankit", "Verma", "+91", "Walkin", "SMS", "Looking for villa"},
            // {"Greenview", "Deepika", "Rao", "+91", "Walkin", "YouTube", "Wants rental options"},
            // {"Harmony", "Raj", "Bansal", "+91", "Walkin", "IVRS", "Needs property documents"},
            // {"Edge", "Ayesha", "Siddiqui", "+91", "Walkin", "Google", "Interested in duplex"},
            // {"Trophy", "Karan", "Malhotra", "+91", "Walkin", "Email", "Wants farmhouse"},
            // {"Crown", "Meera", "Chawla", "+91", "Walkin", "WhatsApp", "Looking for commercial plot"},
            // {"Trophy", "Neha", "Desai", "+91", "Walkin", "Direct-Walkin", "Requires site visit"},
            
            // {"Harmony", "Aditya", "Patil", "+91", "Print Media", "News Paper", "Wants ready-to-move flat"},
            // {"Edge", "Ritu", "Jain", "+91", "Print Media", "Hoardings", "Interested in joint venture"},
            // {"Trophy", "Farhan", "Ansari", "+91", "Print Media", "Leaflets", "Wants coastal property"},
            
            // {"Crown", "Shruti", "Deshmukh", "+91", "Channel Sales", "Channel Partner", "Needs farmhouse land"},
            // {"Curv", "Manish", "Thakur", "+91", "Channel Sales", "ACP", "Wants studio apartment"},
        };
    }
    @DataProvider(name = "AdditionalleadData")
    public Object[][] getAdditionalleadData() {
        return new Object[][] {
            {"Trophy", "Arjun", "Mehta", "+91", "Event", "GIHED", "Interested in real estate", 
             "Edge", "+91", "CP", "Tushar Bhai", "Mumbai", "Immediate", "Hot", "Below 50 Lakh", "500 Sq.Ft", "Residential", "2 Bhk", "Buy"}, 
            
            {"SPlus", "Aarav", "Sharma", "+91", "Event", "GIHED", "Interested in real estate",
             "Harmony", "+91", "CP", "Tushar Bhai", "Delhi", "In 3 Months", "Cold", "50 Lakh to 1 Cr", "750 Sq.Ft", "Residential", "3 Bhk", "Invest"},
            
            {"Crown", "Priya", "Khan", "+91", "Event", "Property Event", "Looking for investment",
             "Trophy", "+91", "CP", "Tushar Bhai", "Bangalore", "In 6 Months", "Warm", "1 Cr to 2 Cr", "1000 Sq.Ft", "Residential", "4 Bhk", "Lease"},
            
            {"Trophy", "Rohan", "Patel", "+91", "Event", "Investor Meet", "Wants quick callback",
             "Edge", "+91", "CP", "Tushar Bhai", "Hyderabad", "In 1 Year +", "Hot", "2 Cr to 5 Cr", "1200 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
            
            {"Crown", "Simran", "Joshi", "+91", "Event", "Channel Partner Event", "Needs brochure",
             "Harmony", "+91", "CP", "Tushar Bhai", "Pune", "Immediate", "Cold", "5 Cr to 10 Cr", "1500 Sq.Ft", "Residential", "Weekend Villa", "Buy"},
            
            
            {"Edge", "Karan", "Mehta", "+91", "Digital", "Website", "Prefers email communication",
             "SPlus", "+91", "CP", "Tushar Bhai", "Chennai", "In 3 Months", "Warm", "10 Cr to 15 Cr", "1800 Sq.Ft", "Commercial", "Office", "Invest"},
            
            {"Crown", "Neha", "Desai", "+91", "Digital", "Facebook", "Requested site visit",
             "Edge", "+91", "CP", "Tushar Bhai", "Ahmedabad", "In 6 Months", "Hot", "Below 50 Lakh", "2000 Sq.Ft", "Commercial", "Showroom", "Lease"},
            
            {"Harmony", "Raj", "Verma", "+91", "Digital", "Instagram", "Interested in 2BHK",
             "SPlus", "+91", "CP", "Tushar Bhai", "Kolkata", "In 1 Year +", "Cold", "1 Cr to 2 Cr", "2200 Sq.Ft", "Commercial", "Shop", "Pre-Lease"},
            
            {"SPlus", "Isha", "Gupta", "+91", "Digital", "LinkedIn", "Budget under 50L",
             "Harmony", "+91", "CP", "Tushar Bhai", "Dubai", "Immediate", "Warm", "2 Cr to 5 Cr", "2500 Sq.Ft", "Residential", "2 Bhk", "Buy"},
            
            {"Crown", "Arjun", "Mehta", "+91", "Digital", "SMS", "Asked for EMI options",
             "Trophy", "+91", "CP", "Tushar Bhai", "Abu Dhabi", "In 3 Months", "Hot", "5 Cr to 10 Cr", "3000 Sq.Ft", "Residential", "3 Bhk", "Invest"},
            
            {"Edge", "Priya", "Sharma", "+91", "Digital", "YouTube", "Wants luxury property",
             "Crown", "+91", "CP", "Tushar Bhai", "Sharjah", "In 6 Months", "Cold", "10 Cr to 15 Cr", "3500 Sq.Ft", "Residential", "4 Bhk", "Lease"},
            
            {"Greenfield", "Rohan", "Desai", "+91", "Digital", "IVRS", "Interested in office space",
             "SPlus", "+91", "CP", "Tushar Bhai", "Singapore", "In 1 Year +", "Warm", "Below 50 Lakh", "4000 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
            
            {"SPlus", "Kavita", "Iyer", "+91", "Digital", "Google", "Student housing required",
             "Greenfield", "+91", "CP", "Tushar Bhai", "Kuala Lumpur", "Immediate", "Hot", "1 Cr to 2 Cr", "4500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            {"Edge", "Sameer", "Khan", "+91", "Digital", "Email", "Prefers WhatsApp updates",
             "Harmony", "+91", "CP", "Tushar Bhai", "Bangkok", "In 3 Months", "Cold", "2 Cr to 5 Cr", "5000 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"Harmony", "Neha", "Kulkarni", "+91", "Digital", "WhatsApp", "Wants loan assistance",
             "Edge", "+91", "CP", "Tushar Bhai", "Jakarta", "In 6 Months", "Warm", "5 Cr to 10 Cr", "5500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            {"Greenfield", "Amit", "Gupta", "+91", "Walkin", "Website", "Interested in resale",
             "Park Lane", "+91", "CP", "Tushar Bhai", "Bali", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "6000 Sq.Ft", "Commercial", "Shop", "Buy"},
            
            {"Park Lane", "Sneha", "Reddy", "+91", "Walkin", "Facebook", "Family shifting soon",
             "Greenfield", "+91", "CP", "Tushar Bhai", "London", "Immediate", "Cold", "Below 50 Lakh", "6500 Sq.Ft", "Residential", "2 Bhk", "Invest"},
            
            {"Parkview 2", "Vikram", "Joshi", "+91", "Walkin", "Instagram", "Needs 3BHK apartment",
             "Harmony", "+91", "CP", "Tushar Bhai", "Manchester", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "7000 Sq.Ft", "Residential", "3 Bhk", "Lease"},
            
            {"Harmony", "Pooja", "Nair", "+91", "Walkin", "LinkedIn", "Asked for price list",
             "Parkview 2", "+91", "CP", "Tushar Bhai", "Paris", "In 6 Months", "Hot", "1 Cr to 2 Cr", "7500 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
            
            {"Curv", "Ankit", "Verma", "+91", "Walkin", "SMS", "Looking for villa",
             "Farmland", "+91", "CP", "Tushar Bhai", "Berlin", "In 1 Year +", "Cold", "2 Cr to 5 Cr", "8000 Sq.Ft", "Residential", "5 Bhk", "Buy"},
            
            {"Farmland", "Deepika", "Rao", "+91", "Walkin", "YouTube", "Wants rental options",
             "Curv", "+91", "CP", "Tushar Bhai", "Frankfurt", "Immediate", "Warm", "5 Cr to 10 Cr", "8500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            {"The Circle", "Raj", "Bansal", "+91", "Walkin", "IVRS", "Needs property documents",
             "Universal", "+91", "CP", "Tushar Bhai", "Zurich", "In 3 Months", "Hot", "10 Cr to 15 Cr", "9000 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"Universal", "Ayesha", "Siddiqui", "+91", "Walkin", "Google", "Interested in duplex",
             "The Circle", "+91", "CP", "Tushar Bhai", "Geneva", "In 6 Months", "Cold", "Below 50 Lakh", "9500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            
            {"Gift 4", "Karan", "Malhotra", "+91", "Walkin", "Email", "Wants farmhouse",
             "Farmland", "+91", "CP", "Tushar Bhai", "New York", "In 1 Year +", "Warm", "50 Lakh to 1 Cr", "10000 Sq.Ft", "Commercial", "Shop", "Buy"},
            
            {"Farmland", "Meera", "Chawla", "+91", "Walkin", "WhatsApp", "Looking for commercial plot",
             "Gift 4", "+91", "CP", "Tushar Bhai", "Los Angeles", "Immediate", "Hot", "1 Cr to 2 Cr", "120 Sq.Ft", "Residential", "2 Bhk", "Invest"},
            
            {"The Circle", "Neha", "Desai", "+91", "Walkin", "Direct-Walkin", "Requires site visit",
             "Farmland", "+91", "CP", "Tushar Bhai", "San Francisco", "In 3 Months", "Cold", "2 Cr to 5 Cr", "150 Sq.Ft", "Residential", "3 Bhk", "Lease"},
            
            
            {"Farmland", "Aditya", "Patil", "+91", "Print Media", "News Paper", "Wants ready-to-move flat",
             "Curv", "+91", "CP", "Tushar Bhai", "Toronto", "In 6 Months", "Warm", "5 Cr to 10 Cr", "200 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
            
            {"Curv", "Ritu", "Jain", "+91", "Print Media", "Hoardings", "Interested in joint venture",
             "Farmland", "+91", "CP", "Tushar Bhai", "Vancouver", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "250 Sq.Ft", "Residential", "5 Bhk", "Buy"},
            
            {"Harmony", "Farhan", "Ansari", "+91", "Print Media", "Leaflets", "Wants coastal property",
             "Edge", "+91", "CP", "Tushar Bhai", "Sydney", "Immediate", "Cold", "Below 50 Lakh", "300 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
            
            
            {"Edge", "Shruti", "Deshmukh", "+91", "Channel Sales", "Channel Partner", "Needs farmhouse land",
             "Harmony", "+91", "CP", "Tushar Bhai", "Melbourne", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "350 Sq.Ft", "Commercial", "Office", "Lease"},
            
            {"The Circle", "Manish", "Thakur", "+91", "Channel Sales", "ACP", "Wants studio apartment",
             "Park Lane", "+91", "CP", "Tushar Bhai", "Tokyo", "In 6 Months", "Hot", "1 Cr to 2 Cr", "400 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
            };
    }

    
    @Test(dataProvider = "leadData", priority = 1)
    public void addLeadForBasicProjectLead(String project, String fname, String lname, String country, String source, String subSource, String remarks) throws InterruptedException {
    String countryCode = CountryCodeMapper.getCountryCode(country);

    // Generate phone number based on country code
    String phone = PhoneNumber.generateUniquePhoneNumber(countryCode);

    System.out.println("Fund lead Phone Number (" + countryCode + "): " + phone);

    String email = fname + lname + "@yopmail.com";
    addprojectLead.addLeadWithBasic(project, fname, lname, country, phone, email, source, subSource, remarks);
    addprojectLead.validateLeadWithBasicInfo(fname, lname, project, source, email, country, phone, remarks, subSource);
    }

    @Test(dataProvider = "AdditionalleadData", priority = 2)
    public void addLeadForAdditionalProjectLeadTest(
            String project, String fname, String lname, String country, String source, String subSource, String remarks,
            String secondproject, String addCountryCode, String referralType, String referralName, String location, String buyingTime,
            String priority, String budget, String area, String projectCat, String unitType, String leadType) throws InterruptedException {
       
        String countryCode = CountryCodeMapper.getCountryCode(country);

    // Generate phone number based on country code
    String phone = PhoneNumber.generateUniquePhoneNumber(countryCode);
    String additionalPhone = PhoneNumber.generateUniquePhoneNumber(countryCode);

    System.out.println("Fund lead Phone Number (" + countryCode + "): " + phone);

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
