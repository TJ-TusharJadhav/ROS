package pages;

import com.microsoft.playwright.Page;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Designation_Department_BranchPage {
     private final Page page;

     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
     String timestamp = LocalDateTime.now().format(formatter);

     // Designation
    private final String Designation = "//div[normalize-space(text())='Designation']";
    private final String AddDesignationBtn = "//button[normalize-space(text())='+ Add Designation']";
    private final String SearchBar = "//input[@type='text']";
    private final String EnterText = "(//input[@type='text'])[2]";
    private final String SubmitBtn = "//button[@type='submit']";
    private final String validatedesignation = "//div[contains(@class,'designation-card')]//h3/text()";
    private final String validatebranchcard  = "//div[contains(@class,'branch-card ')]//h3/text()";
    private final String validatedepartmeny = "//div[contains(@class,'department-card ')]//h3/text()";
    private final String validateseatingOffice = "//div[@role='button']";

    // Department
    private final String Department = "//div[normalize-space(text())='Department']";
    private final String AddDepartmentBtn = "//button[normalize-space(text())='+ Add Department']";

     // Branch
    private final String Branch = "//div[normalize-space(text())='Branch']";
    private final String AddBranchBtn = "//button[normalize-space(text())='+ Add Branch']";

    //seating office
    private final String seatingOffice = "//div[normalize-space(text())='Seating Office']";
    private final String Add_seatingOffice = "//button[normalize-space(text())='+ Add Seating Office']";
    private final String Name = "#seatingOfficeName";
    private final String ShortName = "#shortName";
    private final String Address = "#address";
    private final String City = "#city";
    private final String State = "#state";
    private final String Country = "#country";
    private final String MapLink = "#mapLink";
    private final String Latitude = "#lat";
    private final String Longitude = "#long";
    private final String AllowRange = "#allowRange";


    public Designation_Department_BranchPage(Page page) {
        this.page = page;
    } 
    public void AddSeatingOffice() throws InterruptedException{
        String ExpectedName = "SeatingOffice" + "_" + timestamp;
        String ExpectedShortName = "SO";
        String ExpectedAddress="Shivalik HO";
        String ExpectedCity = "Ahmedabad";
        String ExpectedState="Gujrat";
        String ExpectedCountry = "India";
        String ExpectedLink="https://maps.app.goo.gl/HFFEJM5xLfDN782M8";
        String ExpectedLat = "23.0275739";
        String ExpectedLong="72.5106497";
        String ExpectedRange="60";

        page.click(seatingOffice);Thread.sleep(500);
        page.click(Add_seatingOffice);Thread.sleep(500);
        page.fill(Name, ExpectedName);
        page.fill(ShortName, ExpectedShortName);
        page.fill(Address, ExpectedAddress);
        page.fill(City, ExpectedCity);
        page.fill(State, ExpectedState);
        page.fill(Country, ExpectedCountry);
        page.fill(MapLink, ExpectedLink);
        page.fill(Latitude, ExpectedLat);
        page.fill(Longitude, ExpectedLong);
        page.fill(AllowRange, ExpectedRange);Thread.sleep(500);
        page.click(SubmitBtn);Thread.sleep(500);
        page.fill(SearchBar, ExpectedName);Thread.sleep(1000);
        page.isVisible(validateseatingOffice);
        page.click(validateseatingOffice); Thread.sleep(1000);
        
        // validation 
String actualName = page.inputValue(Name).trim();
String actualShortName = page.inputValue(ShortName).trim();
String actualAddress = page.inputValue(Address).trim();
String actualCity = page.inputValue(City).trim();
String actualState = page.inputValue(State).trim();
String actualCountry = page.inputValue(Country).trim();
String actualMapLink = page.inputValue(MapLink).trim();
String actualLat = page.inputValue(Latitude).trim();
String actualLong = page.inputValue(Longitude).trim();
String actualRange = page.inputValue(AllowRange).trim();


        assert actualName.equals(ExpectedName) 
        : "name mismatch. Expected: " + ExpectedName + ", Got: " + actualName;

        assert actualShortName.equals(ExpectedShortName) 
        : "short name mismatch. Expected: " + ExpectedShortName + ", Got: " + actualShortName;

        assert actualAddress.equals(ExpectedAddress) 
        : "Address mismatch. Expected: " + ExpectedAddress + ", Got: " + actualAddress;

        assert actualCity.equals(ExpectedCity) 
        : "city mismatch. Expected: " + ExpectedCity + ", Got: " + actualCity;

        assert actualState.equals(ExpectedState) 
        : "state mismatch. Expected: " + ExpectedState + ", Got: " + actualState;

        assert actualCountry.equals(ExpectedCountry) 
        : "country mismatch. Expected: " + ExpectedCountry + ", Got: " + actualCountry;

        assert actualMapLink.equals(ExpectedLink) 
        : "link mismatch. Expected: " + ExpectedLink + ", Got: " + actualMapLink;

        assert actualLat.equals(ExpectedLat) 
        : "latitude mismatch. Expected: " + ExpectedLat + ", Got: " + actualLat;

        assert actualLong.equals(ExpectedLong) 
        : "longitude mismatch. Expected: " + ExpectedLong + ", Got: " + actualLong;

        assert actualRange.equals(ExpectedRange) 
        : "range mismatch. Expected: " + ExpectedRange + ", Got: " + actualRange;
    }
    public void AddBranch() throws InterruptedException{
        String unique = "Branch" + "_" + timestamp;
        page.click(Branch);
        Thread.sleep(500);
        page.click(AddBranchBtn);
        Thread.sleep(500);
        page.fill(EnterText, unique);
        Thread.sleep(500);
        page.click(SubmitBtn);
        Thread.sleep(500);
        page.fill(SearchBar, unique);
        Thread.sleep(500);
        page.isVisible(validatebranchcard);
    }

    public void AddDepartment() throws InterruptedException{
        String unique = "Department" + "_" + timestamp;
        page.click(Department);
        Thread.sleep(500);
        page.click(AddDepartmentBtn);
        Thread.sleep(500);
        page.fill(EnterText, unique);
        Thread.sleep(500);
        page.click(SubmitBtn);
        Thread.sleep(500);
        page.fill(SearchBar, unique);
        Thread.sleep(500);
        page.isVisible(validatedepartmeny);
    }

    public void AddDesignations() throws InterruptedException{
        String unique = "Designation" + "_" + timestamp;
        page.click(Designation);
        Thread.sleep(500);
        page.click(AddDesignationBtn);
        Thread.sleep(500);
        page.fill(EnterText, unique);
        Thread.sleep(500);
        page.click(SubmitBtn);
        Thread.sleep(500);
        page.fill(SearchBar, unique);
        Thread.sleep(500);
        page.isVisible(validatedesignation);
    }

    
    
}