package playwright;


import java.nio.file.Paths;
import java.util.Random;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddProjectLead {
	 private Playwright playwright;
	    private Browser browser;
	    private Page page;

	    // Locators
	    private String usernameField = "#phone";
	    private String getOtpBtn = "//button[@type='submit']";
	    private String followupMenu = "(//p[@data-size='sm'])[4]";
	    private String openaddleadoptions = "//div[@class='css-13cymwt-control']";
	    private String selectProjectOption = "//div[text()='Project']";
	    private String addLeadBtn = "//button[normalize-space()='+ Add Lead']";
	    private String projectDropDown = "#project";
	    private String firstName = "#firstName";
	    private String lastName = "#lastName";
	    private String countryCode = "(//div[@aria-label='Select Country Code'])[1]";
	    private String searchCountryCode = "(//input[@aria-label='Search for a country'])";
	    private String email = "#email";
	    private String remark = "#clientRemarks";
	    private String leadPhoneNumber = "#mobileNumber";
	    private String sourceDropdown = "#source";
	    private String subSourceDropdown = "#subSource";
	    private String submitBtn = "//button[@type='submit']";
	 // Locators for Lead card
	    private String fullNameText = "(//span[@class='font-medium'])[1]";
	    private String projectNameText = "(//span[@class='font-medium'])[2]";
	    private String sourceText = "(//span[@class='font-medium'])[3]";
	    private String fullDetailsText = "(//p[@class='text-gray-500 mb-3 text-base'])[1]";
	 // Locators for Lead history
	    private String ProjectName = "(//p[@class='font-medium text-xs sm:text-sm'])[7]";
	    private String FirstName = "(//p[@class='font-medium text-xs sm:text-sm'])[8]";
	    private String LastName = "(//p[@class='font-medium text-xs sm:text-sm'])[9]";
	    private String Email = "(//p[@class='font-medium text-xs sm:text-sm'])[10]";
	    private String CountryCode = "(//p[@class='font-medium text-xs sm:text-sm'])[11]";
	    private String PhoneNumber = "(//p[@class='font-medium text-xs sm:text-sm'])[12]";
	    private String comments = "(//p[@class='font-medium text-xs sm:text-sm'])[13]";
	    private String sources = "(//p[@class='font-medium text-xs sm:text-sm'])[14]";
	    private String SubSources = "(//p[@class='font-medium text-xs sm:text-sm'])[15]";

	    // Utility: Generate always unique phone numbers
	    private String generateUniquePhoneNumber() {
	        Random no = new Random();
	        long num = 6000000000L + (long) (no.nextDouble() * 3999999999L); 
	        return String.valueOf(num);
	    }


	    @BeforeClass
	    public void setup() throws InterruptedException {
	        playwright = Playwright.create();
	        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
	        page = browser.newPage();
	        page.navigate("https://admin-core-development.realestateos.io/");
	        page.fill(usernameField, "8378845340");
	        page.click(getOtpBtn);

	     // Locator for Verify button
	        Locator verifyButton = page.locator("button:has-text('Verify')");
	        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	        page.waitForCondition(() -> verifyButton.isEnabled());
	        verifyButton.click();
	        Thread.sleep(1000);
	        page.navigate("https://admin-core-development.realestateos.io/follow-up");
	        Thread.sleep(1000);
	        page.click(followupMenu);
//	        page.click(openaddleadoptions);
//	        page.click(selectProjectOption);
	    }
	    @AfterMethod
	    public void SC(org.testng.ITestResult result) throws InterruptedException {
	    	
	            
	                if (ITestResult.FAILURE == result.getStatus()) {
	                    // Screenshot on Failure
	                    page.screenshot(new Page.ScreenshotOptions()
	                            .setPath(Paths.get("screenshots/" + result.getName() + "_failed.png"))
	                            .setFullPage(true));
	                } else {
	                	System.out.println("Test Pass");
	                	System.out.println("After Method");
	        	    	Thread.sleep(1000);
	        	        page.click("//button[contains(@class,'mantine-Modal-close')]");
	        	        Thread.sleep(500);
	        	        page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        	        page.click("div.cursor-pointer.flex.items-center.justify-end");

	        	        Thread.sleep(500);
	                }
	            
	        }

	    
		@AfterClass
	    public void tearDown() {
	        browser.close();
	        playwright.close();
	    }


	    // Utility method to add lead
	    public void addLead(String projectName, String fname, String lname, String country, String phone, String mail, String source,String subSource,String note) throws InterruptedException {
//	        String phone = generateUniquePhoneNumber();
//	        String mail = "test" + phone + "@yopmail.com";
//	        String note = "Testing;"+" "+subSource;
	        
	        page.click(addLeadBtn);
	        Thread.sleep(1000);
	        page.fill(projectDropDown, projectName);
	        Thread.sleep(500);
	        page.keyboard().press("Enter");
	        page.fill(firstName, fname);
	        page.fill(lastName, lname);
	        page.click(countryCode);
	        page.fill(searchCountryCode, country);
	        Thread.sleep(500);
	        page.click("//li[@role='option']");
	        page.fill(leadPhoneNumber, phone);
	        page.fill(email, mail);
	        page.selectOption(sourceDropdown, new SelectOption().setLabel(source));
	        page.selectOption(subSourceDropdown, new SelectOption().setLabel(subSource));
	        page.fill(remark, note);
	        page.click(submitBtn);
	        Thread.sleep(2000);
//	        page.reload();
	    }
// --- Validation method ---
private void validateLead(String expectedFirstName, String expectedLastName, String expectedProject, String expectedSource) throws InterruptedException {
String actualFullName = page.textContent(fullNameText).trim();
String actualProject = page.textContent(projectNameText).trim();
String actualSource = page.textContent(sourceText).trim();

assert actualFullName.equals(expectedFirstName + " " + expectedLastName) 
: "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;
assert actualProject.equals(expectedProject) 
: "Project mismatch. Expected: " + expectedProject + ", Got: " + actualProject;
assert actualSource.equals(expectedSource) 
: "Source mismatch. Expected: " + expectedSource + ", Got: " + actualSource;

page.click(fullDetailsText);
page.click("//span[text()='Create']");

String actualProjectName = page.textContent(ProjectName).trim();
String actualFirstName = page.textContent(FirstName).trim();
String actualLastName = page.textContent(LastName).trim();

System.out.println("Actual Project Name: "+actualProjectName);
System.out.println("Actual First Name: "+actualFirstName);
System.out.println("Actual Last Name: "+actualLastName);

assert actualProjectName.equals(expectedProject) 
: "Project Name mismatch. Expected: " + expectedProject + ", Got: " + actualProjectName;
assert actualFirstName.equals(expectedFirstName) 
: "First name mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
assert actualLastName.equals(expectedLastName) 
: "Last name mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;

}
	    
private void validateLeadWithOtherDetails(String expectedEmail, String expectedCountryCode, String expectedPhonenumber, String expectedRemark, 
                String expectedSource, String expectedSubSource) throws InterruptedException {

String actualemail =page.textContent(Email).trim();
String actualCountryCode =page.textContent(CountryCode).trim();
String actualPhoneNumber = page.textContent(PhoneNumber).trim();
String actualRemark = page.textContent(comments).trim();
String actualSource = page.textContent(sources).trim();
String actualSubSource = page.textContent(SubSources).trim();

System.out.println("Actual Email: "+actualemail);
System.out.println("Actual Country Code: "+actualCountryCode);
System.out.println("Actual Phone Number: "+actualPhoneNumber);
System.out.println("Actual Remark: "+actualRemark);
System.out.println("Actual Source: "+actualSource);
System.out.println("Actual SubSource: "+actualSubSource);


assert actualemail.equals(expectedEmail) 
: "Full name mismatch. Expected: " + expectedEmail + ", Got: " + actualemail;
assert actualCountryCode.equals(expectedCountryCode) 
: "Full name mismatch. Expected: " + expectedCountryCode + ", Got: " + actualCountryCode;
assert actualPhoneNumber.equals(expectedPhonenumber) 
: "Full name mismatch. Expected: " + expectedPhonenumber + ", Got: " + actualPhoneNumber;
assert actualRemark.equals(expectedRemark) 
: "Full name mismatch. Expected: " + expectedRemark + ", Got: " + actualRemark;
assert actualSource.equals(expectedSource) 
: "Full name mismatch. Expected: " + expectedSource + ", Got: " + actualSource;
assert actualSubSource.equals(expectedSubSource) 
: "Full name mismatch. Expected: " + expectedSubSource + ", Got: " + actualSubSource;
}

	    
	    @Test(priority = 1)
	    public void addLeadForProject_Event_GIHED() throws InterruptedException {
	        String fname = "Aarav";
	        String lname = "Sharma";
//	        String project = "Greenfield";
	        String project = "New Project 46";
	        String source = "Event";
	        String subSource = "GIHED";
	        String country = "+93";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via "+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	        validateLeadWithOtherDetails(email, country,Phone, comments, source, subSource);
	    }

	    @Test(priority = 2)
	    public void addLeadForProject_Event_PropertyEvent() throws InterruptedException {
	        String fname = "Vivaan";
	        String lname = "Mehta";
	        String project = "Wave";
	        String source = "Event";
	        String subSource = "Property Event";
	        String country = "+20";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via "+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test(priority = 3)
	    public void addLeadForProject_Event_InvestorMeet() throws InterruptedException {
	        String fname = "Arjun";
	        String lname = "Reddy";
	        String project = "Crown";
	        String source = "Event";
	        String subSource = "Investor Meet";
	        String country = "+27";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }

	    @Test (priority = 4)
	    public void addLeadForProject_Event_ChannelPartnerEvent() throws InterruptedException {
	        String fname = "Advait";
	        String lname = "Deshmukh";
	        String project = "SPlus";
	        String source = "Event";
	        String subSource = "Channel Partner Event";
	        String country = "+30";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 5)
	    public void addLeadForProject_Digital_Website() throws InterruptedException {
	        String fname = "Kunal";
	        String lname = "Verma";
	        String project = "Park Lane";
	        String source = "Digital";
	        String subSource = "Website";
	        String country = "+31";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 6)
	    public void addLeadForProject_Digital_Facebook() throws InterruptedException {
	        String fname = "Rohan";
	        String lname = "Nair";
	        String project = "Trophy";
	        String source = "Digital";
	        String subSource = "Facebook";
	        String country = "+32";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 7)
	    public void addLeadForProject_Digital_Instagram() throws InterruptedException {
	        String fname = "Ishaan";
	        String lname = "Kapoor";
	        String project = "Parkview 2";
	        String source = "Digital";
	        String subSource = "Instagram";
	        String country = "+33";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 8)
	    public void addLeadForProject_Digital_LinkedIn() throws InterruptedException {
	        String fname = "Siddharth";
	        String lname = "Iyer";
	        String project = "Edge";
	        String source = "Digital";
	        String subSource = "LinkedIn";
	        String country = "+34";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 9)
	    public void addLeadForProject_Digital_SMS() throws InterruptedException {
	        String fname = "Kabir";
	        String lname = "Joshi";
	        String project = "Harmony";
	        String source = "Digital";
	        String subSource = "SMS";
	        String country = "+49";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 10)
	    public void addLeadForProject_Digital_YouTube() throws InterruptedException {
	        String fname = "Aditya";
	        String lname = "Patel";
	        String project = "Greenview";
	        String source = "Digital";
	        String subSource = "YouTube";
	        String country = "+52";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 11)
	    public void addLeadForProject_Digital_IVRS() throws InterruptedException {
	        String fname = "Aarav";
	        String lname = "Patel";
	        String project = "Greenfield";
	        String source = "Digital";
	        String subSource = "IVRS";
	        String country = "+55";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 12)
	    public void addLeadForProject_Digital_Google() throws InterruptedException {
	        String fname = "Dhruv";
	        String lname = "Shah";
	        String project = "Wave";
	        String source = "Digital";
	        String subSource = "Google";
	        String country = "+62";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test(priority = 13)
	    public void addLeadForProject_Digital_Email() throws InterruptedException {
	        String fname = "Parth";
	        String lname = "Mehta";
	        String project = "Crown";
	        String source = "Digital";
	        String subSource = "Email";
	        String country = "+63";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 14)
	    public void addLeadForProject_Digital_WhatsApp() throws InterruptedException {
	        String fname = "Vivaan";
	        String lname = "Desai";
	        String project = "SPlus";
	        String source = "Digital";
	        String subSource = "WhatsApp";
	        String country = "+64";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    
	    @Test (priority = 15)
	    public void addLeadForProject_Walkin_Facebook() throws InterruptedException {
	        String fname = "Yash";
	        String lname = "Trivedi";
	        String project = "Park Lane";
	        String source = "Walkin";
	        String subSource = "Facebook";
	        String country = "+66";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 16)
	    public void addLeadForProject_Walkin_Instagram() throws InterruptedException {
	        String fname = "Harsh";
	        String lname = "Joshi";
	        String project = "Trophy";
	        String source = "Walkin";
	        String subSource = "Instagram";
	        String country = "+81";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 17)
	    public void addLeadForProject_Walkin_LinkedIn() throws InterruptedException {
	        String fname = "Krunal";
	        String lname = "Modi";
	        String project = "Parkview 2";
	        String source = "Walkin";
	        String subSource = "LinkedIn";
	        String country = "+82";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 18)
	    public void addLeadForProject_Walkin_SMS() throws InterruptedException {
	        String fname = "Manan";
	        String lname = "Bhatt";
	        String project = "Edge";
	        String source = "Walkin";
	        String subSource = "SMS";
	        String country = "+86";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 19)
	    public void addLeadForProject_Walkin_YouTube() throws InterruptedException {
	        String fname = "Nirav";
	        String lname = "Shukla";
	        String project = "Harmony";
	        String source = "Walkin";
	        String subSource = "YouTube";
	        String country = "+90";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 20)
	    public void addLeadForProject_Walkin_IVRS() throws InterruptedException {
	        String fname = "Jatin";
	        String lname = "Vora";
	        String project = "Greenview";
	        String source = "Walkin";
	        String subSource = "IVRS";
	        String country = "+91";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 21)
	    public void addLeadForProject_Walkin_Google() throws InterruptedException {
	        String fname = "Ankit";
	        String lname = "Gohil";
	        String project = "Greenfield";
	        String source = "Walkin";
	        String subSource = "Google";
	        String country = "+92";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 22)
	    public void addLeadForProject_Walkin_Email() throws InterruptedException {
	        String fname = "Kunal";
	        String lname = "Pandya";
	        String project = "Wave";
	        String source = "Walkin";
	        String subSource = "Email";
	        String country = "+93";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 23)
	    public void addLeadForProject_Walkin_WhatsApp() throws InterruptedException {
	        String fname = "Rajesh";
	        String lname = "Parmar";
	        String project = "Crown";
	        String source = "Walkin";
	        String subSource = "WhatsApp";
	        String country = "+94";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 24)
	    public void addLeadForProject_Walkin_DirectWalkin() throws InterruptedException {
	        String fname = "Mihir";
	        String lname = "Chauhan";
	        String project = "SPlus";
	        String source = "Walkin";
	        String subSource = "Direct-Walkin";
	        String country = "+95";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    
	    @Test (priority = 25)
	    public void addLeadForProject_PrintMedia_NewsPaper() throws InterruptedException {
	        String fname = "Rohan";
	        String lname = "Upadhyay";
	        String project = "Park Lane";
	        String source = "Print Media";
	        String subSource = "News Paper";
	        String country = "+98";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 26)
	    public void addLeadForProjectPrintMedia_Hoardings() throws InterruptedException {
	        String fname = "Sagar";
	        String lname = "Dave";
	        String project = "Trophy";
	        String source = "Print Media";
	        String subSource = "Hoardings";
	        String country = "+211";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 27)
	    public void addLeadForProjectPrintMedia_Leaflets() throws InterruptedException {
	        String fname = "Tushar";
	        String lname = "Prajapati";
	        String project = "Parkview 2";
	        String source = "Print Media";
	        String subSource = "Leaflets";
	        String country = "+213";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }

	    @Test (priority = 28)
	    public void addLeadForProject_ChannelSales_ChannelPartner() throws InterruptedException {
	        String fname = "Ketan";
	        String lname = "Solanki";
	        String project = "Edge";
	        String source = "Channel Sales";
	        String subSource = "Channel Partner";
	        String country = "+216";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 29)
	    public void addLeadForProject_ChannelSales_ACP() throws InterruptedException {
	        String fname = "Hiren";
	        String lname = "Vyas";
	        String project = "Harmony";
	        String source = "Channel Sales";
	        String subSource = "ACP";
	        String country = "+218";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
	    @Test (priority = 30)
	    public void addLeadForProject_Walkin_Website() throws InterruptedException {
	        String fname = "Deepak";
	        String lname = "Rana";
	        String project = "Greenview";
	        String source = "Walkin";
	        String subSource = "Website";
	        String country = "+65";
	        String Phone= generateUniquePhoneNumber();
	        String email = fname+ lname+ Phone + "@yopmail.com";
	        String comments = "Lead added via"+source+subSource;
	        
	        addLead(project, fname, lname, country, Phone, email, source, subSource, comments);
	        validateLead(fname, lname, project, source);
	    }
   
}