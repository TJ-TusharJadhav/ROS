package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class EditProjectLeadPage {
    private Page page;
// Basic add lead Locators
    private String EditLeadBtn = "(//button[@class='flex justify-end'])[2]";
    private String projectDropDown = "#project";
    private String firstName = "#firstName";
    private String lastName = "#lastName";
    private String searchCountryCode = "(//input[@aria-label='Search for a country'])";
    private String email = "#email";
    private String remark = "#clientRemarks";
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
    private String fullDetailsText = "//p[contains(text(), 'Mobile no')]";
    
    		
 // Locators for Lead history
    private String projectnameinhirstory = "(//p[@class='font-medium text-xs sm:text-sm'])[7]";
    private String FirstName = "(//p[@class='font-medium text-xs sm:text-sm'])[8]";
    private String LastName = "(//p[@class='font-medium text-xs sm:text-sm'])[9]";
    private String Email = "(//p[@class='font-medium text-xs sm:text-sm'])[10]";
    private String comments = "(//p[@class='font-medium text-xs sm:text-sm'])[11]";
    
    private String alternatecountryCode = "(//p[@class='font-medium text-xs sm:text-sm'])[14]";
    private String alternatePhone  = "(//p[@class='font-medium text-xs sm:text-sm'])[15]";
    private String ReferralType = "(//p[@class='font-medium text-xs sm:text-sm'])[16]";
    private String referralName = "(//p[@class='font-medium text-xs sm:text-sm'])[17]";
    private String buyingTime = "(//p[@class='font-medium text-xs sm:text-sm'])[20]";
    private String Priority = "(//p[@class='font-medium text-xs sm:text-sm'])[21]";
    private String Budget = "(//p[@class='font-medium text-xs sm:text-sm'])[22]";
    private String area = "(//p[@class='font-medium text-xs sm:text-sm'])[23]";
    private String projectCategory  = "(//p[@class='font-medium text-xs sm:text-sm'])[24]";
    private String UnitType = "(//p[@class='font-medium text-xs sm:text-sm'])[25]";
    private String LeadType = "(//p[@class='font-medium text-xs sm:text-sm'])[26]";
    private String Location = "(//p[@class='font-medium text-xs sm:text-sm'])[27]";
    private String secondaryProjectName = "(//p[@class='font-medium text-xs sm:text-sm'])[28]"; 
    
    private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String clickSearchedFirstOption = "//li[@role='option']";
    private String SigIn_Name="(//p[@class='mantine-focus-auto m_b6d8b162 mantine-Text-root'])[1]";
    
    
    
    public EditProjectLeadPage(Page page) {
        this.page = page;
    }
    public void validateLeadWithAdditionalInfo(
    		String expectedFirstName, String expectedLastName, String expectedMainProject, 
    		String expectedEmail,  
    		 String expectedRemark,
    		
    		String expectedAlternatCountry, String expectedAlternatePhone, String expectedReferralType, String expectedreferralName, 
    		String expectedbuyingTime, String expectedpriority, 
    		String expectedbudget, String expectedarea,String expectedprojectCategory, String expectedunitType, 
    		String expectedleadType, String expectedlocation, String expectedecondaryProject) throws InterruptedException {
    	
    	
    	String ExpectedLeadOwner = page.textContent(SigIn_Name).trim();
    	
    	
    	String status = "(//div[@role='button' and contains(@aria-label,'View details for "+expectedFirstName+"')]//span[contains(@class,'rounded-full')])[1]";
    	String leadOwner = "(//div[@role='button' and contains(@aria-label,'View details for "+expectedFirstName+"')]//span[contains(@class,'rounded-full')])[2]";
    	String priority = "(//div[@role='button' and contains(@aria-label,'View details for "+expectedFirstName+"')]//span[contains(@class,'rounded-full')])[3]";
    	
    	
    	String actualFullName = page.textContent(fullNameText).trim();
        String actualProject = page.textContent(projectNameText).trim();        
        String actualStatus = page.textContent(status).trim();;
        String actualLeadOwner = page.textContent(leadOwner).trim();;
        String actualPriority = page.textContent(priority).trim();

        assert actualFullName.equals(expectedFirstName + " " + expectedLastName) 
            : "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;
        assert actualProject.equals(expectedMainProject) 
            : "Project mismatch. Expected: " + expectedMainProject + ", Got: " + actualProject;
       
        assert actualStatus.equals("New Inquiry") 
        : "Phone number mismatch. Expected: "+ "New Inquiry" + ", Got: " + actualStatus;
        
        assert actualLeadOwner.equals(ExpectedLeadOwner) 
        : "Phone number mismatch. Expected: "+ ExpectedLeadOwner + ", Got: " + actualLeadOwner;
        
        assert actualPriority.equals(expectedpriority) 
        : "Phone number mismatch. Expected: "+ expectedpriority + ", Got: " + actualPriority;
        
        
        System.out.println("Lead saved successfully with Name, Project, and Source validated!");
        
//        Lead details on history 
        Thread.sleep(500);
        page.click(fullDetailsText);
        
        
        
        Locator verifyButton = page.locator("(//span[text()='Update'])[1]");
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
        String actualProjectName1 =page.textContent(projectnameinhirstory).trim();
        String actualFirstName =page.textContent(FirstName).trim();
        String actualLastName =page.textContent(LastName).trim();
        String actualemail =page.textContent(Email).trim();
        
        String actualRemark = page.textContent(comments).trim();
        
        
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

        
        assert actualProjectName1.equals(expectedMainProject) 
        : "Full name mismatch. Expected: " + expectedMainProject + ", Got: " + actualProjectName1;
        assert actualFirstName.equals(expectedFirstName) 
        : "Full name mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
        assert actualLastName.equals(expectedLastName) 
        : "Full name mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;
        assert actualemail.equals(expectedEmail) 
        : "Full name mismatch. Expected: " + expectedEmail + ", Got: " + actualemail;
       
        assert actualRemark.equals(expectedRemark) 
        : "Remark mismatch. Expected: " + expectedRemark + ", Got: " + actualRemark;
       
        assert actualalternatecountryCode.equals(expectedAlternatCountry) 
        : "Full name mismatch. Expected: " + expectedAlternatCountry + ", Got: " + actualalternatecountryCode;

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
    public void EditLeadWithAdditional(String projectName, String fname, String lname, 
             String mail,  String note,
            String secondryProject, String AdditionalCountrycode, String AdditionalPhone, 
    		                          String ReferralType,String ReferralName, String Location, 
    		                          String BuyingTime, String Priority, String Budget, 
    		                          String Area, String ProjectCategory, String UnitType, 
    		                          String LeadType) throws InterruptedException {
    	
    	editLeadWithBasic(projectName, fname, lname, mail, note);
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
        page.selectOption(referralType, new SelectOption().setLabel(ReferralType));
        Thread.sleep(500);
        page.click(referralNameField);
        Thread.sleep(1000);
        page.fill(referralNamesearchBar, ReferralName);
        Locator verifyButton = page.locator("//li[@role='option']");
        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        page.waitForCondition(() -> verifyButton.isVisible());
        verifyButton.click();        page.fill(location, Location);
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

    public void EditLeadWithBasic(String projectName, String fname, String lname, String mail, String note) throws InterruptedException {
    	
    	editLeadWithBasic(projectName, fname, lname, mail, note);
        page.click(submitBtn);
        page.reload();
        Thread.sleep(1000);
        page.click(followupMenu);
        Thread.sleep(1000);
        
    }
    // --- Validation method ---
    public void validateLeadWithBasicInfo(
    		String expectedFirstName, String expectedLastName, String expectedProject, 
    		String expectedEmail, String expectedRemark) throws InterruptedException {
    	
    	String ExpectedLeadOwner = page.textContent(SigIn_Name).trim();
    	
    	String status = "(//div[@role='button' and contains(@aria-label,'View details for "+expectedFirstName+"')]//span[contains(@class,'rounded-full')])[1]";
    	String leadOwner = "(//div[@role='button' and contains(@aria-label,'View details for "+expectedFirstName+"')]//span[contains(@class,'rounded-full')])[2]";
    	
        
    	String actualFullName = page.textContent(fullNameText).trim();
        String actualProject = page.textContent(projectNameText).trim();
        
    
    
    String actualStatus = page.textContent(status).trim();
    String actualLeadOwner = page.textContent(leadOwner).trim();
    
    System.out.println("actual Status: "+actualStatus);  
    System.out.println("actual Lead Owner: "+actualLeadOwner); 

        assert actualFullName.equals(expectedFirstName + " " + expectedLastName) 
            : "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;
        assert actualProject.equals(expectedProject) 
            : "Project mismatch. Expected: " + expectedProject + ", Got: " + actualProject;
        assert actualStatus.equals("New Inquiry") 
        : "Phone number mismatch. Expected: "+ "New Inquiry" + ", Got: " + actualStatus;
        assert actualLeadOwner.equals(ExpectedLeadOwner) 
        : "Phone number mismatch. Expected: "+ ExpectedLeadOwner + ", Got: " + actualLeadOwner;
        
        System.out.println("Lead saved successfully with Name, Project, and Source validated!");
        

        
//        Lead details on history 
        Thread.sleep(500);
        page.click(fullDetailsText);
       
      
        Locator verifyButton = page.locator("(//span[text()='Update'])[1]");
        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        page.waitForCondition(() -> verifyButton.isVisible());
        verifyButton.click();
        String actualProjectNameinHistory =page.textContent(projectnameinhirstory).trim();
        String actualFirstName =page.textContent(FirstName).trim();
        String actualLastName =page.textContent(LastName).trim();
        String actualemail =page.textContent(Email).trim();
        String actualRemark = page.textContent(comments).trim();
        
        assert actualProjectNameinHistory.equals(expectedProject) 
        : "Full name mismatch. Expected: " + expectedProject + ", Got: " + actualProjectNameinHistory;
        assert actualFirstName.equals(expectedFirstName) 
        : "Full name mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
        assert actualLastName.equals(expectedLastName) 
        : "Full name mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;
        assert actualemail.equals(expectedEmail) 
        : "Full name mismatch. Expected: " + expectedEmail + ", Got: " + actualemail;
        assert actualRemark.equals(expectedRemark) 
        : "Full name mismatch. Expected: " + expectedRemark + ", Got: " + actualRemark;
        
        }
    public void editLeadWithBasic(String projectName, String fname, String lname, String mail, String note) throws InterruptedException {
        page.click(followupMenu);
        Thread.sleep(1000);
        page.click(fullDetailsText);
        Thread.sleep(1000);
        page.click(EditLeadBtn);
        Thread.sleep(1000);

        page.fill(projectDropDown, projectName);
        Thread.sleep(500);
        page.keyboard().press("Enter");

        page.fill(firstName, fname);
        page.fill(lastName, lname);
        page.fill(email, mail);
        page.fill(remark, note);
    }

    

}
