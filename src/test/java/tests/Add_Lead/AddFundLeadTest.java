package tests.Add_Lead;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.PhoneNumber;
import utils.ScreenshotUtil;


@Listeners(listeners.ExtentTestNGListener.class)
public class AddFundLeadTest extends BaseTest {
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}
	


    @DataProvider(name = "leadData")
    public Object[][] getLeadData() {
        return new Object[][] {
            {"The Pre-Launch Fund", "Aarav", "Sharma", "+93", "Event", "GIHED", "Interested in real estate"},
            // {"Universal", "Priya", "Khan", "+20", "Event", "Property Event", "Looking for investment"},
            // {"The Pre-Launch Fund", "Rohan", "Patel", "+27", "Event", "Investor Meet", "Wants quick callback"},
            // {"Universal", "Simran", "Joshi", "+30", "Event", "Channel Partner Event", "Needs brochure"},
            
            // {"The Pre-Launch Fund", "Karan", "Mehta", "+31", "Digital", "Website", "Prefers email communication"},
            // {"Universal", "Neha", "Desai", "+32", "Digital", "Facebook", "Requested site visit"},
            // {"The Pre-Launch Fund", "Raj", "Verma", "+33", "Digital", "Instagram", "Interested in 2BHK"},
            // {"Universal", "Isha", "Gupta", "+34", "Digital", "LinkedIn", "Budget under 50L"},
            // {"The Pre-Launch Fund", "Arjun", "Mehta", "+49", "Digital", "SMS", "Asked for EMI options"},
            // {"Universal", "Priya", "Sharma", "+52", "Digital", "YouTube", "Wants luxury property"},
            // {"The Pre-Launch Fund", "Rohan", "Desai", "+55", "Digital", "IVRS", "Interested in office space"},
            // {"Universal", "Kavita", "Iyer", "+62", "Digital", "Google", "Student housing required"},
            // {"The Pre-Launch Fund", "Sameer", "Khan", "+63", "Digital", "Email", "Prefers WhatsApp updates"},
            // {"Universal", "Neha", "Kulkarni", "+64", "Digital", "WhatsApp", "Wants loan assistance"},
            
            // {"The Pre-Launch Fund", "Amit", "Gupta", "+66", "Walkin", "Website", "Interested in resale"},
            // {"Universal", "Sneha", "Reddy", "+81", "Walkin", "Facebook", "Family shifting soon"},
            // {"The Pre-Launch Fund", "Vikram", "Joshi", "+82", "Walkin", "Instagram", "Needs 3BHK apartment"},
            // {"Universal", "Pooja", "Nair", "+86", "Walkin", "LinkedIn", "Asked for price list"},
            // {"The Pre-Launch Fund", "Ankit", "Verma", "+90", "Walkin", "SMS", "Looking for villa"},
            // {"Universal", "Deepika", "Rao", "+91", "Walkin", "YouTube", "Wants rental options"},
            // {"The Pre-Launch Fund", "Raj", "Bansal", "+92", "Walkin", "IVRS", "Needs property documents"},
            // {"Universal", "Ayesha", "Siddiqui", "+93", "Walkin", "Google", "Interested in duplex"},
            // {"The Pre-Launch Fund", "Karan", "Malhotra", "+94", "Walkin", "Email", "Wants farmhouse"},
            // {"Universal", "Meera", "Chawla", "+95", "Walkin", "WhatsApp", "Looking for commercial plot"},
            // {"The Pre-Launch Fund", "Neha", "Desai", "+98", "Walkin", "Direct-Walkin", "Requires site visit"},
            
            // {"Universal", "Aditya", "Patil", "+211", "Print Media", "News Paper", "Wants ready-to-move flat"},
            // {"The Pre-Launch Fund", "Ritu", "Jain", "+213", "Print Media", "Hoardings", "Interested in joint venture"},
            // {"Universal", "Farhan", "Ansari", "+216", "Print Media", "Leaflets", "Wants coastal property"},
            
            // {"The Pre-Launch Fund", "Shruti", "Deshmukh", "+65", "Channel Sales", "Channel Partner", "Needs farmhouse land"},
            // {"Universal", "Manish", "Thakur", "+91", "Channel Sales", "ACP", "Wants studio apartment"},
        };
        }
    
        @DataProvider(name = "AdditionalleadData")
        public Object[][] getAdditionalleadData() {
            return new Object[][] {
            	{"The Pre-Launch Fund", "Arjun", "Mehta", "+234", "Event", "GIHED", "Interested in real estate", 
                 "+33", "CP", "Tushar Bhai", "Mumbai", "Immediate", "Hot", "Below 50 Lakh", "500 Sq.Ft", "Residential", "2 Bhk", "Buy"}, 
                   
            	{"Universal", "Aarav", "Sharma", "+93", "Event", "GIHED", "Interested in real estate",
                 "+36", "CP", "Tushar Bhai", "Delhi", "In 3 Months", "Cold", "50 Lakh to 1 Cr", "750 Sq.Ft", "Residential", "3 Bhk", "Invest"},
                    
                 {"The Pre-Launch Fund", "Priya", "Khan", "+20", "Event", "Property Event", "Looking for investment",
                 "+40", "CP", "Tushar Bhai", "Bangalore", "In 6 Months", "Warm", "1 Cr to 2 Cr", "1000 Sq.Ft", "Residential", "4 Bhk", "Lease"},
                    
                 {"Universal", "Rohan", "Patel", "+27", "Event", "Investor Meet", "Wants quick callback",
                  "+41", "CP", "Tushar Bhai", "Hyderabad", "In 1 Year +", "Hot", "2 Cr to 5 Cr", "1200 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
                    
                 {"The Pre-Launch Fund", "Simran", "Joshi", "+30", "Event", "Channel Partner Event", "Needs brochure",
                  "+43", "CP", "Tushar Bhai", "Pune", "Immediate", "Cold", "5 Cr to 10 Cr", "1500 Sq.Ft", "Residential", "Weekend Villa", "Buy"},
                    
                 {"Universal", "Karan", "Mehta", "+31", "Digital", "Website", "Prefers email communication",
                  "+45", "CP", "Tushar Bhai", "Chennai", "In 3 Months", "Warm", "10 Cr to 15 Cr", "1800 Sq.Ft", "Commercial", "Office", "Invest"},
                    
                  {"The Pre-Launch Fund", "Neha", "Desai", "+32", "Digital", "Facebook", "Requested site visit",
                   "+46", "CP", "Tushar Bhai", "Ahmedabad", "In 6 Months", "Hot", "Below 50 Lakh", "2000 Sq.Ft", "Commercial", "Showroom", "Lease"},
                    
                  {"Universal", "Raj", "Verma", "+33", "Digital", "Instagram", "Interested in 2BHK",
                   "+420", "CP", "Tushar Bhai", "Kolkata", "In 1 Year +", "Cold", "1 Cr to 2 Cr", "2200 Sq.Ft", "Commercial", "Shop", "Pre-Lease"},
                    
                     {"The Pre-Launch Fund", "Isha", "Gupta", "+34", "Digital", "LinkedIn", "Budget under 50L",
                      "+48", "CP", "Tushar Bhai", "Dubai", "Immediate", "Warm", "2 Cr to 5 Cr", "2500 Sq.Ft", "Residential", "2 Bhk", "Buy"},
                    
                     {"Universal", "Arjun", "Mehta", "+49", "Digital", "SMS", "Asked for EMI options",
                      "+51", "CP", "Tushar Bhai", "Abu Dhabi", "In 3 Months", "Hot", "5 Cr to 10 Cr", "3000 Sq.Ft", "Residential", "3 Bhk", "Invest"},
                    
                     {"The Pre-Launch Fund", "Priya", "Sharma", "+52", "Digital", "YouTube", "Wants luxury property",
                       "+53", "CP", "Tushar Bhai", "Sharjah", "In 6 Months", "Cold", "10 Cr to 15 Cr", "3500 Sq.Ft", "Residential", "4 Bhk", "Lease"},
                    
                     {"Universal", "Rohan", "Desai", "+55", "Digital", "IVRS", "Interested in office space",
                       "+54", "CP", "Tushar Bhai", "Singapore", "In 1 Year +", "Warm", "Below 50 Lakh", "4000 Sq.Ft", "Residential", "5 Bhk", "Pre-Lease"},
                    
                     {"The Pre-Launch Fund", "Kavita", "Iyer", "+62", "Digital", "Google", "Student housing required",
                       "+56", "CP", "Tushar Bhai", "Kuala Lumpur", "Immediate", "Hot", "1 Cr to 2 Cr", "4500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
                    
                     {"Universal", "Sameer", "Khan", "+63", "Digital", "Email", "Prefers WhatsApp updates",
                       "+57", "CP", "Tushar Bhai", "Bangkok", "In 3 Months", "Cold", "2 Cr to 5 Cr", "5000 Sq.Ft", "Commercial", "Office", "Lease"},
                    
                     {"The Pre-Launch Fund", "Neha", "Kulkarni", "+64", "Digital", "WhatsApp", "Wants loan assistance",
                       "+58", "CP", "Tushar Bhai", "Jakarta", "In 6 Months", "Warm", "5 Cr to 10 Cr", "5500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
                    
                     {"Universal", "Amit", "Gupta", "+66", "Walkin", "Website", "Interested in resale",
                       "+81", "CP", "Tushar Bhai", "Bali", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "6000 Sq.Ft", "Commercial", "Shop", "Buy"},
                    
                     {"The Pre-Launch Fund", "Sneha", "Reddy", "+81", "Walkin", "Facebook", "Family shifting soon",
                       "+82", "CP", "Tushar Bhai", "London", "Immediate", "Cold", "Below 50 Lakh", "6500 Sq.Ft", "Residential", "2 Bhk", "Invest"},
                    
                     {"Universal", "Vikram", "Joshi", "+82", "Walkin", "Instagram", "Needs 3BHK apartment",
                       "+94", "CP", "Tushar Bhai", "Manchester", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "7000 Sq.Ft", "Residential", "3 Bhk", "Lease"},
                    
                     {"The Pre-Launch Fund", "Pooja", "Nair", "+86", "Walkin", "LinkedIn", "Asked for price list",
                       "+351", "CP", "Tushar Bhai", "Paris", "In 6 Months", "Hot", "1 Cr to 2 Cr", "7500 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
                    
                     {"Universal", "Ankit", "Verma", "+90", "Walkin", "SMS", "Looking for villa",
                       "+352", "CP", "Tushar Bhai", "Berlin", "In 1 Year +", "Cold", "2 Cr to 5 Cr", "8000 Sq.Ft", "Residential", "5 Bhk", "Buy"},
                    
                     {"The Pre-Launch Fund", "Deepika", "Rao", "+91", "Walkin", "YouTube", "Wants rental options",
                       "+353", "CP", "Tushar Bhai", "Frankfurt", "Immediate", "Warm", "5 Cr to 10 Cr", "8500 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
                    
                     {"Universal", "Raj", "Bansal", "+92", "Walkin", "IVRS", "Needs property documents",
                       "+354", "CP", "Tushar Bhai", "Zurich", "In 3 Months", "Hot", "10 Cr to 15 Cr", "9000 Sq.Ft", "Commercial", "Office", "Lease"},
                    
                     {"The Pre-Launch Fund", "Ayesha", "Siddiqui", "+93", "Walkin", "Google", "Interested in duplex",
                       "+355", "CP", "Tushar Bhai", "Geneva", "In 6 Months", "Cold", "Below 50 Lakh", "9500 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
                    
                     {"Universal", "Karan", "Malhotra", "+94", "Walkin", "Email", "Wants farmhouse",
                       "+356", "CP", "Tushar Bhai", "New York", "In 1 Year +", "Warm", "50 Lakh to 1 Cr", "10000 Sq.Ft", "Commercial", "Shop", "Buy"},
                    
                     {"The Pre-Launch Fund", "Meera", "Chawla", "+95", "Walkin", "WhatsApp", "Looking for commercial plot",
                       "+357", "CP", "Tushar Bhai", "Los Angeles", "Immediate", "Hot", "1 Cr to 2 Cr", "120 Sq.Ft", "Residential", "2 Bhk", "Invest"},
                    
                     {"Universal", "Neha", "Desai", "+98", "Walkin", "Direct-Walkin", "Requires site visit",
                       "+421", "CP", "Tushar Bhai", "San Francisco", "In 3 Months", "Cold", "2 Cr to 5 Cr", "150 Sq.Ft", "Residential", "3 Bhk", "Lease"},
                   
                     {"The Pre-Launch Fund", "Aditya", "Patil", "+211", "Print Media", "News Paper", "Wants ready-to-move flat",
                      "+359", "CP", "Tushar Bhai", "Toronto", "In 6 Months", "Warm", "5 Cr to 10 Cr", "200 Sq.Ft", "Residential", "4 Bhk", "Pre-Lease"},
                    
                     {"Universal", "Ritu", "Jain", "+213", "Print Media", "Hoardings", "Interested in joint venture",
                      "+380", "CP", "Tushar Bhai", "Vancouver", "In 1 Year +", "Hot", "10 Cr to 15 Cr", "250 Sq.Ft", "Residential", "5 Bhk", "Buy"},
                    
                     {"The Pre-Launch Fund", "Farhan", "Ansari", "+216", "Print Media", "Leaflets", "Wants coastal property",
                      "+385", "CP", "Tushar Bhai", "Sydney", "Immediate", "Cold", "Below 50 Lakh", "300 Sq.Ft", "Residential", "Weekend Villa", "Invest"},
                    
                     {"Universal", "Shruti", "Deshmukh", "+65", "Channel Sales", "Channel Partner", "Needs farmhouse land",
                      "+386", "CP", "Tushar Bhai", "Melbourne", "In 3 Months", "Warm", "50 Lakh to 1 Cr", "350 Sq.Ft", "Commercial", "Office", "Lease"},
                    
                     {"The Pre-Launch Fund", "Manish", "Thakur", "+91", "Channel Sales", "ACP", "Wants studio apartment",
                      "+387", "CP", "Tushar Bhai", "Tokyo", "In 6 Months", "Hot", "1 Cr to 2 Cr", "400 Sq.Ft", "Commercial", "Showroom", "Pre-Lease"},
                    };
    }

    @Test(dataProvider = "leadData")
    public void addLeadForBasicFundLead(String project, String fname, String lname, String country, String source, String subSource, String remarks) throws InterruptedException {
     phone = PhoneNumber.generateUniquePhoneNumber();
     System.out.print("Fund lead Phone Number: "+phone);
    String email = fname + lname + "@yopmail.com";
    addfundLead.addLeadWithBasic(project, fname, lname, country, phone, email, source, subSource, remarks);
    addfundLead.validateLeadWithBasicInfo(fname, lname, project, source, email, country, phone, remarks, subSource);
    }
    
    // @Test(dataProvider = "AdditionalleadData")
    public void addLeadForAdditionalFundLeadTest(
            String project, String fname, String lname, String country, String source, String subSource, String remarks,
             String addCountryCode, String referralType, String referralName, String location, String buyingTime,
            String priority, String budget, String area, String projectCat, String unitType, String leadType) throws InterruptedException {
       
         phone = PhoneNumber.generateUniquePhoneNumber();
         System.out.print("Fund lead Phone Number: "+phone);
        String additionalPhone = PhoneNumber.generateUniquePhoneNumber();
        String email = fname + lname + "@yopmail.com";

        addfundLead.addLeadWithAdditional(
            project, fname, lname, country, phone, email, source, subSource, remarks,
             addCountryCode, additionalPhone, referralType, referralName,
            location, buyingTime, priority, budget, area, projectCat, unitType, leadType
        );
        addfundLead.validateLeadWithAdditionalInfo(fname, lname, project, source, email, country, phone, remarks, subSource, 
        		addCountryCode, additionalPhone, referralType, referralName,
                buyingTime, priority, budget, area,
                projectCat, unitType, leadType, location);
        
    }

}
