package pages;

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
    
    
    private String followupMenu = "(//p[@data-size='sm'])[4]";
    private String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
    private String selectCPOption = "//div[text()='CP']";
    
    public AddCPLeadPage(Page page) {
        this.page = page;
    }
    public void add_Individual_CPLead_With_Non_Registered(String fname, String lname, String country,String phone, String mail, String address, String Tier, String ChannelRole) throws InterruptedException {
    	addCPLead(fname,lname,country,phone,mail,address,Tier);
        page.selectOption(channelRole, new SelectOption().setLabel(ChannelRole));
        page.click(Individual);
        page.click(submitBtn);
        Thread.sleep(1000);
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
        }

    public void add_Company_CPLead_With_Non_Registered(String fname, String lname, String country, String phone, String mail, String address, String Tier, String ChannelRole, String Company_Name) throws InterruptedException {
    	addCPLead(fname,lname,country,phone,mail,address,Tier);
        page.selectOption(channelRole, new SelectOption().setLabel(ChannelRole));
        page.click(Company);
        page.fill(CompanyNameField, Company_Name);
        page.click(submitBtn);
        Thread.sleep(1000);
        }
        
    public void add_Company_CPLead_With_Registered(String fname, String lname, String country, String phone, String mail,String address, String Tier, String ChannelRole, String Company_Name,String filepath, String date) throws InterruptedException {
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

