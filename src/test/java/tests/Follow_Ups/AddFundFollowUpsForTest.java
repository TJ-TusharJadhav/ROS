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
import utils.RetryAnalyzer;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)

public class AddFundFollowUpsForTest extends BaseTest {
	public String phone;
	@AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.capture(page, result, phone);
		}
	@BeforeClass
	public void beforemethod() throws InterruptedException {
		 String openAddLeadOptions = "//div[@class='css-13cymwt-control']";
	     String selectFundOption = "//div[text()='Fund']";
		page.click("(//p[@data-size='sm'])[4]");
		page.click(openAddLeadOptions);
		page.click(selectFundOption);
        page.click("//div[contains(text(), 'New Lead')]");
        Thread.sleep(2000);
        page.click("(//p[@class='text-gray-500 mb-3 text-base break-words'])[1]");
        Thread.sleep(2000);
		
	}
	 @DataProvider(name = "followup")
	    public Object[][] followup() {

	        return new Object[][] {
	        	{"Not Connected", "Not Responding", "Tried calling but no response", "Today’s Follow Up"},
	        	{"Not Connected", "Not Reachable", "Number not reachable, will try again", "Today’s Follow Up"},
	        	{"Not Connected", "Switch Off", "Client’s phone switched off", "Today’s Follow Up"},
	        	{"Not Connected", "Busy", "Client was busy, will call later", "Today’s Follow Up"},

	        	{"Qualified", "Shared Documents", "Sent project details to client", "Today’s Follow Up"},
	        	{"Qualified", "Follow-up Scheduled", "Follow-up scheduled to discuss proposal", "Today’s Follow Up"},

	        	{"Meeting Planned", "Shivalik House", "Meeting scheduled at Shivalik House", "Today’s Follow Up"},
	        	{"Meeting Planned", "Online", "Online meeting link shared with client", "Today’s Follow Up"},
	        	{"Meeting Planned", "Client Office", "Meeting planned at client’s office", "Today’s Follow Up"},

	        	{"Meeting Done", "Attended", "Meeting completed successfully", "Today’s Follow Up"},
	        	{"Meeting Done", "Not Attended", "Client missed the meeting, need to reschedule", "Today’s Follow Up"},

                {"Outcome", "Follow-up Meeting", "Follow-up meeting scheduled for discussion", "Today’s Follow Up"},

//	        	{"Qualified", "meeting planned", "Planned meeting to discuss investment options", "Today’s Follow Up"},
//	        	{"Outcome", "committed", "Client committed to proceed", "Today’s Follow Up"},
	        	
	        	
};
	 }
	 @DataProvider(name = "with_Child_Stage_followup")
	  public Object[][] block() {
		 return new Object[][] {
			 {"Lost", "Budget","0–25 Lacs", "Lead lost due to budget constraints", "Lost Lead"},
			 {"Lost", "Budget","25–50 Lacs", "Lead lost due to budget constraints", "Lost Lead"},
			 {"Lost", "Budget","50 Lacs – 1 Cr", "Lead lost due to budget constraints", "Lost Lead"},
		        	
			 
			 };
	        }
	 @DataProvider(name = "with_out_chile_Stage")
	  public Object[][] lostfollowupdatawithoutchileStage() {
         return new Object[][] {
        	    {"Lost", "Fixed Returns", "Client prefers fixed return investment", "Lost Lead"},
	        	{"Lost", "Lack of Liquidity", "Client concerned about liquidity", "Lost Lead"},
	        	{"Lost", "Expected Higher Returns", "Client expecting higher returns elsewhere", "Lost Lead"},
	        	{"Lost", "Want to Invest for Shorter terms", "Client interested in short-term investment", "Lost Lead"},
	        	{"Lost", "Client wants Possession of Property", "Client prefers ready possession property", "Lost Lead"},
	        	{"Lost", "Requires monthly income", "Client requires monthly income option", "Lost Lead"},
	        	{"Lost", "Not planning to invest in Six months", "Client not planning to invest soon", "Lost Lead"},
	        	{"Lost", "Not ready to pay setup cost and management fees", "Client unwilling to pay setup/management fees", "Lost Lead"},
	        	{"Lost", "Looking for Emergency Exit", "Client wants quick exit option", "Lost Lead"},
	        	{"Lost", "Requires monthly payment options", "Client requires flexible monthly payment", "Lost Lead"},
	        	{"Lost", "Did not inquired", "Client didn’t inquire further", "Lost Lead"},
	        	{"Lost", "Not Interested", "Client not interested in offer", "Lost Lead"},
	        	{"Lost", "Not Responding", "Client not responding to follow-up", "Lost Lead"},
	        	{"Lost", "Invalid Number", "Client contact number invalid", "Lost Lead"},

	        	
       	    };
	    }

	 

	    @Test(dataProvider = "followup",priority = 1,retryAnalyzer = RetryAnalyzer.class)
	    public void addFollowUpTestforBlockStage(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException, ParseException {
	    	FundFollowUps.addFollowUP(stage, subStage, remark, expectedFilter);
	    }
	    @Test(dataProvider = "with_out_chile_Stage",priority = 2,retryAnalyzer = RetryAnalyzer.class)
	    public void addFollowUpTestforLostStageWithoutChieldstage(String stage, String subStage, String remark, String expectedFilter) throws InterruptedException {
	    	FundFollowUps.addfollowUPWithOutChildStage(stage, subStage, remark, expectedFilter);
	    }
	    
	    @Test(dataProvider = "with_Child_Stage_followup", priority = 3,retryAnalyzer = RetryAnalyzer.class)
	    public void addFollowUpforLOstStage(String stage, String subStage, String childStage, String remark, String expectedFilter) throws InterruptedException {
	    	FundFollowUps.addFollowUpForLostStage(stage, subStage, childStage, remark, expectedFilter);
	    }
	    // @Test(priority = 4,retryAnalyzer = RetryAnalyzer.class)
	    public void addFollowUpTestWithTextfield() throws InterruptedException {
	    	FundFollowUps.addFollowUpForLostStageWithTextField("Lost", "Others (Mention)","Not Respoend","Enter Customer Reason", "Remark", "Lost Lead");
	    }


}
