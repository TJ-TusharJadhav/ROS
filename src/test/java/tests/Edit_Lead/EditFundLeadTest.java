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
import utils.PhoneNumber;
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)

public class EditFundLeadTest extends BaseTest {
	
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}
public Object[][] getLeadBasicDataFromGoogleSheet() throws Exception {
		
	String sheetUrl = "https://docs.google.com/spreadsheets/d/1genFxFEXIvAK3M_mdIo42FZTBBtaN3DHV6L_OKwULXA/export?format=csv&gid=614114709";
    List<Object[]> data = new ArrayList<>();

	    try (CSVReader reader = new CSVReader(new InputStreamReader(new URL(sheetUrl).openStream()))) {
	        String[] line;
	        boolean firstRow = true;
	        while ((line = reader.readNext()) != null) {
	            if (firstRow) { // skip header row
	                firstRow = false;
	                continue;
	            }
	            if (line.length == 4) { 
	                data.add(line);
	            } else {
	                System.out.println("⚠️ Skipping row because it has " + line.length + " columns: " + Arrays.toString(line));
	            }
	        }
	    }

	    return data.toArray(new Object[0][]);
	}
public Object[][] getLeadAdditionalDataFromGoogleSheet() throws Exception {
	
	
	String sheetUrl = "https://docs.google.com/spreadsheets/d/1genFxFEXIvAK3M_mdIo42FZTBBtaN3DHV6L_OKwULXA/export?format=csv&gid=480001572";
    List<Object[]> data = new ArrayList<>();

	    try (CSVReader reader = new CSVReader(new InputStreamReader(new URL(sheetUrl).openStream()))) {
	        String[] line;
	        boolean firstRow = true;
	        while ((line = reader.readNext()) != null) {
	            if (firstRow) { // skip header row
	                firstRow = false;
	                continue;
	            }
	            if (line.length == 15) { 
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
    public void EditLeadForBasicFundLead(String project, String fname, String lname, String remarks) throws InterruptedException {
    String email = fname + lname + "@yopmail.com";
    editfundLead.editLeadWithBasic(project, fname, lname, email, remarks);
    editfundLead.validateLeadWithBasicInfo(fname, lname, project, email, remarks);
    }
    
    @Test(dataProvider = "AdditionalleadData",retryAnalyzer = RetryAnalyzer.class)
    public void EditLeadForAdditionalFundLeadTest(
            String project, String fname, String lname,  String remarks,
             String addCountryCode, String referralType, String referralName, String location, String buyingTime,
            String priority, String budget, String area, String projectCat, String unitType, String leadType) throws InterruptedException {
       
        String additionalPhone = PhoneNumber.generateUniquePhoneNumber(addCountryCode);
        String email = fname + lname + "@yopmail.com";

        editfundLead.editLeadWithAdditional(
            project, fname, lname, email, remarks,
             addCountryCode, additionalPhone, referralType, referralName,
            location, buyingTime, priority, budget, area, projectCat, unitType, leadType
        );
        editfundLead.validateLeadWithAdditionalInfo(fname, lname, project, email,  remarks, 
        		addCountryCode, additionalPhone, referralType, referralName,
                buyingTime, priority, budget, area,
                projectCat, unitType, leadType, location);
        
    }

}