package tests.Add_Lead;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import com.opencsv.CSVReader;
import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.ScreenshotUtil;
import utils.PhoneNumber;

@Listeners(ExtentTestNGListener.class)
public class AddFurnitureLeadTest extends BaseTest {
	
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}

    public Object[][] getLeadAdditionalDataFromGoogleSheet() throws Exception {
		
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

        @Test(dataProvider = "leadData", priority = 1)
        public void addLeadForBasicFurnitureLead(
                String project, String fname, String lname, String country, 
                String source, String subSource, String category, String subCategory, String remarks) throws InterruptedException {

            phone = PhoneNumber.generateUniquePhoneNumber();
            String email = fname + lname + "@shivalikgroup.com";
            
            System.out.println(phone);

            addFurnitureLead.addLeadWithBasic(
            		project, fname, lname, country, phone, email, source, subSource, category, subCategory, remarks
            );
            addFurnitureLead.validateLeadWithGeneratedData(
                    fname, lname, project, source, email, country, phone, remarks, subSource, category, subCategory
            );
        }
    
//    @Test(dataProvider = "AdditionalleadData", priority = 2)
    public void addLeadForAdditionalFurnitureLeadTest(
        String project, String fname, String lname, String country, String source,
        String subSource, String category, String subCategory, String remarks,
        String referralType, String referralName, String priority, String budget,
        String ProjectType, String buyingTime, String Finance, String BOD,
        String area, String CompanyName, String EstimatedQuantity, String ProjectTimeline
    ) throws InterruptedException {
       
        phone = PhoneNumber.generateUniquePhoneNumber(); 
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
