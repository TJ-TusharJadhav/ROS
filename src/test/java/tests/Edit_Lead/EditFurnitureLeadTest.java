package tests.Edit_Lead;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)
public class EditFurnitureLeadTest extends BaseTest {
	
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}
public Object[][] getLeadBasicDataFromGoogleSheet() throws Exception {
		
	String sheetUrl = "https://docs.google.com/spreadsheets/d/1genFxFEXIvAK3M_mdIo42FZTBBtaN3DHV6L_OKwULXA/export?format=csv&gid=1039936583";
    List<Object[]> data = new ArrayList<>();

	    try (CSVReader reader = new CSVReader(new InputStreamReader(new URL(sheetUrl).openStream()))) {
	        String[] line;
	        boolean firstRow = true;
	        while ((line = reader.readNext()) != null) {
	            if (firstRow) { // skip header row
	                firstRow = false;
	                continue;
	            }
	            if (line.length == 5) { 
	                data.add(line);
	            } else {
	                System.out.println("⚠️ Skipping row because it has " + line.length + " columns: " + Arrays.toString(line));
	            }
	        }
	    }

	    return data.toArray(new Object[0][]);
	}
public Object[][] getLeadAdditionalDataFromGoogleSheet() throws Exception {
	
	
	String sheetUrl = "https://docs.google.com/spreadsheets/d/1genFxFEXIvAK3M_mdIo42FZTBBtaN3DHV6L_OKwULXA/export?format=csv&gid=1353534104";
    List<Object[]> data = new ArrayList<>();

	    try (CSVReader reader = new CSVReader(new InputStreamReader(new URL(sheetUrl).openStream()))) {
	        String[] line;
	        boolean firstRow = true;
	        while ((line = reader.readNext()) != null) {
	            if (firstRow) { // skip header row
	                firstRow = false;
	                continue;
	            }
	            if (line.length == 17) { 
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
    
       

    @Test(dataProvider = "leadData",retryAnalyzer = RetryAnalyzer.class)
    public void EditLeadForBasicFundLead(String fname, String lname, String Category, String subCategory,String remarks) throws InterruptedException {
    String email = fname + lname + "@yopmail.com";
    editFurnitureLead.EditLeadWithBasic( fname, lname, email,  Category, subCategory,remarks);
    editFurnitureLead.validateLeadWithGeneratedData(fname, lname, email, remarks, Category, subCategory);
    }
    
    @Test(dataProvider = "AdditionalleadData", priority = 2)
    public void EditLeadForAdditionalFurnitureLeadTest(
        String fname, String lname, String category, String subCategory, String remarks,
        String referralType, String referralName, String priority, String budget,
        String ProjectType, String buyingTime, String Finance, String BOD,
        String area, String CompanyName, String EstimatedQuantity, String ProjectTimeline
    ) throws InterruptedException {

    String email = fname + lname + "@yopmail.com";

        editFurnitureLead.EditLeadWithAdditional(
            fname, lname, email, category, subCategory, remarks,
            referralType, referralName,
            priority, budget, ProjectType, buyingTime, Finance, BOD, area,CompanyName,EstimatedQuantity,ProjectTimeline
        );
        editFurnitureLead.validateLeadWithAdditionalInfo(
        	    fname, lname, email, remarks,
        	    referralType, referralName, buyingTime, 
        	    area, ProjectType, budget, priority,  
        	    Finance, BOD, CompanyName, EstimatedQuantity, ProjectTimeline,
        	    category, subCategory                 
        	);


        
    }

}