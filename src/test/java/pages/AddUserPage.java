package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddUserPage {
    private final Page page;

    // Locators
    private final String peopleBtn = "(//p[@data-size='sm'])[2]";
    private final String addUserBtn = "//button[@aria-label='Add User']";
    private final String firstName = "#firstName";
    private final String lastName = "#lastName";
    private final String countryCode = "(//div[@aria-label='Select Country Code'])[1]";
    private final String searchCountryCode = "(//input[@aria-label='Search for a country'])";
    private final String clickSearchedFirstOption = "//li[@role='option']";
    private final String phoneNumber = "#mobileNumber";
    private final String email = "#email";
    private final String submitBtn = "//button[@type='submit']";
    private final String cancelBtn = "(//button[@type='button'])[4]";

    // Sidebar Locators
    private final String sidebarUserName = "(//p[@class='font-semibold text-gray-900 break-words whitespace-normal min-w-0'])[1]";
    private final String sidebarUserMobile = "(//p[@class='font-semibold text-gray-900 break-words whitespace-normal min-w-0'])[2]";
    private final String sidebarUserEmail = "(//a[@class='text-blue-500 hover:underline break-words whitespace-normal min-w-0 block'])";
    private final String sidebarUserDate = "(//span[@class='font-semibold text-gray-900 min-w-0 break-words whitespace-normal'])";

    // User Card
    private final String userName = "(//h3[@class='font-semibold text-lg text-gray-800 break-words whitespace-normal'])[1]";
    private final String fullDetailsText = "(//p[@class='text-gray-500 text-base pt-1 mb-2 break-words whitespace-normal'])[1]";

    public AddUserPage(Page page) {
        this.page = page;
    }

    public void fillAddUserForm(String fname, String lname, String country, String phone, String emailID) throws InterruptedException {
        page.click(peopleBtn);
        page.click(addUserBtn);

        page.fill(firstName, fname);
        page.fill(lastName, lname);
        page.click(countryCode);

        page.fill(searchCountryCode, country);
        Thread.sleep(500);
        page.waitForSelector(clickSearchedFirstOption, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        Thread.sleep(500);
        page.click(clickSearchedFirstOption);

        page.fill(phoneNumber, phone);
        page.fill(email, emailID);
        page.click(submitBtn);
        Thread.sleep(1000);

        // Wait until user card appears
        page.waitForSelector(userName, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

        // Fetch actual values
        String actualFullName = page.textContent(userName).trim();
        String actualPhone = page.textContent(fullDetailsText).trim();

        // Extract phone details
        String phonePart = actualPhone.replace("Mobile No - ", "").trim();
        String[] parts = phonePart.split(" ", 2);
        String actualCountryCode = parts[0];
        String actualPhoneNumber = parts[1];

        // ✅ Assertions
        Assert.assertEquals(fname + " " + lname, actualFullName, "Full name mismatch.");
        Assert.assertEquals(country, actualCountryCode, "Country code mismatch.");
        Assert.assertEquals(phone, actualPhoneNumber, "Phone number mismatch.");

        // Sidebar details
        page.click(fullDetailsText);
        String sidebarName = page.textContent(sidebarUserName).trim();
        String sidebarMobile = page.textContent(sidebarUserMobile).trim();
        String sidebarEmail = page.textContent(sidebarUserEmail).trim();
        String sidebarDate = page.textContent(sidebarUserDate).trim();

        // Current date (if system-generated)
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Assert.assertEquals(fname + " " + lname, sidebarName, "Sidebar name mismatch.");
        Assert.assertEquals(country + " " + phone, sidebarMobile, "Sidebar mobile mismatch.");
        Assert.assertEquals(emailID, sidebarEmail, "Sidebar email mismatch.");
        Assert.assertEquals(today, sidebarDate, "Sidebar date mismatch.");
    }
}
