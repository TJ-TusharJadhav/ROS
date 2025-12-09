package pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class EditFurnitureLeadPage {
    private Page page;
    public String LeadPhoneNo;

 // Basic add lead Locators
    private String EditLeadBtn = "(//button[@class='cursor-pointer'])[2]";
    private String projectDropDown = "#storeName";
    private String firstName = "#firstName";
    private String lastName = "#lastName";
    private String email = "#email";
    private String remark = "#clientRemarks";
    private String Product_Category = "#category";
    private String subCategory = "#subCategory";
    private String submitBtn = "//button[@type='submit']";
    
 // Additional add lead Locators
    private String referralType = "#referralType";
    private String referralNameField = "//input[@placeholder='Referral Name *']";
    private String referralNamesearchBar = "//input[@placeholder='Search Referral']";
    private String SelectPriority = "//select[@aria-label='Select Priority']";
    private String budget = "#budget";
    private String projectType ="#projectType";
    private String sourceOfFinance = "#sourceOfFinance";
    private String dob = "#dob";  
    private String areaLocation = "#areaLocation";
    private String companyName = "#companyName";
    private String estimatedQuantity = "#estimatedQuantity";
    private String projectTimeline ="#projectTimeline";
    
 // Locators for Lead card
    private String fullNameText = "(//span[@class='font-medium'])[1]";
    private String projectNameText = "(//span[@class='font-medium'])[2]";
    private String sourceText = "(//span[@class='font-medium'])[3]";
    private String fullDetailsText = "(//p[@class='text-gray-500 mb-3 text-base break-words'])[1]";
    private String phoneNumber = "//p[contains(text(), 'Mobile no')]";
    // Locators for Lead history
    private String ProjectName = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[1]";
    private String FirstName = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[2]";
    private String LastName = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[3]";
    private String Email = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[4]";
    private String CountryCode = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[5]";
    private String PhoneNumber = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[6]"; 
    private String comments = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[5]";
    private String sources = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[8]";
    private String SubSources = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[9]";
    
    private String buyingTime = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[12]";
    private String Priority = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[13]";
    private String Budget = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[14]";
    
    // private String projectCategory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[13]";
    private String area = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[16]";
    private String ReferralType = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[8]";
    private String referralName = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[9]";
    private String productCategory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[17]";
    private String SubCategory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[18]";
    private String ProjectType = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[20]";
    private String sourceOfFinanceInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[21]";
    private String dobInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[22]";
    private String companyNameInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[23]";    
    private String estimatedQuantityInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[24]";
    private String projectTimelineInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[26]";
    
    
    private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
    private String selectFurnitureOption = "//div[text()='Furniture']";
    private String SigIn_Name="(//p[@class='mantine-focus-auto m_b6d8b162 mantine-Text-root'])[1]";
    // private String burgerMenu = "//button[contains(@class,'mantine-Burger-root')]";
    
    public EditFurnitureLeadPage(Page page) {
        this.page = page;
    }

    public void EditLeadWithBasic( String fname, String lname,
     String mail, String Category, String subCategory, String note) throws InterruptedException {
    	
    	EditLeadBasic( fname, lname, mail, Category, subCategory, note);
        page.click(submitBtn);
        Thread.sleep(2000);
        // page.click("//div[contains(text(), 'Re-Inquiry')]");
        // Thread.sleep(2000);
        // page.click("//div[contains(text(), 'New Lead')]");
    }
    public void validateLeadWithGeneratedData(
            String expectedFirstName, String expectedLastName ,
             String expectedEmail, String expectedRemark, String expectedProductCategory, String expectedSubCategory) throws InterruptedException {
        Thread.sleep(1000);
    	page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", LeadPhoneNo);
    	Thread.sleep(1000);
    	Locator verifyButton = page.locator("(//div[@aria-label='View details for "+expectedFirstName+"'])[1]");
    	verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    	page.waitForCondition(() -> verifyButton.isVisible());

        String expectedLeadOwner = page.textContent(SigIn_Name).trim();
        String statusLocator = "(//div[@role='button' and contains(@aria-label,'View details for " + expectedFirstName + "')]//span[contains(@class,'rounded-full')])[1]";
        String leadOwnerLocator = "(//div[@role='button' and contains(@aria-label,'View details for " + expectedFirstName + "')]//span[contains(@class,'rounded-full')])[2]";

        String actualFullName = page.textContent(fullNameText).trim();
        String actualStatus = page.textContent(statusLocator).trim();
        String actualLeadOwner = page.textContent(leadOwnerLocator).trim();

        // Assertions
        assert actualFullName.equals(expectedFirstName + " " + expectedLastName) : "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;
        assert actualStatus.equals("New Inquiry") : "Status mismatch. Expected: New Inquiry, Got: " + actualStatus;
        assert actualLeadOwner.equals(expectedLeadOwner) : "Lead owner mismatch. Expected: " + expectedLeadOwner + ", Got: " + actualLeadOwner;

        System.out.println("Lead saved successfully with Name, Project, Source, Email & Phone validated!");

        // Validate history details (same as previous method)
        page.click(fullDetailsText);
        Thread.sleep(500);
        Locator createCard = page.locator("(//span[text()='Update'])[1]");
        if (createCard.count() > 0) {
            createCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            createCard.click();
        } else {
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
            page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            page.click(fullDetailsText);
            createCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            createCard.click();
        }
        Thread.sleep(1000);
        
        String actualFirstName = page.textContent(FirstName).trim();
        String actualLastName = page.textContent(LastName).trim();
        String actualEmail = page.textContent(Email).trim();
        String actualproductCategory = page.textContent(productCategory).trim();
        String actualSubCategory = page.textContent(SubCategory).trim();
        String actualcomments = page.textContent(comments).trim();
        
        
        assert actualFirstName.equals(expectedFirstName) 
        : "FirstName mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
        assert actualLastName.equals(expectedLastName) 
        : "LastName mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;
        assert actualEmail.equals(expectedEmail) 
        : "Email mismatch. Expected: " + expectedEmail + ", Got: " + actualEmail;
        assert actualproductCategory.equals(expectedProductCategory) 
        : "productSubCategory mismatch. Expected: " + expectedProductCategory + ", Got: " + actualproductCategory;
        assert actualSubCategory.equals(expectedSubCategory) 
        : "SubCategory mismatch. Expected: " + expectedSubCategory + ", Got: " + actualSubCategory;
        assert actualcomments.equals(expectedRemark) 
        : "comments mismatch. Expected: " + expectedRemark + ", Got: " + actualcomments;
        
     }

    public void EditLeadWithAdditional( String fname, String lname,
             String mail,  String Category,String Subcategory, String note,
            String ReferralType,String ReferralName, String Priority1, String Budget, String ProjectType, String BuyingTime,
    		String Finance, String DOB, String Area, String CompanyName, String EstimatedQuantity, String ProjectTimeline) throws InterruptedException {

EditLeadBasic( fname, lname, mail, Category, Subcategory, note);
page.click("text=Basic Lead Form Details");
Thread.sleep(500);
page.click("text=Additional Form Details");
page.selectOption(referralType, new SelectOption().setLabel(ReferralType));
Thread.sleep(500);
page.click(referralNameField);
Thread.sleep(1000);
page.fill(referralNamesearchBar, ReferralName);
Locator verifyButton = page.locator("//li[@role='option']");
verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
page.waitForCondition(() -> verifyButton.isVisible());
verifyButton.click();
//page.click(clickSearchedFirstOption);
Thread.sleep(500);
page.selectOption(SelectPriority, new SelectOption().setValue(Priority1));
page.selectOption(budget, new SelectOption().setLabel(Budget));
page.selectOption(projectType, new SelectOption().setLabel(ProjectType));
page.click("//input[@value='"+BuyingTime+"']");
page.fill(sourceOfFinance, Finance);
page.click(dob);
page.locator(dob).press("Backspace");
page.press(dob, "Backspace");
Thread.sleep(500);
page.type(dob, DOB);
page.fill(areaLocation, Area);
page.fill(companyName, CompanyName);
page.fill(estimatedQuantity, EstimatedQuantity);
page.fill(projectTimeline, ProjectTimeline);
page.click(submitBtn);
Thread.sleep(1000);
page.click("//div[contains(text(), 'Re-Inquiry')]");
Thread.sleep(2000);
page.click("//div[contains(text(), 'New Lead')]");

}
    public void validateLeadWithAdditionalInfo(String expectedFirstName, String expectedLastName, String expectedEmail, 
    		 String expectedRemark,
    		String expectedReferralType, String expectedreferralName,String expectedbuyingTime,String expectedArea,String expectedProjectType, 
    		String expectedbudget,String expectedpriority,  String expectedFinance, String expectedDOB,String expectedCompanyName, 
    		String expectedEstimatedQuantity,String expectedprojectTimeline, String expectedProductCategory, String expectedProductSubCategory) throws InterruptedException {
    	 Thread.sleep(1000);
    	page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", LeadPhoneNo);
    	Thread.sleep(1000);
         
         Locator verifyButton = page.locator("(//div[@aria-label='View details for "+expectedFirstName+"'])[1]");
    	verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    	page.waitForCondition(() -> verifyButton.isVisible());

        String expectedLeadOwner = page.textContent(SigIn_Name).trim();
        String statusLocator = "(//div[@role='button' and contains(@aria-label,'View details for " + expectedFirstName + "')]//span[contains(@class,'rounded-full')])[1]";
        String leadOwnerLocator = "(//div[@role='button' and contains(@aria-label,'View details for " + expectedFirstName + "')]//span[contains(@class,'rounded-full')])[2]";

         String actualFullName = page.textContent(fullNameText).trim();


         String actualStatus = page.textContent(statusLocator).trim();
         String actualLeadOwner = page.textContent(leadOwnerLocator).trim();
         Thread.sleep(500);
         
         // Assertions
         assert actualFullName.equals(expectedFirstName + " " + expectedLastName) : "Full name mismatch. Expected: " + expectedFirstName + " " + expectedLastName + ", Got: " + actualFullName;
         assert actualStatus.equals("New Inquiry") : "Status mismatch. Expected: New Inquiry, Got: " + actualStatus;
         assert actualLeadOwner.equals(expectedLeadOwner) : "Lead owner mismatch. Expected: " + expectedLeadOwner + ", Got: " + actualLeadOwner;

         System.out.println("Lead saved successfully with Name, Project, Source, Email & Phone validated!");

         // Validate history details (same as previous method)
         page.click(fullDetailsText);
         Thread.sleep(500);
         Locator createCard = page.locator("(//span[text()='Update'])[1]");
         if (createCard.count() > 0) {
             createCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
             createCard.click();
         } else {
             page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
             page.click("div.cursor-pointer.flex.items-center.justify-end");
             page.click("//div[contains(text(),'Re-Inquiry')]");
             page.click("//div[contains(text(),'New Lead')]");
             page.click(fullDetailsText);
             createCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
             createCard.click();
         }
         Thread.sleep(500);
         
         String actualFirstName = page.textContent(FirstName).trim();
         String actualLastName = page.textContent(LastName).trim();
         String actualEmail = page.textContent(Email).trim();
         String actualcomments = page.textContent(comments).trim();
         String actualbuyingTime = page.textContent(buyingTime).trim();
         String actualpriority = page.textContent(Priority).trim();
         String actualbudget = page.textContent(Budget).trim();
        //  String actualprojectCategory = page.textContent(projectCategory).trim();
         String actualareaLocation = page.textContent(area).trim();
         String actualreferralType = page.textContent(ReferralType).trim();
         String actualreferralName = page.textContent(referralName).trim();
         String actualproductCategory = page.textContent(productCategory).trim();
         String actualSubCategory = page.textContent(SubCategory).trim();
         String actualprojectType = page.textContent(ProjectType).trim();
         String actualsourceOfFinances = page.textContent(sourceOfFinanceInHistory).trim();
         String actualdob = page.textContent(dobInHistory).trim();
         String actualcompanyName = page.textContent(companyNameInHistory).trim();
         String actualestimatedQuantity = page.textContent(estimatedQuantityInHistory).trim();
         String actualprojectTimeline = page.textContent(projectTimelineInHistory).trim();
         
        assert actualFirstName.equals(expectedFirstName) 
         : "FirstName mismatch. Expected: " + expectedFirstName + ", Got: " + actualFirstName;
         assert actualLastName.equals(expectedLastName) 
         : "LastName mismatch. Expected: " + expectedLastName + ", Got: " + actualLastName;
         assert actualEmail.equals(expectedEmail) 
         : "Email mismatch. Expected: " + expectedEmail + ", Got: " + actualEmail;
         
         assert actualcomments.equals(expectedRemark) 
         : "comments mismatch. Expected: " + expectedRemark + ", Got: " + actualcomments;
         
         
         assert actualbuyingTime.equals(expectedbuyingTime) 
         : "buying Time mismatch. Expected: " + expectedbuyingTime + ", Got: " + actualbuyingTime;
         assert actualpriority.equals(expectedpriority) 
         : "priority mismatch. Expected: " + expectedpriority + ", Got: " + actualpriority;
         assert actualbudget.equals(expectedbudget) 
         : "budget mismatch. Expected: " + expectedbudget + ", Got: " + actualbudget;
         
         assert actualareaLocation.equals(expectedArea) 
         : "areaLocation mismatch. Expected: " + expectedArea + ", Got: " + actualareaLocation;
         assert actualreferralType.equals(expectedReferralType) 
         : "referralType mismatch. Expected: " + expectedReferralType + ", Got: " + actualreferralType;
         assert actualreferralName.equals(expectedreferralName) 
         : "referralName mismatch. Expected: " + expectedreferralName + ", Got: " + actualreferralName;
         
         assert actualproductCategory.equals(expectedProductCategory) 
         : "productSubCategory mismatch. Expected: " + expectedProductCategory + ", Got: " + actualproductCategory;
         assert actualSubCategory.equals(expectedProductSubCategory) 
         : "SubCategory mismatch. Expected: " + expectedProductSubCategory + ", Got: " + actualSubCategory;
         
         assert actualprojectType.equals(expectedProjectType) 
         : "projectType mismatch. Expected: " + expectedProjectType + ", Got: " + actualprojectType;
         assert actualsourceOfFinances.equals(expectedFinance) 
         : "sourceOfFinances mismatch. Expected: " + expectedFinance + ", Got: " + actualsourceOfFinances;
        Thread.sleep(500);
        System.out.println(actualdob);
         assert actualdob.equals(expectedDOB) 
        : "dob mismatch. Expected: " + expectedDOB + ", Got: " + actualdob;
        
         assert actualcompanyName.equals(expectedCompanyName) 
         : "companyName mismatch. Expected: " + expectedCompanyName + ", Got: " + actualcompanyName;
         assert actualestimatedQuantity.equals(expectedEstimatedQuantity) 
         : "estimatedQuantity mismatch. Expected: " + expectedEstimatedQuantity + ", Got: " + actualestimatedQuantity;
         assert actualprojectTimeline.equals(expectedprojectTimeline) 
         : "projectTimeline mismatch. Expected: " + expectedprojectTimeline + ", Got: " + actualprojectTimeline;
         }
    public void EditLeadBasic( String fname, String lname, String mail, 
    		                  String Category, String SubCategory, String note) throws InterruptedException {
    	Thread.sleep(1000);
    	page.click(followupMenu);
        Thread.sleep(1000);
        page.click(openAddLeadOptions);
        page.click(selectFurnitureOption);
        Thread.sleep(1000);
        // String ExpectedLeadOwner = page.textContent(SigIn_Name).trim();
        // String leadOwner = "(//div[@role='button' and contains(@aria-label,'View details for "+ExpectedLeadOwner+"')]//span[contains(@class,'rounded-full')])[2]";
    	// page.click(leadOwner);
        String getPhoneNo = page.textContent(phoneNumber).trim();
        String phonePart = getPhoneNo.replace("Mobile no -", "").trim();
        String[] parts = phonePart.split(" ", 2); 
        LeadPhoneNo = parts[1]; 
        System.out.println("LeadPhoneNo: "+LeadPhoneNo);
    	page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", LeadPhoneNo);
    	Thread.sleep(1000);
        page.click(phoneNumber);
        Thread.sleep(1000);
        page.click(EditLeadBtn);
        Thread.sleep(1000);
        page.fill(firstName, fname);
        page.fill(lastName, lname);
        page.fill(email, mail);
        page.selectOption(Product_Category, new SelectOption().setValue(Category));
        page.selectOption(subCategory, new SelectOption().setLabel(SubCategory));
        page.fill(remark, note);
    }
   
}
