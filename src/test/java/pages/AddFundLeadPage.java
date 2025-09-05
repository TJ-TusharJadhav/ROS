package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddFundLeadPage {
    private Page page;

    // Locators
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
    private String FirstName = "(//p[@class='font-medium text-xs sm:text-sm'])[7]";
    private String LastName = "(//p[@class='font-medium text-xs sm:text-sm'])[8]";
    private String Email = "(//p[@class='font-medium text-xs sm:text-sm'])[9]";
    private String CountryCode = "(//p[@class='font-medium text-xs sm:text-sm'])[10]";
    private String PhoneNumber = "(//p[@class='font-medium text-xs sm:text-sm'])[11]"; 
    private String comments = "(//p[@class='font-medium text-xs sm:text-sm'])[12]";
    private String sources = "(//p[@class='font-medium text-xs sm:text-sm'])[13]";
    private String SubSources = "(//p[@class='font-medium text-xs sm:text-sm'])[14]";
    
    private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
    private String selectFundOption = "//div[text()='Fund']";

    public AddFundLeadPage(Page page) {
        this.page = page;
    }

    public void addLead(String projectName, String fname, String lname, String country,
                        String phone, String mail, String source, String subSource, String note) throws InterruptedException {
    	
    	page.click(followupMenu);
        Thread.sleep(1000);
        page.click(openAddLeadOptions);
        page.click(selectFundOption);
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
        Thread.sleep(1000);
        page.reload();
        page.click(followupMenu);
        Thread.sleep(1000);
        page.click(openAddLeadOptions);
        page.click(selectFundOption);
        Thread.sleep(500);
    }
    // --- Validation method ---
    public void validateLead(String expectedFirstName, String expectedLastName, String expectedProject, String expectedSource) throws InterruptedException {
        String actualFullName = page.textContent(fullNameText).trim();
        String actualProject = page.textContent(projectNameText).trim();
        String actualSource = page.textContent(sourceText).trim();
        
       System.out.println("Actual Full Name: "+actualFullName);

        assert actualFullName.equals(expectedFirstName + " " + expectedLastName) 
            : "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;

        assert actualProject.equals(expectedProject) 
            : "Project mismatch. Expected: " + expectedProject + ", Got: " + actualProject;

        assert actualSource.equals(expectedSource) 
            : "Source mismatch. Expected: " + expectedSource + ", Got: " + actualSource;

        System.out.println("Lead saved successfully with Name, Project, and Source validated!");
        
//        Lead details on history 
        Thread.sleep(500);
        page.click(fullDetailsText);
//        page.click("//span[text()='Create']");
        Locator verifyButton = page.locator("//span[text()='Create']");

        if (verifyButton.count() > 0) {  // element exists in DOM
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
            System.out.println("Verify button clicked!");
        } else {
            System.out.println("Verify button not found, reloading page...");
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            page.click(fullDetailsText);
            Locator verifyButtonAfterReload = page.locator("//span[text()='Create']");
            verifyButtonAfterReload.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButtonAfterReload.click();
            System.out.println("Verify button clicked after reload!");
        }
        
        String actualFirstName =page.textContent(FirstName).trim();
        String actualLastName =page.textContent(LastName).trim();
        System.out.println("Actual First Name: "+actualFirstName+"\n"+"Actual Last Name: "+actualLastName);
        
        
        assert actualFirstName.equals(expectedFirstName) 
        : "Full name mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
        assert actualLastName.equals(expectedLastName) 
        : "Full name mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;
        
        

        
    }
 // --- Validation method ---
    public void validateLeadWithOtherDetails(String expectedEmail, String expectedCountryCode, String expectedPhonenumber, String expectedRemark, 
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
}
