package tests.Add_Lead;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.CountryCodeMapper;
import utils.PhoneNumber;
import utils.ScreenshotUtil;

@Listeners(listeners.ExtentTestNGListener.class)
public class AddCPLeadTest extends BaseTest {

    private String phone;

    // 📸 Screenshot after each method
    @AfterMethod
    public void takeScreenshot(ITestResult result) throws InterruptedException {
        ScreenshotUtil.capture(page, result, phone);
    }

    /* -------------------- 🧪 DATA PROVIDERS -------------------- */

    @DataProvider(name = "add_Individual_CPLead_With_Non_Registered")
    public Object[][] individualNonRegisteredData() {
        return new Object[][]{
            {"Aarav", "Shah", "+91", "Andheri East", "Tier 1", "Experienced, RERA not registered", "Individual"},
            {"Meera", "Patel", "+91", "Vastrapur", "Tier 2", "Experienced, RERA not registered", "Individual"},
            {"Rohan", "Mehta", "+91", "Baner", "Tier 3", "Experienced, RERA not registered", "Individual"}
        };
    }

    @DataProvider(name = "add_Company_CPLead_With_Non_Registered")
    public Object[][] companyNonRegisteredData() {
        return new Object[][]{
            {"Kavya", "Rao", "+91", "Navi Mumbai", "Tier 1", "Experienced, RERA not registered", "Company", "Alpha Corp"},
            {"Vivaan", "Singh", "+91", "Bopal", "Tier 2", "Experienced, RERA not registered", "Company", "NexGen Realty"},
            {"Isha", "Desai", "+91", "Powai", "Tier 3", "Experienced, RERA not registered", "Company", "Vision Builders"}
        };
    }

    @DataProvider(name = "add_Individual_CPLead_With_Registered")
    public Object[][] individualRegisteredData() {
        return new Object[][]{
            {"Arjun", "Nair", "+91", "Koramangala", "Tier 1", "Experienced, RERA Certified","src/test/java/Documents/propertyCard.pdf", "25/12/2030", "Individual"},
            {"Dia", "Joshi", "+91", "Bodakdev", "Tier 2", "Experienced, RERA Certified", "src/test/java/Documents/propertyCard.pdf","10/05/2031", "Individual"},
            {"Reyansh", "Kumar", "+91", "Wakad", "Tier 3", "Experienced, RERA Certified", "src/test/java/Documents/propertyCard.pdf","18/09/2032", "Individual"}
        };
    }

    @DataProvider(name = "add_Company_CPLead_With_Registered")
    public Object[][] companyRegisteredData() {
        return new Object[][]{
            {"Priya", "Shah", "+91", "Malad West", "Tier 1", "Experienced, RERA Certified","src/test/java/Documents/propertyCard.pdf", "21/11/2030", "UrbanNest Group"},
            {"Dev", "Verma", "+91", "Wagholi", "Tier 2", "Experienced, RERA Certified", "src/test/java/Documents/propertyCard.pdf","05/03/2033", "FutureSpaces Pvt Ltd"},
            {"Anya", "Gupta", "+91", "Mira Road", "Tier 3", "Experienced, RERA Certified", "src/test/java/Documents/propertyCard.pdf","12/08/2032", "Summit Infra"}
        };
    }

    /* -------------------- ✅ TEST CASES -------------------- */

    @Test(dataProvider = "add_Individual_CPLead_With_Non_Registered", priority = 1)
    public void addIndividualNonRegistered(
            String fname, String lname, String country, String address,
            String tier, String channelRole, String radio) throws InterruptedException {

        String countryCode = CountryCodeMapper.getCountryCode(country);
        phone = PhoneNumber.generateUniquePhoneNumber(countryCode);
        System.out.println("Fund lead Phone Number (" + countryCode + "): " + phone);

        String email = fname + lname + "@yopmail.com";

        addCPLead.add_Individual_CPLead_With_Non_Registered(
                fname, lname, country, phone, email, address, tier, channelRole
        );
    }

    @Test(dataProvider = "add_Company_CPLead_With_Non_Registered", priority = 2)
    public void addCompanyNonRegistered(
            String fname, String lname, String country, String address, String tier,
            String channelRole, String radio, String company) throws InterruptedException {

        String countryCode = CountryCodeMapper.getCountryCode(country);
        phone = PhoneNumber.generateUniquePhoneNumber(countryCode);
        System.out.println("Fund lead Phone Number (" + countryCode + "): " + phone);

        String email = fname + lname + "@yopmail.com";

        addCPLead.add_Company_CPLead_With_Non_Registered(
                fname, lname, country, phone, email, address, tier, channelRole, company
        );
    }

    @Test(dataProvider = "add_Individual_CPLead_With_Registered", priority = 4)
    public void addIndividualRegistered(
            String fname, String lname, String country, String address, String tier,
            String channelRole, String Path, String date, String radio) throws InterruptedException {

        String countryCode = CountryCodeMapper.getCountryCode(country);
        phone = PhoneNumber.generateUniquePhoneNumber(countryCode);
        System.out.println("Fund lead Phone Number (" + countryCode + "): " + phone);

        String email = fname + lname + "@yopmail.com";

        addCPLead.add_Individual_CPLead_With_Registered(
                fname, lname, country, phone, email, address, tier, channelRole, Path,date
        );
    }

    @Test(dataProvider = "add_Company_CPLead_With_Registered", priority = 3)
    public void addCompanyRegistered(
            String fname, String lname, String country, String address, String tier,
            String channelRole, String path,String date, String companyName) throws InterruptedException {

        String countryCode = CountryCodeMapper.getCountryCode(country);
        phone = PhoneNumber.generateUniquePhoneNumber(countryCode);
        System.out.println("Fund lead Phone Number (" + countryCode + "): " + phone);

        String email = fname + lname + "@yopmail.com";

        addCPLead.add_Company_CPLead_With_Registered(
                fname, lname, country, phone, email, address, tier, channelRole, companyName, path, date
        );
    }
}