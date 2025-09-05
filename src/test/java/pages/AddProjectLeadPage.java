package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddProjectLeadPage {
    private Page page;
// Basic add lead Locators
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
// Additional add lead Locators
    private String SecondaryProjectDropDown = "#secondaryProject";
    private String additionalcountryCode = "(//div[@aria-label='Select Country Code'])[2]";
    private String alternateMobileNumber = "#alternateMobileNumber";
    private String referralType = "#referralType";
    private String referralNameField = "//input[@placeholder='Referral Name *']";
    private String referralNamesearchBar = "//input[@placeholder='Search Referral']";
    private String location = "#location";
    private String priority = "#priority";
    private String budget = "#budget";
    private String areaRequired = "#areaRequired";  
    private String leadCategory = "#leadCategory";
    private String unitType = "#unitType";
    private String leadType = "#leadType";
 // Locators for Lead card
    private String fullNameText = "(//span[@class='font-medium'])[1]";
    private String projectNameText = "(//span[@class='font-medium'])[2]";
    private String sourceText = "(//span[@class='font-medium'])[3]";
    private String fullDetailsText = "//p[contains(text(), 'Mobile no')]";
 // Locators for Lead history
    private String projectnameinhirstory = "(//p[@class='font-medium text-xs sm:text-sm'])[7]";
    private String FirstName = "(//p[@class='font-medium text-xs sm:text-sm'])[8]";
    private String LastName = "(//p[@class='font-medium text-xs sm:text-sm'])[9]";
    private String Email = "(//p[@class='font-medium text-xs sm:text-sm'])[10]";
    private String CountryCode = "(//p[@class='font-medium text-xs sm:text-sm'])[11]";
    private String PhoneNumber = "(//p[@class='font-medium text-xs sm:text-sm'])[12]"; 
    private String comments = "(//p[@class='font-medium text-xs sm:text-sm'])[13]";
    private String sources = "(//p[@class='font-medium text-xs sm:text-sm'])[14]";
    private String SubSources = "(//p[@class='font-medium text-xs sm:text-sm'])[15]";
    
    private String alternatecountryCode = "(//p[@class='font-medium text-xs sm:text-sm'])[16]";
    private String alternatePhone  = "(//p[@class='font-medium text-xs sm:text-sm'])[17]";
    private String ReferralType = "(//p[@class='font-medium text-xs sm:text-sm'])[18]";
    private String referralName = "(//p[@class='font-medium text-xs sm:text-sm'])[19]";
    private String buyingTime = "(//p[@class='font-medium text-xs sm:text-sm'])[22]";
    private String Priority = "(//p[@class='font-medium text-xs sm:text-sm'])[23]";
    private String Budget = "(//p[@class='font-medium text-xs sm:text-sm'])[24]";
    private String area = "(//p[@class='font-medium text-xs sm:text-sm'])[25]";
    private String projectCategory  = "(//p[@class='font-medium text-xs sm:text-sm'])[26]";
    private String UnitType = "(//p[@class='font-medium text-xs sm:text-sm'])[27]";
    private String LeadType = "(//p[@class='font-medium text-xs sm:text-sm'])[28]";
    private String Location = "(//p[@class='font-medium text-xs sm:text-sm'])[29]";
    private String secondaryProjectName = "(//p[@class='font-medium text-xs sm:text-sm'])[30]"; 
    
    private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String clickSearchedFirstOption = "//li[@role='option']";
    
    public AddProjectLeadPage(Page page) {
        this.page = page;
    }
    public void validateLeadWithAdditionalInfo(
    		String expectedFirstName, String expectedLastName, String expectedMainProject, 
    		String expectedSource,String expectedEmail, String expectedCountryCode, 
    		String expectedPhonenumber, String expectedRemark,String expectedSubSource,
    		
    		String expectedAlternatCountry, String expectedAlternatePhone, String expectedReferralType, String expectedreferralName, 
    		String expectedbuyingTime, String expectedpriority, 
    		String expectedbudget, String expectedarea,String expectedprojectCategory, String expectedunitType, 
    		String expectedleadType, String expectedlocation, String expectedecondaryProject) throws InterruptedException {
    	
    	
    	
    	String actualFullName = page.textContent(fullNameText).trim();
        String actualProject = page.textContent(projectNameText).trim();
        String actualSource = page.textContent(sourceText).trim();
        String actualPhone = page.textContent(fullDetailsText).trim();
        String phonePart = actualPhone.replace("Mobile no -", "").trim();
       

//     Split into country code and number
    String[] parts = phonePart.split(" ", 2); 
    String ActualcountryCode = parts[0]; // +91
    String ActualphoneNumber = parts[1]; // 1234567867

        assert actualFullName.equals(expectedFirstName + " " + expectedLastName) 
            : "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;
        assert actualProject.equals(expectedMainProject) 
            : "Project mismatch. Expected: " + expectedMainProject + ", Got: " + actualProject;
        assert actualSource.equals(expectedSource) 
            : "Source mismatch. Expected: " + expectedSource + ", Got: " + actualSource;
        assert ActualcountryCode.equals(expectedCountryCode) 
            : "Country code mismatch. Expected: "+ expectedCountryCode + ", Got: " + ActualcountryCode;
        assert ActualphoneNumber.equals(expectedPhonenumber) 
            : "Phone number mismatch. Expected: "+ expectedPhonenumber + ", Got: " + ActualphoneNumber;
        
        System.out.println("Lead saved successfully with Name, Project, and Source validated!");
        
//        Lead details on history 
        Thread.sleep(500);
        page.click(fullDetailsText);
        Locator verifyButton = page.locator("//span[text()='Create']");
        if (verifyButton.count() > 0) {
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
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
            System.out.println("Verify button clicked after reload!");
        }
        String actualProjectName =page.textContent(projectnameinhirstory).trim();
        String actualFirstName =page.textContent(FirstName).trim();
        String actualLastName =page.textContent(LastName).trim();
        String actualemail =page.textContent(Email).trim();
        String actualCountryCode =page.textContent(CountryCode).trim();
        String actualLeadPhoneNumber = page.textContent(PhoneNumber).trim();
        String actualRemark = page.textContent(comments).trim();
        String actualSource1 = page.textContent(sources).trim();
        String actualSubSource = page.textContent(SubSources).trim();
        
        String actualalternatecountryCode =page.textContent(alternatecountryCode).trim();
        String actualalternatePhoneNumber =page.textContent(alternatePhone).trim();
        String actualreferralType =page.textContent(ReferralType).trim();
        String actualreferralName =page.textContent(referralName).trim();
        String actualbuyingTime = page.textContent(buyingTime).trim();
        String actualpriority = page.textContent(Priority).trim();
        String actualbudget = page.textContent(Budget).trim();
        String actualarea =page.textContent(area).trim();
        String actualprojectCategory =page.textContent(projectCategory).trim();
        String actualunitType =page.textContent(UnitType).trim();
        String actualleadType =page.textContent(LeadType).trim();
        String actuallocation =page.textContent(Location).trim();
        String actualsecondaryProject  = page.textContent(secondaryProjectName).trim();
        
        assert actualProjectName.equals(expectedMainProject) 
        : "Full name mismatch. Expected: " + expectedMainProject + ", Got: " + actualProjectName;
        assert actualFirstName.equals(expectedFirstName) 
        : "Full name mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
        assert actualLastName.equals(expectedLastName) 
        : "Full name mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;
        assert actualemail.equals(expectedEmail) 
        : "Full name mismatch. Expected: " + expectedEmail + ", Got: " + actualemail;
        assert actualCountryCode.equals(expectedCountryCode) 
        : "CountryCode mismatch. Expected: " + expectedCountryCode + ", Got: " + actualCountryCode;
        assert actualLeadPhoneNumber.equals(expectedPhonenumber) 
        : "Full name mismatch. Expected: " + expectedPhonenumber + ", Got: " + actualLeadPhoneNumber;
        assert actualRemark.equals(expectedRemark) 
        : "Full name mismatch. Expected: " + expectedRemark + ", Got: " + actualRemark;
        assert actualSource1.equals(expectedSource) 
        : "Full name mismatch. Expected: " + expectedSource + ", Got: " + actualSource1;
        assert actualSubSource.equals(expectedSubSource) 
        : "Full name mismatch. Expected: " + expectedSubSource + ", Got: " + actualSubSource;
        assert actualalternatecountryCode.equals(expectedAlternatCountry) 
        : "Full name mismatch. Expected: " + expectedAlternatCountry + ", Got: " + actualalternatecountryCode;
        
//        Scroll 
//        page.mouse().wheel(0, 10000);  // Scrolls down

        assert actualalternatePhoneNumber.equals(expectedAlternatePhone) 
        : "Full name mismatch. Expected: " + expectedAlternatePhone + ", Got: " + actualalternatePhoneNumber;
        assert actualreferralType.equals(expectedReferralType) 
        : "Full name mismatch. Expected: " + expectedReferralType + ", Got: " + actualreferralType;
        assert actualreferralName.equals(expectedreferralName) 
        : "Full name mismatch. Expected: " + expectedreferralName + ", Got: " + actualreferralName;
        assert actualbuyingTime.equals(expectedbuyingTime) 
        : "Full name mismatch. Expected: " + expectedbuyingTime + ", Got: " + actualbuyingTime;
        assert actualpriority.equals(expectedpriority) 
        : "Full name mismatch. Expected: " + expectedpriority + ", Got: " + actualpriority;
        assert actualbudget.equals(expectedbudget) 
        : "Full name mismatch. Expected: " + expectedbudget + ", Got: " + actualbudget;
        assert actualarea.equals(expectedarea) 
        : "Full name mismatch. Expected: " + expectedarea + ", Got: " + actualarea;
        assert actualprojectCategory.equals(expectedprojectCategory) 
        : "Full name mismatch. Expected: " + expectedprojectCategory + ", Got: " + actualprojectCategory;
        assert actualunitType.equals(expectedunitType) 
        : "Full name mismatch. Expected: " + expectedunitType + ", Got: " + actualunitType;  
        assert actualleadType.equals(expectedleadType) 
        : "Full name mismatch. Expected: " + expectedleadType + ", Got: " + actualleadType;
        assert actuallocation.equals(expectedlocation) 
        : "Full name mismatch. Expected: " + expectedlocation + ", Got: " + actuallocation;
        assert actualsecondaryProject.equals(expectedecondaryProject) 
        : "Full name mismatch. Expected: " + expectedecondaryProject + ", Got: " + actualsecondaryProject;
        
        }
    public void addLeadWithAdditional(String projectName, String fname, String lname, String country,
            String phone, String mail, String source, String subSource, String note,
            String secondryProject, String AdditionalCountrycode, String AdditionalPhone, 
    		                          String ReferralType,String ReferralName, String Location, 
    		                          String BuyingTime, String Priority, String Budget, 
    		                          String Area, String ProjectCategory, String UnitType, 
    		                          String LeadType) throws InterruptedException {
    	page.click(followupMenu);
        Thread.sleep(1000);
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
        page.click(clickSearchedFirstOption);
        page.fill(leadPhoneNumber, phone);
        page.fill(email, mail);
        page.selectOption(sourceDropdown, new SelectOption().setLabel(source));
        page.selectOption(subSourceDropdown, new SelectOption().setLabel(subSource));
        page.fill(remark, note);
        page.click("text=Basic Lead Form Details");
        Thread.sleep(500);
    	page.click("text=Additional Form Details");
    	page.fill(SecondaryProjectDropDown, secondryProject);
        Thread.sleep(500);
        page.keyboard().press("Enter");
        page.click(additionalcountryCode);
        page.fill(searchCountryCode, AdditionalCountrycode);
        Thread.sleep(500);
        page.click(clickSearchedFirstOption);
        page.fill(alternateMobileNumber, AdditionalPhone);
//        referral Type and name is pending 
        page.selectOption(referralType, new SelectOption().setLabel(ReferralType));
        Thread.sleep(500);
        page.click(referralNameField);
        Thread.sleep(1000);
        page.fill(referralNamesearchBar, ReferralName);
        Locator verifyButton = page.locator("//li[@role='option']");
        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.waitForCondition(() -> verifyButton.isVisible());
        verifyButton.click();
//        page.click(clickSearchedFirstOption);
        page.fill(location, Location);
        page.click("//input[@value='"+BuyingTime+"']");
      page.selectOption(priority, new SelectOption().setLabel(Priority));
        page.selectOption(budget, new SelectOption().setLabel(Budget));
        page.fill(areaRequired, Area);
        page.selectOption(leadCategory, new SelectOption().setLabel(ProjectCategory));
        page.selectOption(unitType, new SelectOption().setLabel(UnitType));
        page.selectOption(leadType, new SelectOption().setLabel(LeadType));
        page.click(submitBtn);
        Thread.sleep(1000);
        page.reload();
        page.click(followupMenu);
        Thread.sleep(1000);
        Thread.sleep(500);
        
    }

    public void addLeadWithBasic(String projectName, String fname, String lname, String country,
                        String phone, String mail, String source, String subSource, String note) throws InterruptedException {
    	
    	page.click(followupMenu);
        Thread.sleep(1000);
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
        page.click(clickSearchedFirstOption);
        page.fill(leadPhoneNumber, phone);
        page.fill(email, mail);
        page.selectOption(sourceDropdown, new SelectOption().setLabel(source));
        page.selectOption(subSourceDropdown, new SelectOption().setLabel(subSource));
        page.fill(remark, note);
        page.click(submitBtn);
        Thread.sleep(1000);
        page.reload();
        Thread.sleep(1000);
        page.click(followupMenu);
        Thread.sleep(1000);
        
    }
    // --- Validation method ---
    public void validateLeadWithBasicInfo(
    		String expectedFirstName, String expectedLastName, String expectedProject, 
    		String expectedSource,String expectedEmail, String expectedCountryCode, 
    		String expectedPhonenumber, String expectedRemark,String expectedSubSource) throws InterruptedException {
    	
    	
        
    	String actualFullName = page.textContent(fullNameText).trim();
        String actualProject = page.textContent(projectNameText).trim();
        String actualSource = page.textContent(sourceText).trim();
        String actualPhone = page.textContent(fullDetailsText).trim();
        String phonePart = actualPhone.replace("Mobile no -", "").trim();
       

    // Split into country code and number
    String[] parts = phonePart.split(" ", 2); 
    String countryCode = parts[0]; // +91
    String phoneNumber = parts[1]; // 1234567867

        assert actualFullName.equals(expectedFirstName + " " + expectedLastName) 
            : "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;
        assert actualProject.equals(expectedProject) 
            : "Project mismatch. Expected: " + expectedProject + ", Got: " + actualProject;
        assert actualSource.equals(expectedSource) 
            : "Source mismatch. Expected: " + expectedSource + ", Got: " + actualSource;
        assert countryCode.equals(expectedCountryCode) 
            : "Country code mismatch. Expected: "+ expectedCountryCode + ", Got: " + countryCode;
        assert phoneNumber.equals(expectedPhonenumber) 
            : "Phone number mismatch. Expected: "+ expectedPhonenumber + ", Got: " + phoneNumber;
        
        System.out.println("Lead saved successfully with Name, Project, and Source validated!");
        
//        Lead details on history 
        Thread.sleep(500);
        page.click(fullDetailsText);
        Locator verifyButton = page.locator("//span[text()='Create']");
        if (verifyButton.count() > 0) {
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
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
            System.out.println("Verify button clicked after reload!");
        }
        String actualProjectName =page.textContent(projectnameinhirstory).trim();
        String actualFirstName =page.textContent(FirstName).trim();
        String actualLastName =page.textContent(LastName).trim();
        String actualemail =page.textContent(Email).trim();
        String actualCountryCode =page.textContent(CountryCode).trim();
        String actualPhoneNumber = page.textContent(PhoneNumber).trim();
        String actualRemark = page.textContent(comments).trim();
        String actualSource1 = page.textContent(sources).trim();
        String actualSubSource = page.textContent(SubSources).trim();
        
        assert actualProjectName.equals(expectedProject) 
        : "Full name mismatch. Expected: " + expectedProject + ", Got: " + actualProjectName;
        assert actualFirstName.equals(expectedFirstName) 
        : "Full name mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
        assert actualLastName.equals(expectedLastName) 
        : "Full name mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;
        assert actualemail.equals(expectedEmail) 
        : "Full name mismatch. Expected: " + expectedEmail + ", Got: " + actualemail;
        assert actualCountryCode.equals(expectedCountryCode) 
        : "Full name mismatch. Expected: " + expectedCountryCode + ", Got: " + actualCountryCode;
        assert actualPhoneNumber.equals(expectedPhonenumber) 
        : "Full name mismatch. Expected: " + expectedPhonenumber + ", Got: " + actualPhoneNumber;
        assert actualRemark.equals(expectedRemark) 
        : "Full name mismatch. Expected: " + expectedRemark + ", Got: " + actualRemark;
        assert actualSource1.equals(expectedSource) 
        : "Full name mismatch. Expected: " + expectedSource + ", Got: " + actualSource1;
        assert actualSubSource.equals(expectedSubSource) 
        : "Full name mismatch. Expected: " + expectedSubSource + ", Got: " + actualSubSource;
        
        }
}
