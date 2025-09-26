//package playwright;
//
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.Test;
//import org.testng.annotations.BeforeClass;
//import java.util.Random;
//
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.microsoft.playwright.Browser;
//import com.microsoft.playwright.BrowserType;
//import com.microsoft.playwright.Locator;
//import com.microsoft.playwright.Page;
//import com.microsoft.playwright.Playwright;
//import com.microsoft.playwright.options.SelectOption;
//import com.microsoft.playwright.options.WaitForSelectorState;
//
//public class Add100leadForProject {
//    private Playwright playwright;
//    private Browser browser;
//    private Page page;
//
//    // Locators
//    private String usernameField = "#phone";
//    private String getOtpBtn = "//button[@type='submit']";
//    private String followupMenu = "(//p[@data-size='sm'])[4]";
//    private String addLeadBtn = "//button[normalize-space()='+ Add Lead']";
//    private String projectDropDown = "#project";
//    private String firstName = "#firstName";
//    private String lastName = "#lastName";
//    private String email = "#email";
//    private String remark = "#clientRemarks";
//    private String leadPhoneNumber = "#mobileNumber";
//    private String sourceDropdown = "#source";
//    private String subSourceDropdown = "#subSource";
//    private String submitBtn = "//button[@type='submit']";
//
//    // Utility: Generate always unique phone numbers
//    private String generateUniquePhoneNumber() {
//        Random rand = new Random();
//        long num = 6000000000L + (long) (rand.nextDouble() * 3999999999L);
//        return String.valueOf(num);
//    }
//
//    @BeforeClass
//    public void setup() throws InterruptedException {
//        playwright = Playwright.create();
//        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        page = browser.newPage();
//        String URL= "https://admin-core-staging.realestateos.io/login";
//        
//        page.navigate(URL);
//        page.fill(usernameField, "8378845340");
//        page.click(getOtpBtn);
//
//        Locator verifyButton = page.locator("button:has-text('Verify')");
//        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//        verifyButton.click();
//    }
//
//    
//	@AfterClass
//    public void tearDown() {
//        browser.close();
//        playwright.close();
//    }
//	
//
//    
//    // Utility method to add lead
//    private void addLead(String projectName, String source, String subSource, String fname, String lname) throws InterruptedException {
//        String phone = generateUniquePhoneNumber();
//        String mail = "test@yopmail.com";
//        String note = "Testing" + phone;
//
//        page.click(followupMenu);
//        
////      checking 1st card is visible or not 
////        Locator verifyButton = page.locator("(//span[@class='font-medium'])[1]");
////        verifyButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//         
//        page.click(addLeadBtn);
//        Thread.sleep(1000);
//
//        // Select project
//        page.fill(projectDropDown, projectName);
//        Thread.sleep(500);
//
//
//        page.keyboard().press("Enter");
//        Thread.sleep(500);
//
//        page.fill(firstName, fname);
//        page.fill(lastName, lname);
//
//    
//
//        // Lead details
//        page.fill(leadPhoneNumber, phone);
//        page.fill(email, mail);
//
//        // Select Source
//        page.selectOption(sourceDropdown, new SelectOption().setLabel(source));
//        page.selectOption(subSourceDropdown, new SelectOption().setLabel(subSource));
//        page.fill(remark, note);
//        page.click(submitBtn);
//
//        Thread.sleep(500);
//        page.reload();
//    }
//
//    // DataProvider with 100 test cases (static project name)
//    @DataProvider(name = "leadData", parallel = false)
//    public Object[][] getLeadData() {
//        Object[][] data = new Object[100][5];
//        for (int i = 0; i < 100; i++) {
//            data[i][0] = "Gift 4";             // Static project name
//            data[i][1] = "Digital"; // Alternate source
//            data[i][2] = "SMS";       // Alternate subsource
//            data[i][3] = "Automation"; // First Name
//            data[i][4] = "Testing";    // Last Name
//        }
//        return data;
//        
//    }
//    
//
//    @Test(dataProvider = "leadData")
//    public void addMultipleLeads(String project, String source, String subSource, String fname, String lname) throws InterruptedException {
//    	 addLead(project, source, subSource, fname, lname);
//    }
//   
//}
