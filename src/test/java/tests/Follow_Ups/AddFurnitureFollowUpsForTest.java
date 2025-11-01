package tests.Follow_Ups;

import java.text.ParseException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)

public class AddFurnitureFollowUpsForTest extends BaseTest {
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}
	@BeforeClass
	public void beforemethod() throws InterruptedException {
		 String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
	     String selectFundOption = "//div[text()='Furniture']";
		page.click("(//p[@data-size='sm'])[4]");
		page.click(openAddLeadOptions);
		page.click(selectFundOption);
        page.click("//div[contains(text(), 'New Lead')]");
        Thread.sleep(2000);
        page.click("(//p[@class='text-gray-500 mb-3 text-base break-words'])[1]");
        Thread.sleep(2000);
		
	}
	 @DataProvider(name = "followUpData")
	    public Object[][] followUpData() {

	        return new Object[][] {
	        	{"Call Connected", "Product Details Shared", "User declined the follow-up conversation", "Today’s Follow Up"},
	        	{"Call Connected", "Quotation Share", "All calls unreachable today", "Today’s Follow Up"},
	        	{"Call Connected", "Call Not Picked", "Phone is switched off", "Today’s Follow Up"},
	        	{"Call Connected", "Follow-up Scheduled", "Line was busy on multiple attempts", "Today’s Follow Up"},
	        	
	        	{"Not Qualifyed", "Call Not Picked", "Initial discussion completed", "Today’s Follow Up"},
	        	{"Not Qualifyed", "Busy", "Shared brochure and pricing details", "Today’s Follow Up"},
	        	
	        	{"Store Visit Planned", "Appointment Fixed", "Meeting scheduled for 12th July", "Today’s Follow Up"},
	        	{"Store Visit Planned", "Reschedule Requested", "Shared project overview", "Today’s Follow Up"},
	        	{"Store Visit Planned", "Follow-up Scheduled", "No follow-up response since 3 days", "Today’s Follow Up"},
	        	
	        	{"Store Visit Done", "Negotiation", "Had a good discussion on project types", "Today’s Follow Up"},
	        	{"Store Visit Done", "Quotation Share", "Agenda set for the upcoming site visit", "Today’s Follow Up"},
	        	{"Store Visit Done", "Follow-up Pending Decision", "User missed last scheduled call", "Today’s Follow Up"},
	        	
	        	
	        };
	    }
	 
	 @DataProvider(name = "with_Child_Stage_followup")
	  public Object[][] block() {
		 return new Object[][] {
			 {"Lost", "Postponed Purchase", "Less than 1 Month","Remark", "Lost Lead"},
			 {"Lost", "Postponed Purchase", "1 – 3 Months","Remark", "Lost Lead"},
			 {"Lost", "Postponed Purchase", "3 – 6 Months","Remark", "Lost Lead"},
			 {"Lost", "Postponed Purchase", "6 – 12 Months","Remark", "Lost Lead"},
			 {"Lost", "Postponed Purchase", "More than 12 Months","Remark", "Lost Lead"},
			 {"Lost", "Postponed Purchase", "No Clear Timeline","Remark", "Lost Lead"},
			 
			 {"Lost", "Cancelled Due to Budget", "Below ₹50,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "₹50,000 – ₹1,00,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "₹1,00,000 – ₹3,00,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "₹3,00,000 – ₹5,00,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "₹5,00,000 – ₹10,00,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "₹10,00,000 – ₹20,00,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "₹20,00,000 – ₹35,00,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "₹35,00,000 – ₹50,00,000","Remark", "Lost Lead"},
			 {"Lost", "Cancelled Due to Budget", "Not Disclosed","Remark", "Lost Lead"},
			 
			 {"Lost", "Long Delivery Timeline", "1 Month","Remark", "Lost Lead"},
			 {"Lost", "Long Delivery Timeline", "2 - 3 Month","Remark", "Lost Lead"},
			 {"Lost", "Long Delivery Timeline", "3 to 5 Month","Remark", "Lost Lead"},
			 {"Lost", "Long Delivery Timeline", "5 - 8 Month","Remark", "Lost Lead"},
			 {"Lost", "Long Delivery Timeline", "More then 10 Month","Remark", "Lost Lead"}
			 };
	        }
	 @DataProvider(name = "with_out_chile_Stage")
	  public Object[][] lostfollowupdatawithoutchileStage() {
          return new Object[][] {
        	    {"Lost", "Chose Competitor", "Preference mismatch", "Lost Lead"},
	        	{"Lost", "No Response After Multiple Attempts", "Preference mismatch", "Lost Lead"},
	        	{"Lost", "Requirement Changed", "Prefers smaller community", "Lost Lead"},
	        	{"Lost", "Style/Design Not Matching", "Unfavorable floor plan", "Lost Lead"},
	        	{"Lost", "Product Not Available", "Loan not approved", "Lost Lead"},
	        	{"Lost", "Wrong Number", "Customer booked with another builder", "Lost Lead"},
	        	{"Lost", "Lead by Mistake", "No longer interested in buying", "Lost Lead"},
	        	{"Lost", "Poor Experience", "Decision postponed", "Lost Lead"},
	        	{"Deal Closed", "Invoice Generated", "Shared updated brochure", "Deal Done"},
	        	{"Deal Closed", "Advance Received", "User impressed with interiors", "Deal Done"},
	        	{"Deal Closed", "Delivery Scheduled", "Unit options shared with client", "Deal Done"},
	        	{"Deal Closed", "Delivered & Installed", "Unit 3B-405 blocked temporarily", "Deal Done"},
	        	};
	    }
	 
	 
	 @DataProvider(name = "DealClosed")
	    public Object[][] bookfollowup() {

	        return new Object[][] {
	        	{"Deal Closed", "Invoice Generated", "Shared updated brochure", "Deal Done"},
	        	{"Deal Closed", "Advance Received", "User impressed with interiors", "Deal Done"},
	        	{"Deal Closed", "Delivery Scheduled", "Unit options shared with client", "Deal Done"},
	        	{"Deal Closed", "Delivered & Installed", "Unit 3B-405 blocked temporarily", "Deal Done"},
	        	};
	 }

	    @Test(dataProvider = "followUpData", priority = 1)
	    public void addFollowUpTest(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException, ParseException {
	    	FurnitureFollowUps.addFollowUP(stage, subStage, remark, expectedFilter);
	    }
	    
	    @Test(dataProvider = "with_out_chile_Stage",priority = 2)
	    public void addFollowUpTestforLostStageWithoutChieldstage(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException {
	    	FurnitureFollowUps.addfollowUPWithOutChildStage(stage, subStage, remark, expectedFilter);
	    }
	    
	    @Test(dataProvider = "with_Child_Stage_followup", priority = 3)
	    public void addFollowUpforLOstStage(String stage, String subStage, String childStage, String remark, String expectedFilter) throws InterruptedException {
	    	FurnitureFollowUps.addFollowUpForLostStage(stage, subStage, childStage, remark, expectedFilter);
	    }

	    @Test(dataProvider = "DealClosed",priority = 4)
	    public void addFollowUpTestforBlockStage(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException {
	    	FurnitureFollowUps.addfollowUPWithOutChildStage(stage, subStage, remark, expectedFilter);
	    }


}
