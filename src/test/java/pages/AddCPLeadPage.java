package pages;

import java.io.File;
import java.nio.file.Paths;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AddCPLeadPage {
   private Page page;

 // Basic add lead Locators
    private String addLeadBtn = "//button[normalize-space()='+ Add Lead']";
    private String firstName = "#firstName";
    private String lastName = "#lastName";
    private String countryCode = "(//div[@aria-label='Select Country Code'])[1]";
    private String searchCountryCode = "(//input[@aria-label='Search for a country'])";
    private String email = "#email";
    private String Address = "#address";
    private String leadPhoneNumber = "#mobileNumber";
    private String tier = "#tier";
    private String channelRole = "#channelRole";
    private String path = "(//label[@for='reraCertificate'])";
    private String Individual = "//input[@value='Individual']";
    private String Company = "//input[@value='Company']";
    private String CompanyNameField = "#companyName";
    private String submitBtn = "//button[@type='submit']";

    // Locators for Lead card
    private String leadDate = "(//div[@class='text-sm text-gray-500 font-medium'])[1]";
    private String LeadName = "(//h3[@class='font-semibold text-lg text-gray-800 break-words'])[1]";
    private String leadMobile = "(//p[@class='text-gray-500 text-base pt-1 mb-2'])[1]";
    private String Stage = "(//span[@class='px-2 py-1 text-xs font-medium rounded-full bg-gray-100 text-gray-700'])[1]";
    private String LeadOwner = "(//span[@class='px-2 py-1 text-xs font-medium rounded-full bg-gray-100 text-gray-700'])[1]";
     
    // Locators for  history card
    private String CreatedBY_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm'])[1]";
    private String createDate_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm'])[2]";
    private String ActionType_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm'])[3]";
    private String FirstName_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[1]";
    private String LastName_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[2]";
    private String Email_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[3]";
    private String CountryCode_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[4]";
    private String PhoneNumber_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[5]";
    private String Address_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[6]";
    private String ChannelRole_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[7]";
    private String ReraCertificate_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[8]";
    private String Type_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[9]";
    private String CompanyName_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[10]";
    private String SalesType_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[11]";
    private String SourceName_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[12]";
    private String SubSourceName_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[13]";
    private String Tier_InHistoryCard = "(//p[@class='font-medium text-xs sm:text-sm truncate'])[13]";
    
    private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
    private String selectCPOption = "//div[text()='CP']";
    private String SigIn_Name="(//p[@class='mantine-focus-auto m_b6d8b162 mantine-Text-root'])[1]";
    
    
    public AddCPLeadPage(Page page) {
        this.page = page;
    }
    public void add_and_Validate_Individual_CPLead_With_Non_Registered(String fname, String lname, String country,String phone,String mail, String address, String Tier, String ChannelRole) throws InterruptedException {
    	addCPLead(fname,lname,country,phone,mail,address,Tier);
        page.selectOption(channelRole, new SelectOption().setLabel(ChannelRole));
        page.click(Individual);
        page.click(submitBtn);
       Thread.sleep(1000);
        page.click("//div[contains(text(), 'Untouched')]");
        page.click("//div[contains(text(), 'New')]");
        
        // --- Validation method ---
        Thread.sleep(3000); 
    	Locator checkNewLeadCard = page.locator("(//div[@aria-label='View details for " + fname + "'])[1]");
        
        if (checkNewLeadCard.count() > 0) {
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            Thread.sleep(500);
            checkNewLeadCard.click();
        } else {
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            checkNewLeadCard.click();
        }
    	String ExpectedLeadOwner = page.textContent(SigIn_Name).trim();
    	
    	String status = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[1]";
    	String leadOwner = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[2]";
    	
        
    	String actualFullName = page.textContent(LeadName).trim();
        String actualPhone = page.textContent(leadMobile).trim();
        String phonePart = actualPhone.replaceAll("(?i)Mobile No -", "").trim();


    // Split into country code and number
    String[] parts = phonePart.split(" ", 2);
    String countryCode = parts[0];      
    String phoneNumber = parts[1];
    
    String actualStatus = page.textContent(status).trim();
    String actualLeadOwner = page.textContent(leadOwner).trim();
    

        assert actualFullName.equals(fname + " " + lname) 
            : "Full name mismatch. Expected: " + fname + " " + lname + ", Got: " + actualFullName;
        
        assert countryCode.equals(country) 
            : "Country code mismatch. Expected: "+ country + ", Got: " + countryCode;
        assert phoneNumber.equals(phone) 
            : "Phone number mismatch. Expected: "+ phone + ", Got: " + phoneNumber;
        
        assert actualStatus.equals("Registration") 
        : "Stage mismatch. Expected: "+ "Registration" + ", Got: " + actualStatus;
        
        // assert actualLeadOwner.equals(ExpectedLeadOwner) 
        // : "Lead owner mismatch. Expected: "+ ExpectedLeadOwner + ", Got: " + actualLeadOwner;
        
        System.out.println("Lead saved successfully with Name, Project, Source and Mobile number validated!");
        

        
//        Lead details on history 
        Thread.sleep(500);
        page.click(LeadName);
       
      
        Locator verifyButton = page.locator("//span[text()='Create']");
        if (verifyButton.count() > 0) {
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        } else {
            System.out.println("Verify button not found, reloading page...");
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            page.click(LeadName);
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        }
        Thread.sleep(500);
               
        String actualCreatedByinHistory =page.textContent(CreatedBY_InHistoryCard).trim();
        String actualCreatedDate =page.textContent(createDate_InHistoryCard).trim();
        String actualActionType =page.textContent(ActionType_InHistoryCard).trim();

        String actualfirstName =page.textContent(FirstName_InHistoryCard).trim();
        String actuallastName =page.textContent(LastName_InHistoryCard).trim();
        String actualEmail = page.textContent(Email_InHistoryCard).trim();
        String actualCountryCode = page.textContent(CountryCode_InHistoryCard).trim();
        String actualPhoneNumber = page.textContent(PhoneNumber_InHistoryCard).trim();
        String actualAddress = page.textContent(Address_InHistoryCard).trim();
        String actualChannelRole =page.textContent(ChannelRole_InHistoryCard).trim();
        String actualReraCertificate =page.textContent(ReraCertificate_InHistoryCard).trim();
        String actualType =page.textContent(Type_InHistoryCard).trim();
        String actualCompanyName =page.textContent(CompanyName_InHistoryCard).trim();
        String actualSalesType =page.textContent(SalesType_InHistoryCard).trim();
        String actualSourceName = page.textContent(SourceName_InHistoryCard).trim();
        String actualSubSourcename = page.textContent(SubSourceName_InHistoryCard).trim();
        String actualTier = page.textContent(Tier_InHistoryCard).trim();

        // assert actualCreatedByinHistory.equals(ExpectedLeadOwner) 
        // : "Full name mismatch. Expected: " + ExpectedLeadOwner + ", Got: " + actualCreatedByinHistory;
        // // assert actualCreatedDate.equals(lname) 
        // // : "Full name mismatch. Expected: " + lname + ", Got: " + actualCreatedDate;
        // assert actualActionType.equals("Create") 
        // : "Full name mismatch. Expected: " + "Create" + ", Got: " + actualActionType;
        
        assert actualfirstName.equals(fname) 
        : "first name mismatch. Expected: " + fname + ", Got: " + actualfirstName;
        assert actuallastName.equals(lname) 
        : "llast name mismatch. Expected: " + lname + ", Got: " + actuallastName;
        assert actualEmail.equals(mail) 
        : "Email name mismatch. Expected: " + mail + ", Got: " + actualEmail;
        assert actualCountryCode.equals(country) 
        : "CountryCode mismatch. Expected: " + country + ", Got: " + actualCountryCode;
        assert actualPhoneNumber.equals(phone) 
        : "PhoneNumber mismatch. Expected: " + phone + ", Got: " + actualPhoneNumber;
        assert actualAddress.equals(address) 
        : "Address mismatch. Expected: " + address + ", Got: " + actualAddress;
        // assert actualChannelRole.equals(ChannelRole) 
        // : "Full name mismatch. Expected: " + ChannelRole + ", Got: " + actualChannelRole;
        assert actualReraCertificate.equals("N/A") 
        : "Rera Certificate mismatch. Expected: " + "N/A" + ", Got: " + actualReraCertificate;
        assert actualType.equals("Individual") 
        : "Type mismatch. Expected: " + "Individual" + ", Got: " + actualType;
        assert actualCompanyName.equals("N/A") 
        : "Company Name mismatch. Expected: " + "N/A" + ", Got: " + actualCompanyName;
        assert actualSalesType.equals("CP") 
        : "Sales Type mismatch. Expected: " + "CP" + ", Got: " + actualSalesType;
        assert actualSourceName.equals("Digital") 
        : "Source Name mismatch. Expected: " + "Digital" + ", Got: " + actualSourceName;
        assert actualSubSourcename.equals("Admin") 
        : "Sub Source name mismatch. Expected: " + "Admin" + ", Got: " + actualSubSourcename;
        // assert actualTier.equals(Tier) 
        // : "Tier mismatch. Expected: " + Tier + ", Got: " + actualTier;
        
        }
    public void add_Individual_CPLead_With_Registered(String fname, String lname, String country, String phone, String mail, String address, String Tier, String ChannelRole, String filepath,String date) throws InterruptedException {
    	addCPLead(fname,lname,country,phone,mail,address,Tier);
        page.selectOption(channelRole, new SelectOption().setLabel(ChannelRole));
        //Upload file
        page.setInputFiles(path, Paths.get(filepath));
        page.getByText("DD/MM/YYYY").click();
        page.keyboard().type(date);
        page.click(Individual);
        page.click(submitBtn);
        Thread.sleep(1000);
        page.click("//div[contains(text(), 'Untouched')]");
        page.click("//div[contains(text(), 'New')]");
        
        // --- Validation method ---
        Thread.sleep(3000); 
    	Locator checkNewLeadCard = page.locator("(//div[@aria-label='View details for " + fname + "'])[1]");
        
        if (checkNewLeadCard.count() > 0) {
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            Thread.sleep(500);
            checkNewLeadCard.click();
        } else {
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            checkNewLeadCard.click();
        }
    	String ExpectedLeadOwner = page.textContent(SigIn_Name).trim();
    	
    	String status = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[1]";
    	String leadOwner = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[2]";
    	
        
    	String actualFullName = page.textContent(LeadName).trim();
        String actualPhone = page.textContent(leadMobile).trim();
        String phonePart = actualPhone.replaceAll("(?i)Mobile No -", "").trim();


    // Split into country code and number
    String[] parts = phonePart.split(" ", 2);
    String countryCode = parts[0];      
    String phoneNumber = parts[1];
    
    String actualStatus = page.textContent(status).trim();
    String actualLeadOwner = page.textContent(leadOwner).trim();
    

        assert actualFullName.equals(fname + " " + lname) 
            : "Full name mismatch. Expected: " + fname + " " + lname + ", Got: " + actualFullName;
        
        assert countryCode.equals(country) 
            : "Country code mismatch. Expected: "+ country + ", Got: " + countryCode;
        assert phoneNumber.equals(phone) 
            : "Phone number mismatch. Expected: "+ phone + ", Got: " + phoneNumber;
        
        assert actualStatus.equals("Registration") 
        : "Stage mismatch. Expected: "+ "Registration" + ", Got: " + actualStatus;
        
        // assert actualLeadOwner.equals(ExpectedLeadOwner) 
        // : "Lead owner mismatch. Expected: "+ ExpectedLeadOwner + ", Got: " + actualLeadOwner;
        
        System.out.println("Lead saved successfully with Name, Project, Source and Mobile number validated!");
        

        
//        Lead details on history 
        Thread.sleep(500);
        page.click(LeadName);
       
      
        Locator verifyButton = page.locator("//span[text()='Create']");
        if (verifyButton.count() > 0) {
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        } else {
            System.out.println("Verify button not found, reloading page...");
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            page.click(LeadName);
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        }
        Thread.sleep(500);
               
        String actualCreatedByinHistory =page.textContent(CreatedBY_InHistoryCard).trim();
        String actualCreatedDate =page.textContent(createDate_InHistoryCard).trim();
        String actualActionType =page.textContent(ActionType_InHistoryCard).trim();

        String actualfirstName =page.textContent(FirstName_InHistoryCard).trim();
        String actuallastName =page.textContent(LastName_InHistoryCard).trim();
        String actualEmail = page.textContent(Email_InHistoryCard).trim();
        String actualCountryCode = page.textContent(CountryCode_InHistoryCard).trim();
        String actualPhoneNumber = page.textContent(PhoneNumber_InHistoryCard).trim();
        String actualAddress = page.textContent(Address_InHistoryCard).trim();
        String actualChannelRole =page.textContent(ChannelRole_InHistoryCard).trim();
        String actualReraCertificate =page.textContent(ReraCertificate_InHistoryCard).trim();
        String actualType =page.textContent(Type_InHistoryCard).trim();
        String actualCompanyName =page.textContent(CompanyName_InHistoryCard).trim();
        String actualSalesType =page.textContent(SalesType_InHistoryCard).trim();
        String actualSourceName = page.textContent(SourceName_InHistoryCard).trim();
        String actualSubSourcename = page.textContent(SubSourceName_InHistoryCard).trim();
        String actualTier = page.textContent(Tier_InHistoryCard).trim();

        File f1 = new File(filepath);
        String expectedFileName1 = f1.getName(); 

        String[] rera = actualReraCertificate.split("_", 2);
        String actualReraCertificateName = rera.length > 1 ? rera[1] : actualReraCertificate;

        // assert actualCreatedByinHistory.equals(fname) 
        // : "Full name mismatch. Expected: " + fname + ", Got: " + actualCreatedByinHistory;
        // assert actualCreatedDate.equals(lname) 
        // : "Full name mismatch. Expected: " + lname + ", Got: " + actualCreatedDate;
        // assert actualActionType.equals(mail) 
        // : "Full name mismatch. Expected: " + mail + ", Got: " + actualActionType;
        
        assert actualfirstName.equals(fname) 
        : "first name mismatch. Expected: " + fname + ", Got: " + actualfirstName;
        assert actuallastName.equals(lname) 
        : "Last name mismatch. Expected: " + lname + ", Got: " + actuallastName;
        assert actualEmail.equals(mail) 
        : "Email mismatch. Expected: " + mail + ", Got: " + actualEmail;
        assert actualCountryCode.equals(country) 
        : "Country Code mismatch. Expected: " + country + ", Got: " + actualCountryCode;
        assert actualPhoneNumber.equals(phone) 
        : "Phone Number mismatch. Expected: " + phone + ", Got: " + actualPhoneNumber;
        assert actualAddress.equals(address) 
        : "Address mismatch. Expected: " + address + ", Got: " + actualAddress;
        // assert actualChannelRole.equals(ChannelRole) 
        // : "Full name mismatch. Expected: " + ChannelRole + ", Got: " + actualChannelRole;

        assert actualReraCertificateName.equals(expectedFileName1) 
        : "Rera Certificate Name mismatch! Expected: " + expectedFileName1 + ", Got: " + actualReraCertificateName;
        assert actualType.equals("Individual") 
        : "Type mismatch. Expected: " + "Individual" + ", Got: " + actualType;
        assert actualCompanyName.equals("N/A") 
        : "Company Name mismatch. Expected: " + "N/A" + ", Got: " + actualCompanyName;
        assert actualSalesType.equals("CP") 
        : "Sales Type mismatch. Expected: " + "CP" + ", Got: " + actualSalesType;
        assert actualSourceName.equals("Digital") 
        : "Source Name mismatch. Expected: " + "Digital" + ", Got: " + actualSourceName;
        assert actualSubSourcename.equals("Admin") 
        : "Sub Source name mismatch. Expected: " + "Admin" + ", Got: " + actualSubSourcename;
        // assert actualTier.equals(Tier) 
        // : "Tier mismatch. Expected: " + Tier + ", Got: " + actualTier;
        
        }

    public void add_and_Validate_Company_CPLead_With_Non_Registered(String fname, String lname, String country, String phone, String mail, String address, String Tier, String ChannelRole, String Company_Name) throws InterruptedException {
    	addCPLead(fname,lname,country,phone,mail,address,Tier);
        page.selectOption(channelRole, new SelectOption().setLabel(ChannelRole));
        page.click(Company);
        page.fill(CompanyNameField, Company_Name);
        page.click(submitBtn);
        Thread.sleep(1000);
        page.click("//div[contains(text(), 'Untouched')]");
        page.click("//div[contains(text(), 'New')]");
        
        // --- Validation method ---
        Thread.sleep(3000); 
    	Locator checkNewLeadCard = page.locator("(//div[@aria-label='View details for " + fname + "'])[1]");
        
        if (checkNewLeadCard.count() > 0) {
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            Thread.sleep(500);
            checkNewLeadCard.click();
        } else {
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            checkNewLeadCard.click();
        }
    	String ExpectedLeadOwner = page.textContent(SigIn_Name).trim();
    	
    	String status = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[1]";
    	String leadOwner = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[2]";
    	
        
    	String actualFullName = page.textContent(LeadName).trim();
        String actualPhone = page.textContent(leadMobile).trim();
        String phonePart = actualPhone.replaceAll("(?i)Mobile No -", "").trim();


    // Split into country code and number
    String[] parts = phonePart.split(" ", 2);
    String countryCode = parts[0];      
    String phoneNumber = parts[1];
    
    String actualStatus = page.textContent(status).trim();
    String actualLeadOwner = page.textContent(leadOwner).trim();
    

        assert actualFullName.equals(fname + " " + lname) 
            : "Full name mismatch. Expected: " + fname + " " + lname + ", Got: " + actualFullName;
        
        assert countryCode.equals(country) 
            : "Country code mismatch. Expected: "+ country + ", Got: " + countryCode;
        assert phoneNumber.equals(phone) 
            : "Phone number mismatch. Expected: "+ phone + ", Got: " + phoneNumber;
        
        assert actualStatus.equals("Registration") 
        : "Stage mismatch. Expected: "+ "Registration" + ", Got: " + actualStatus;
        
        // assert actualLeadOwner.equals(ExpectedLeadOwner) 
        // : "Lead owner mismatch. Expected: "+ ExpectedLeadOwner + ", Got: " + actualLeadOwner;
        
        System.out.println("Lead saved successfully with Name, Project, Source and Mobile number validated!");
        

        
//        Lead details on history 
        Thread.sleep(500);
        page.click(LeadName);
       
      
        Locator verifyButton = page.locator("//span[text()='Create']");
        if (verifyButton.count() > 0) {
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        } else {
            System.out.println("Verify button not found, reloading page...");
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            page.click(LeadName);
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        }
        Thread.sleep(500);
               
        String actualCreatedByinHistory =page.textContent(CreatedBY_InHistoryCard).trim();
        String actualCreatedDate =page.textContent(createDate_InHistoryCard).trim();
        String actualActionType =page.textContent(ActionType_InHistoryCard).trim();

        String actualfirstName =page.textContent(FirstName_InHistoryCard).trim();
        String actuallastName =page.textContent(LastName_InHistoryCard).trim();
        String actualEmail = page.textContent(Email_InHistoryCard).trim();
        String actualCountryCode = page.textContent(CountryCode_InHistoryCard).trim();
        String actualPhoneNumber = page.textContent(PhoneNumber_InHistoryCard).trim();
        String actualAddress = page.textContent(Address_InHistoryCard).trim();
        String actualChannelRole =page.textContent(ChannelRole_InHistoryCard).trim();
        String actualReraCertificate =page.textContent(ReraCertificate_InHistoryCard).trim();
        String actualType =page.textContent(Type_InHistoryCard).trim();
        String actualCompanyName =page.textContent(CompanyName_InHistoryCard).trim();
        String actualSalesType =page.textContent(SalesType_InHistoryCard).trim();
        String actualSourceName = page.textContent(SourceName_InHistoryCard).trim();
        String actualSubSourcename = page.textContent(SubSourceName_InHistoryCard).trim();
        String actualTier = page.textContent(Tier_InHistoryCard).trim();

        // assert actualCreatedByinHistory.equals(fname) 
        // : "Full name mismatch. Expected: " + fname + ", Got: " + actualCreatedByinHistory;
        // assert actualCreatedDate.equals(lname) 
        // : "Full name mismatch. Expected: " + lname + ", Got: " + actualCreatedDate;
        // assert actualActionType.equals(mail) 
        // : "Full name mismatch. Expected: " + mail + ", Got: " + actualActionType;
        
        assert actualfirstName.equals(fname) 
        : "First name mismatch. Expected: " + fname + ", Got: " + actualfirstName;
        assert actuallastName.equals(lname) 
        : "Last name mismatch. Expected: " + lname + ", Got: " + actuallastName;
        assert actualEmail.equals(mail) 
        : "Email mismatch. Expected: " + mail + ", Got: " + actualEmail;
        assert actualCountryCode.equals(country) 
        : "Country Code mismatch. Expected: " + country + ", Got: " + actualCountryCode;
        assert actualPhoneNumber.equals(phone) 
        : "Phone Number mismatch. Expected: " + phone + ", Got: " + actualPhoneNumber;
        assert actualAddress.equals(address) 
        : "Address mismatch. Expected: " + address + ", Got: " + actualAddress;
        // assert actualChannelRole.equals(ChannelRole) 
        // : "Full name mismatch. Expected: " + ChannelRole + ", Got: " + actualChannelRole;
        assert actualReraCertificate.equals("N/A") 
        : "ReraCertificate name mismatch. Expected: " + "N/A" + ", Got: " + actualReraCertificate;
        assert actualType.equals("Company") 
        : "Type mismatch. Expected: " + "Company" + ", Got: " + actualType;
        assert actualCompanyName.equals(Company_Name) 
        : "Company Name mismatch. Expected: " + Company_Name + ", Got: " + actualCompanyName;
        assert actualSalesType.equals("CP") 
        : "FSales Type mismatch. Expected: " + "CP" + ", Got: " + actualSalesType;
        assert actualSourceName.equals("Digital") 
        : "Source Name mismatch. Expected: " + "Digital" + ", Got: " + actualSourceName;
        assert actualSubSourcename.equals("Admin") 
        : "Sub Source name mismatch. Expected: " + "Admin" + ", Got: " + actualSubSourcename;
        // assert actualTier.equals(Tier) 
        // : "Tier mismatch. Expected: " + Tier + ", Got: " + actualTier;
        
        }
        
    public void add_Individual_Company_CPLead_With_Registered(String fname, String lname, String country, String phone, String mail,String address, String Tier, String ChannelRole, String Company_Name,String filepath, String date) throws InterruptedException {
    	addCPLead(fname,lname,country,phone,mail,address,Tier);
        page.selectOption(channelRole, new SelectOption().setLabel(ChannelRole));
        //Upload file
        page.setInputFiles(path, Paths.get(filepath));
        page.getByText("DD/MM/YYYY").click();
        page.keyboard().type(date);
        page.click(Company);
        page.fill(CompanyNameField, Company_Name);
        page.click(submitBtn);
        Thread.sleep(1000);
        page.click("//div[contains(text(), 'Untouched')]");
        page.click("//div[contains(text(), 'New')]");
        
        // --- Validation method ---
        Thread.sleep(3000); 
    	Locator checkNewLeadCard = page.locator("(//div[@aria-label='View details for " + fname + "'])[1]");
        
        if (checkNewLeadCard.count() > 0) {
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            Thread.sleep(500);
            checkNewLeadCard.click();
        } else {
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            checkNewLeadCard.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            checkNewLeadCard.click();
        }
    	String ExpectedLeadOwner = page.textContent(SigIn_Name).trim();
    	
    	String status = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[1]";
    	String leadOwner = "(//div[@role='button' and contains(@aria-label,'View details for "+fname+"')]//span[contains(@class,'rounded-full')])[2]";
    	
        
    	String actualFullName = page.textContent(LeadName).trim();
        String actualPhone = page.textContent(leadMobile).trim();
        String phonePart = actualPhone.replaceAll("(?i)Mobile No -", "").trim();


    // Split into country code and number
    String[] parts = phonePart.split(" ", 2);
    String countryCode = parts[0];      
    String phoneNumber = parts[1];
    
    String actualStatus = page.textContent(status).trim();
    String actualLeadOwner = page.textContent(leadOwner).trim();
    

        assert actualFullName.equals(fname + " " + lname) 
            : "Full name mismatch. Expected: " + fname + " " + lname + ", Got: " + actualFullName;
        
        assert countryCode.equals(country) 
            : "Country code mismatch. Expected: "+ country + ", Got: " + countryCode;
        assert phoneNumber.equals(phone) 
            : "Phone number mismatch. Expected: "+ phone + ", Got: " + phoneNumber;
        
        assert actualStatus.equals("Registration") 
        : "Stage mismatch. Expected: "+ "Registration" + ", Got: " + actualStatus;
        
        // assert actualLeadOwner.equals(ExpectedLeadOwner) 
        // : "Lead owner mismatch. Expected: "+ ExpectedLeadOwner + ", Got: " + actualLeadOwner;
        
        System.out.println("Lead saved successfully with Name, Project, Source and Mobile number validated!");
        

        
//        Lead details on history 
        Thread.sleep(500);
        page.click(LeadName);
       
      
        Locator verifyButton = page.locator("//span[text()='Create']");
        if (verifyButton.count() > 0) {
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        } else {
            System.out.println("Verify button not found, reloading page...");
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.click("//div[contains(text(),'Re-Inquiry')]");
            page.click("//div[contains(text(),'New Lead')]");
            page.click(LeadName);
            verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            verifyButton.click();
        }
        Thread.sleep(500);
               
        String actualCreatedByinHistory =page.textContent(CreatedBY_InHistoryCard).trim();
        String actualCreatedDate =page.textContent(createDate_InHistoryCard).trim();
        String actualActionType =page.textContent(ActionType_InHistoryCard).trim();

        String actualfirstName =page.textContent(FirstName_InHistoryCard).trim();
        String actuallastName =page.textContent(LastName_InHistoryCard).trim();
        String actualEmail = page.textContent(Email_InHistoryCard).trim();
        String actualCountryCode = page.textContent(CountryCode_InHistoryCard).trim();
        String actualPhoneNumber = page.textContent(PhoneNumber_InHistoryCard).trim();
        String actualAddress = page.textContent(Address_InHistoryCard).trim();
        String actualChannelRole =page.textContent(ChannelRole_InHistoryCard).trim();
        String actualReraCertificate =page.textContent(ReraCertificate_InHistoryCard).trim();
        String actualType =page.textContent(Type_InHistoryCard).trim();
        String actualCompanyName =page.textContent(CompanyName_InHistoryCard).trim();
        String actualSalesType =page.textContent(SalesType_InHistoryCard).trim();
        String actualSourceName = page.textContent(SourceName_InHistoryCard).trim();
        String actualSubSourcename = page.textContent(SubSourceName_InHistoryCard).trim();
        String actualTier = page.textContent(Tier_InHistoryCard).trim();

        File f1 = new File(filepath);
        String expectedFileName1 = f1.getName(); 

        String[] rera = actualReraCertificate.split("_", 2);
        String actualReraCertificateName = rera.length > 1 ? rera[1] : actualReraCertificate;

        // assert actualCreatedByinHistory.equals(fname) 
        // : "Full name mismatch. Expected: " + fname + ", Got: " + actualCreatedByinHistory;
        // assert actualCreatedDate.equals(lname) 
        // : "Full name mismatch. Expected: " + lname + ", Got: " + actualCreatedDate;
        // assert actualActionType.equals(mail) 
        // : "Full name mismatch. Expected: " + mail + ", Got: " + actualActionType;
        
        assert actualfirstName.equals(fname) 
        : "first name mismatch. Expected: " + fname + ", Got: " + actualfirstName;
        assert actuallastName.equals(lname) 
        : "Last name mismatch. Expected: " + lname + ", Got: " + actuallastName;
        assert actualEmail.equals(mail) 
        : "Email mismatch. Expected: " + mail + ", Got: " + actualEmail;
        assert actualCountryCode.equals(country) 
        : "Country Code mismatch. Expected: " + country + ", Got: " + actualCountryCode;
        assert actualPhoneNumber.equals(phone) 
        : "Phone Number mismatch. Expected: " + phone + ", Got: " + actualPhoneNumber;
        assert actualAddress.equals(address) 
        : "Address mismatch. Expected: " + address + ", Got: " + actualAddress;
        // assert actualChannelRole.equals(ChannelRole) 
        // : "Full name mismatch. Expected: " + ChannelRole + ", Got: " + actualChannelRole;
        assert actualReraCertificateName.equals(expectedFileName1) 
        : "Rera Certificate Name mismatch! Expected: " + expectedFileName1 + ", Got: " + actualReraCertificateName;
        assert actualType.equals("Company") 
        : "Type mismatch. Expected: " + "Company" + ", Got: " + actualType;
        assert actualCompanyName.equals(Company_Name) 
        : "Company Name mismatch. Expected: " + Company_Name + ", Got: " + actualCompanyName;
        assert actualSalesType.equals("CP") 
        : "Sales Type mismatch. Expected: " + "CP" + ", Got: " + actualSalesType;
        assert actualSourceName.equals("Digital") 
        : "Source Name mismatch. Expected: " + "Digital" + ", Got: " + actualSourceName;
        assert actualSubSourcename.equals("Admin") 
        : "Sub Source name mismatch. Expected: " + "Admin" + ", Got: " + actualSubSourcename;
        // assert actualTier.equals(Tier) 
        // : "Tier mismatch. Expected: " + Tier + ", Got: " + actualTier;
        
        }

    public void addCPLead(String fname, String lname, String country, String phone, String mail, String address, String Tier) throws InterruptedException {
    	Thread.sleep(1000);
        page.waitForSelector(followupMenu, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click(followupMenu);
        Thread.sleep(1000);
        page.click(openAddLeadOptions);
        page.click(selectCPOption);
        page.click(addLeadBtn);
        Thread.sleep(1500);
        page.fill(firstName, fname);
        page.fill(lastName, lname);
        page.click(countryCode);
        page.fill(searchCountryCode, country);
        Thread.sleep(500);
        page.click("//li[@role='option']");
        page.fill(leadPhoneNumber, phone);
        page.fill(email, mail);
        page.fill(Address, address);
        page.selectOption(tier, new SelectOption().setLabel(Tier));
        
        }
    
}

