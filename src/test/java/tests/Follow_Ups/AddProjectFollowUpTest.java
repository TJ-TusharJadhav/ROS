package tests.Follow_Ups;

import java.text.ParseException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.ScreenshotUtil;
@Listeners(listeners.ExtentTestNGListener.class)

public class AddProjectFollowUpTest extends BaseTest {
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}
	@BeforeClass
	public void beforemethod() throws InterruptedException {
		page.click("(//p[@data-size='sm'])[4]");
        page.click("//div[contains(text(), 'New Lead')]");
        Thread.sleep(2000);
        page.click("(//p[@class='text-gray-500 mb-3 text-base break-words'])[1]");
        Thread.sleep(2000);
		
	}
	 @DataProvider(name = "followUpData")
	    public Object[][] followUpData() {

	        return new Object[][] {
	        	{"Not Connected", "Not Responding", "User declined the follow-up conversation", "Today’s Follow Up"},
	        	{"Not Connected", "Not Reachable", "All calls unreachable today", "Today’s Follow Up"},
	        	{"Not Connected", "Switch Off", "Phone is switched off", "Today’s Follow Up"},
	        	{"Not Connected", "Busy", "Line was busy on multiple attempts", "Today’s Follow Up"},
	        	{"Not Connected", "Discussion", "Initial discussion completed", "Today’s Follow Up"},
	        	
	        	{"Qualified", "Project Details Shared", "Shared brochure and pricing details", "Today’s Follow Up"},
	        	{"Qualified", "Site Meeting Planned", "Meeting scheduled for 12th July", "Today’s Follow Up"},
	        	{"Qualified", "Project Briefing", "Shared project overview", "Today’s Follow Up"},
	        	{"Qualified", "Discussion", "No follow-up response since 3 days", "Today’s Follow Up"},
	        	{"Qualified", "Not Responding", "Had a good discussion on project types", "Today’s Follow Up"},
	        	
	        	{"Meeting Planned", "Discussion", "Agenda set for the upcoming site visit", "Today’s Follow Up"},
	        	{"Meeting Planned", "Not Responding", "User missed last scheduled call", "Today’s Follow Up"},
	        	
	        	{"Meeting Done", "Brochure Shared", "Shared updated brochure", "Today’s Follow Up"},
	        	{"Meeting Done", "Sample House Visited", "User impressed with interiors", "Today’s Follow Up"},
	        	{"Meeting Done", "Unit Availablity Checked", "Unit options shared with client", "Today’s Follow Up"},
	        	{"Meeting Done", "Unit Blocked", "Unit 3B-405 blocked temporarily", "Today’s Follow Up"},
	        	{"Meeting Done", "Payment Plan Discussion", "Explained staggered payment plan", "Today’s Follow Up"},
	        	{"Meeting Done", "Next Meeting Planned", "Next visit planned for 15th July", "Today’s Follow Up"},
	        	{"Meeting Done", "Discussion", "Finalizing decision with family", "Today’s Follow Up"},
	        	{"Meeting Done", "Not Responding", "No callback after site visit", "Today’s Follow Up"},
	        	{"Meeting Done", "Project Overview & Cost Discussion", "Covered pricing, next step is booking", "Today’s Follow Up"},  
	        };
	    }
	 
	 @DataProvider(name = "blockdata")
	  public Object[][] block() {
		 return new Object[][] {
	        	{"Block", "Discussion", "Remark", "Active Lead"},  
	        	{"Block", "Not Responding", "Remark", "Active Lead"},
	            };
	        }
	 @DataProvider(name = "lostfollowupdatawithoutchileStage")
	  public Object[][] lostfollowupdatawithoutchileStage() {
          return new Object[][] {
	    	    {"Lost", "Location", "Location not suitable", "Lost Lead"},
	        	{"Lost", "Comment", "Negative feedback received", "Lost Lead"},
	        	{"Lost", "Cast", "Preference mismatch", "Lost Lead"},
	        	{"Lost", "Carpet Area", "Carpet area too small", "Lost Lead"},
	        	{"Lost", "Large Community", "Prefers smaller community", "Lost Lead"},
	        	{"Lost", "Unit Availability", "Desired unit not available", "Lost Lead"},
	        	{"Lost", "Floor Plan", "Unfavorable floor plan", "Lost Lead"},
	        	{"Lost", "Loan", "Loan not approved", "Lost Lead"},
	        	{"Lost", "Booked With Others", "Customer booked with another builder", "Lost Lead"},
	        	{"Lost", "Not Looking For Property", "No longer interested in buying", "Lost Lead"},
	        	{"Lost", "Planning Postponed", "Decision postponed", "Lost Lead"},
	        	{"Lost", "Wrong No", "Contact number incorrect", "Lost Lead"},
	        	{"Lost", "Other (Mention)", "Other reason mentioned", "Lost Lead"},
	        	{"Lost", "Never Respond", "No response from lead", "Lost Lead"},
	        	};
	    }
	 @DataProvider(name = "lostfollowup")
	    public Object[][] lostfollowup() {

	        return new Object[][] {
	        	{"Lost", "Budget", "Bellow 50 Lakhs", "Remark: Budget too low", "Lost Lead"},
	        	 {"Lost", "Budget", "50 Lakh 10 1Cr", "Remark: Limited budget range", "Lost Lead"},
	        	 {"Lost", "Budget", "1 Cr to 2 Cr", "Remark: Mid-budget preference", "Lost Lead"},
	        	 {"Lost", "Budget", "2 Cr to 5 Cr", "Remark: High budget category", "Lost Lead"},
	        	 {"Lost", "Budget", "5 Cr to 10 Cr", "Remark: Very high budget", "Lost Lead"},
	        	 {"Lost", "Budget", "10 Cr to 15 Cr", "Remark: Luxury budget segment", "Lost Lead"},
	        	
	        	 {"Lost", "Property Type", "Apartment", "Remark: Looking for apartment", "Lost Lead"},
	        	 {"Lost", "Property Type", "Studio", "Remark: Interested in studio", "Lost Lead"},
	        	 {"Lost", "Property Type", "Penthouse", "Remark: Penthouse preference", "Lost Lead"},
	        	 {"Lost", "Property Type", "Tenement", "Remark: Tenement option required", "Lost Lead"},
	        	 {"Lost", "Property Type", "Villa", "Remark: Villa requirement", "Lost Lead"},
	        	 {"Lost", "Property Type", "Plot", "Remark: Plot needed", "Lost Lead"},
	        	 {"Lost", "Property Type", "Office", "Remark: Office space requirement", "Lost Lead"},
	        	 {"Lost", "Property Type", "Showroom", "Remark: Looking for showroom", "Lost Lead"},
	        	
	        	 {"Lost", "BHK", "2 BHK", "Remark: Preferred 2 BHK", "Lost Lead"},
	        	 {"Lost", "BHK", "3 BHK", "Remark: Preferred 3 BHK", "Lost Lead"},
	        	 {"Lost", "BHK", "4 BHK", "Remark: Preferred 4 BHK", "Lost Lead"},
	        	 {"Lost", "BHK", "5 BHK", "Remark: Preferred 5 BHK", "Lost Lead"},
	        	
	        	 {"Lost", "Property Requirement", "Ready to Move", "Remark: Wants ready-to-move property", "Lost Lead"},
	        	 {"Lost", "Property Requirement", "Under Construction", "Remark: Open to under-construction", "Lost Lead"},
	        	 {"Lost", "Property Requirement", "Pre Launch", "Remark: Interested in pre-launch", "Lost Lead"},
	        	 {"Lost", "Property Requirement", "Nearby Possession", "Remark: Looking for near possession", "Lost Lead"}
	        	
	        };
	 }

	    @Test(dataProvider = "followUpData", priority = 1)
	    public void addFollowUpTest(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException, ParseException {
	    	projectFollowUp.addfollowUP(stage, subStage, remark, expectedFilter);
	    }

	    @Test(dataProvider = "lostfollowupdatawithoutchileStage",priority = 4)
	    public void addFollowUpTestforLostStageWithoutChieldstage(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException {
	    	projectFollowUp.addfollowUPWithOutChiledstage(stage, subStage, remark, expectedFilter);
	    }
	    @Test(dataProvider = "blockdata",priority = 2)
	    public void addFollowUpTestforBlockStage(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException {
	    	projectFollowUp.addfollowUPWithOutChiledstage(stage, subStage, remark, expectedFilter);
	    }

	    @Test(priority = 3)
	    public void addFollowUpTestforBookStagewithUnitNumber() throws InterruptedException {
	    	projectFollowUp.addFollowUpForunitnumber("Booked", "Select Unit Number", "123", "Remark Test", "Booked");
	    }
	    
	    @Test(dataProvider = "lostfollowup",priority = 5)
	    public void addFollowUpforLOstStage(String stage, String subStage, String childStage, String remark, String expectedFilter) throws InterruptedException {
	    	projectFollowUp.addFollowUpForLostStage(stage, subStage, childStage, remark, expectedFilter);
	    }
	    
	    @Test(priority = 6)
	    public void addFollowUpTestforBlockStagewithUnitNumber() throws InterruptedException {
	    	projectFollowUp.addFollowUpForunitnumber("Block", "Select Unit Number", "123", "Remark Test", "Active Lead");
	    }


}
