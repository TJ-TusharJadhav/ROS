package tests;

import org.testng.annotations.Test;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.opencsv.CSVReader;

import base.BaseTest;
import utils.TestUtils;
import java.io.InputStreamReader;
import java.net.URL;


public class AddFurnitureLeadTest extends BaseTest {
	
	@AfterMethod
//	ITestResult result
    public void takeScreenshot() throws InterruptedException {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            Object[] parameters = result.getParameters();
//            String testData = "";
//            if (parameters != null && parameters.length > 0) {
//                testData = "_" + String.join("_", 
//                    Arrays.stream(parameters)
//                          .map(Object::toString)
//                          .toArray(String[]::new)
//                );
//            }
//            page.screenshot(new Page.ScreenshotOptions()
//                    .setPath(Paths.get("screenshots/" + result.getName() + testData + "_failed.png"))
//                    .setFullPage(true));
//
//            System.out.println("❌ Screenshot saved for failed test with data: " + Arrays.toString(parameters));
//            Locator closeBtn = page.locator("//button[contains(@class,'mantine-Modal-close')]");
//            if (closeBtn.count() > 0 && closeBtn.isVisible()) {
//                closeBtn.click();
//                System.out.println("Fail: Modal close button clicked!");
//            } else {
//                System.out.println("Fail: Modal close button not found, skipping...");
//            }
//
//            String closeicon = "//div[@class='cursor-pointer flex justify-end px-4']";
//            if (page.locator(closeicon).count() > 0) {
//                page.waitForSelector(closeicon);
//                page.click(closeicon);
//                Thread.sleep(1000);
//            }
//        } else {
            Locator closeBtn = page.locator("//button[contains(@class,'mantine-Modal-close')]");
            if (closeBtn.count() > 0 && closeBtn.isVisible()) {
                closeBtn.click();
                System.out.println("Pass: Modal close button clicked!");
            } else {
                System.out.println("Pass: Modal close button not found, skipping...");
            }

            String closeicon = "//div[@class='cursor-pointer flex justify-end px-4']";
            if (page.locator(closeicon).count() > 0) {
                page.waitForSelector(closeicon);
                page.click(closeicon);
                Thread.sleep(1000);
            }
        }
//    }

    // ---------------- Google Sheet DataProvider ----------------
	public Object[][] getLeadAdditionalDataFromGoogleSheet() throws Exception {
	    // MUST use export=csv, not edit?usp=sharing
		
		String sheetUrl = "https://docs.google.com/spreadsheets/d/1genFxFEXIvAK3M_mdIo42FZTBBtaN3DHV6L_OKwULXA/export?format=csv&gid=601221684";
	    List<Object[]> data = new ArrayList<>();

	    try (CSVReader reader = new CSVReader(new InputStreamReader(new URL(sheetUrl).openStream()))) {
	        String[] line;
	        boolean firstRow = true;
	        while ((line = reader.readNext()) != null) {
	            if (firstRow) { // skip header row
	                firstRow = false;
	                continue;
	            }
	            if (line.length == 21) { // ensure 21 columns
	                data.add(line);
	            } else {
	                System.out.println("⚠️ Skipping row because it has " + line.length + " columns: " + Arrays.toString(line));
	            }
	        }
	    }

	    return data.toArray(new Object[0][]);
	}
	
	public Object[][] getLeadBasicDataFromGoogleSheet() throws Exception {
	    // MUST use export=csv, not edit?usp=sharing
		String sheetUrl = "https://docs.google.com/spreadsheets/d/1genFxFEXIvAK3M_mdIo42FZTBBtaN3DHV6L_OKwULXA/export?format=csv&gid=1355100398";
	    List<Object[]> data = new ArrayList<>();

	    try (CSVReader reader = new CSVReader(new InputStreamReader(new URL(sheetUrl).openStream()))) {
	        String[] line;
	        boolean firstRow = true;
	        while ((line = reader.readNext()) != null) {
	            if (firstRow) { // skip header row
	                firstRow = false;
	                continue;
	            }
	            if (line.length == 9) { // ensure 9 columns
	                data.add(line);
	            } else {
	                System.out.println("⚠️ Skipping row because it has " + line.length + " columns: " + Arrays.toString(line));
	            }
	        }
	    }

	    return data.toArray(new Object[0][]);
	}


    @DataProvider(name = "leadData")
    public Object[][] leadData() throws Exception {
        return getLeadBasicDataFromGoogleSheet();
    }
    @DataProvider(name = "AdditionalleadData")
    public Object[][] AdditionalleadData() throws Exception {
        return getLeadAdditionalDataFromGoogleSheet();
    }

        @Test(dataProvider = "leadData")
        public void addLeadForBasicFurnitureLead(
                String project, String fname, String lname, String country, 
                String source, String subSource, String category, String subCategory, String remarks) throws InterruptedException {

            String phone = TestUtils.generateUniquePhoneNumber();
            String email = fname + lname + "@yopmail.com";
            
            System.out.println(phone);

            addFurnitureLead.addLeadWithBasic(
            		project, fname, lname, country, phone, email, source, subSource, category, subCategory, remarks
            );
            addFurnitureLead.validateLeadWithGeneratedData(
                    fname, lname, project, source, email, country, phone, remarks, subSource, category, subCategory
            );
        }
    
    @Test(dataProvider = "AdditionalleadData")
    public void addLeadForAdditionalFurnitureLeadTest(
        String project, String fname, String lname, String country, String source,
        String subSource, String category, String subCategory, String remarks,
        String referralType, String referralName, String priority, String budget,
        String ProjectType, String buyingTime, String Finance, String BOD,
        String area, String CompanyName, String EstimatedQuantity, String ProjectTimeline
    ) throws InterruptedException {
       
        String phone = TestUtils.generateUniquePhoneNumber(); 
        String email = fname + lname + "@shivalikgroup.com"; 
        
        System.out.println(phone);

        addFurnitureLead.addLeadWithAdditional(
            project, fname, lname, country, phone, email, source, subSource, category, subCategory, remarks,
            referralType, referralName,
            priority, budget, ProjectType, buyingTime, Finance, BOD, area,CompanyName,EstimatedQuantity,ProjectTimeline
        );
        addFurnitureLead.validateLeadWithAdditionalInfo(
        	    fname, lname, project, source, email, country, phone, remarks, subSource, 
        	    referralType, referralName, buyingTime, 
        	    area, ProjectType, budget, priority,  
        	    Finance, BOD, CompanyName, EstimatedQuantity, ProjectTimeline,
        	    category, subCategory                 
        	);


        
    }

}
