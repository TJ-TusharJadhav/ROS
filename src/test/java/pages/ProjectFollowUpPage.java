package pages;
import org.testng.asserts.SoftAssert;

import java.util.Map;
import java.text.ParseException;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import utils.DateTimeUtil;

public class ProjectFollowUpPage {
	private Page page;
	private String actualPhone;
	
	private String fullDetailsText = "(//p[@class='text-gray-500 mb-3 text-base break-words'])[1]";
	private String dateField = "//input[@class='MuiPickersInputBase-input MuiPickersOutlinedInput-input css-1ftw2zb']";
	

    Map<String, String> dateTimeMap = DateTimeUtil.getFormattedDateTimePlus20Mins();
	private String Enterdatetime = dateTimeMap.get("Enterdatetime");
	private String Verifydatetime = dateTimeMap.get("Verifydatetime");



    public ProjectFollowUpPage(Page page) {
        this.page = page;
    }
    
    
    public void addfollowUP(String Stage, String SubStage, String Remark, String ExpectedFilter) throws InterruptedException, ParseException {
    	actualPhone = page.textContent(fullDetailsText).trim();
        System.out.println("actualPhone: "+actualPhone);
        System.out.println("Enterdatetime: "+Enterdatetime);
        System.out.println("Verifydatetime: "+Verifydatetime); 
    	page.waitForSelector("button:has-text('Add Lead Follow up')", 
    			new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    	page.click("button:has-text('Add Lead Follow up')");
        page.click("button:has-text('" + Stage + "')");
        page.click("button:has-text('" + SubStage + "')");
        Thread.sleep(1000);
        page.click("//div[@class='MuiFormControl-root MuiFormControl-fullWidth MuiPickersTextField-root css-2abjvj']");
        Thread.sleep(1000);

        
        page.fill(dateField, Enterdatetime);
        
        page.fill("#remarks", Remark);
        Thread.sleep(1000);
        // Submit
        Locator submitButton = page.locator("//button[@type='submit']");

     // Keep clicking until the button is no longer visible
     while (submitButton.isVisible()) {
         if (submitButton.isEnabled()) {
             submitButton.click();
         }
         page.waitForTimeout(2000);
     }

        page.click("//div[contains(text(), '"+ExpectedFilter+"')]");
        Thread.sleep(1000);
        String phonePart = actualPhone.replace("Mobile no -", "").trim();
        String[] parts = phonePart.split(" ", 2); 
        String ActualphoneNumber = parts[1]; 
        System.out.println("Lead phone Number: " + ActualphoneNumber);

        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
        Thread.sleep(1000);
//        Validation actual stage in lead card 
       Locator fullDetails = page.locator(fullDetailsText);
        if (!fullDetails.isVisible()) {
            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
            
        }
        String actualStatus1 = page.textContent("span:has-text('"+Stage+"')").trim();
            System.out.println("Actual Status: " + actualStatus1);
            assert actualStatus1.equals(Stage): "Follow Up Stage mismatch. Expected: "+ Stage + ", Got: " + actualStatus1;
        fullDetails.click();
        Thread.sleep(1000);

        Locator AddFollowupbtn = page.locator("(//span[contains(text(), 'Add-Followup')])[1]");
        if (AddFollowupbtn.count() > 0) {
        	AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        	AddFollowupbtn.click();
        } else {
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
	        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
	        Thread.sleep(1000);
	        if (!fullDetails.isVisible()) {
	            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
	        }
	        fullDetails.click();
            AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            AddFollowupbtn.click();
        }

        String ActualStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[6]").trim();
        String ActualComments = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[8]").trim();
        String ActualSubStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[9]").trim();
        String ActualDateTime = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[7]").trim();

     

     // Inside your test method
     SoftAssert softAssert = new SoftAssert();

     softAssert.assertEquals(ActualStage, Stage,
             "Stage mismatch. Expected: " + Stage + ", Got: " + ActualStage);
     softAssert.assertEquals(ActualComments, Remark,
             "Remark mismatch. Expected: " + Remark + ", Got: " + ActualComments);
     softAssert.assertEquals(ActualSubStage, SubStage,
             "Sub Stage mismatch. Expected: " + SubStage + ", Got: " + ActualSubStage);
     softAssert.assertEquals(ActualDateTime, Verifydatetime,
             "FollowUp date and time mismatch. Expected: " + Verifydatetime + ", Got: " + ActualDateTime);

     System.out.println("Validation is done from History");

     // history close button logic
     Locator closebtn = page.locator("(//button[@type='button'])[4]");

     // Keep clicking until the button is no longer visible
     while (closebtn.isVisible()) {
         if (closebtn.isEnabled()) {
             closebtn.click();
             System.out.println("Clicked history close button...");
         } else {
             System.out.println("History close button is disabled, waiting...");
         }
         page.waitForTimeout(1000); // Small wait to avoid tight loop
     }

     System.out.println("History close button is no longer visible.");
     Thread.sleep(500);

     // Collect all soft assertion results at the end
     softAssert.assertAll();


    }
    
    public void addfollowUPWithOutChiledstage(String Stage, String SubStage, String Remark, String ExpectedFilter) throws InterruptedException {
    	actualPhone = page.textContent(fullDetailsText).trim();
    	page.waitForSelector("button:has-text('Add Lead Follow up')", 
    			new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    		page.click("button:has-text('Add Lead Follow up')");

        page.click("button:has-text('" + Stage + "')");
        page.click("button:has-text('" + SubStage + "')");

        // Add remark
        page.fill("#remarks", Remark);

        // Submit
        Locator submitButton = page.locator("//button[@type='submit']");

     // Keep clicking until the button is no longer visible
     while (submitButton.isVisible()) {
         if (submitButton.isEnabled()) {
             submitButton.click();
         }
         page.waitForTimeout(2000);
     }

        page.click("//div[contains(text(), '"+ExpectedFilter+"')]");
        Thread.sleep(500);
        String phonePart = actualPhone.replace("Mobile no -", "").trim();
        String[] parts = phonePart.split(" ", 2); 
        String ActualphoneNumber = parts[1]; 
        System.out.println("Lead phone Number: " + ActualphoneNumber);

        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
        Thread.sleep(1000);
        Locator fullDetails = page.locator(fullDetailsText);
        if (!fullDetails.isVisible()) {
            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        }
        String actualStatus1 = page.textContent("span:has-text('"+Stage+"')").trim();
            System.out.println("Actual Status: " + actualStatus1);
            assert actualStatus1.equals(Stage): "Follow Up Stage mismatch. Expected: "+ Stage + ", Got: " + actualStatus1;
        fullDetails.click();
        Thread.sleep(500);

        Locator AddFollowupbtn = page.locator("(//span[contains(text(), 'Add-Followup')])[1]");
        if (AddFollowupbtn.count() > 0) {
        	AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        	AddFollowupbtn.click();
        } else {
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
	        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
	        Thread.sleep(1000);
	        if (!fullDetails.isVisible()) {
	            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
	        }
	        fullDetails.click();
            AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            AddFollowupbtn.click();
        }

        String ActualStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[6]").trim();
        String ActualComments = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[8]").trim();
        String ActualSubStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[9]").trim();
        
        
//       

     // Inside your test method
     SoftAssert softAssert = new SoftAssert();

     softAssert.assertEquals(ActualStage, Stage,
             "Stage mismatch. Expected: " + Stage + ", Got: " + ActualStage);
     softAssert.assertEquals(ActualComments, Remark,
             "Remark mismatch. Expected: " + Remark + ", Got: " + ActualComments);
     softAssert.assertEquals(ActualSubStage, SubStage,
             "Sub Stage mismatch. Expected: " + SubStage + ", Got: " + ActualSubStage);

     System.out.println("Validation is done from History");

     // history close button logic
     Locator closebtn = page.locator("(//button[@type='button'])[4]");

     // Keep clicking until the button is no longer visible
     while (closebtn.isVisible()) {
         if (closebtn.isEnabled()) {
             closebtn.click();
             System.out.println("Clicked history close button...");
         } else {
             System.out.println("History close button is disabled, waiting...");
         }
         page.waitForTimeout(1000); // Small wait to avoid tight loop
     }

     System.out.println("History close button is no longer visible.");
     Thread.sleep(500);

     // Collect all soft assertion results at the end
     softAssert.assertAll();


    }
    
    public void addFollowUpForLostStage(String Stage, String SubStage, String ChildStage,String Remark, String ExpectedFilter) throws InterruptedException {
    	actualPhone = page.textContent(fullDetailsText).trim();
    	page.waitForSelector("button:has-text('Add Lead Follow up')", 
    			new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    		page.click("button:has-text('Add Lead Follow up')");

        page.click("button:has-text('" + Stage + "')");
        page.click("button:has-text('" + SubStage + "')");
        page.click("button:has-text('" + ChildStage + "')");
        page.fill("#remarks", Remark);

        // Submit
        Locator submitButton = page.locator("//button[@type='submit']");

     // Keep clicking until the button is no longer visible
     while (submitButton.isVisible()) {
         if (submitButton.isEnabled()) {
             submitButton.click();
         }
         page.waitForTimeout(2000);
     }

        page.click("//div[contains(text(), '"+ExpectedFilter+"')]");
        Thread.sleep(500);
        String phonePart = actualPhone.replace("Mobile no -", "").trim();
        String[] parts = phonePart.split(" ", 2); 
        String ActualphoneNumber = parts[1]; 
        System.out.println("Lead phone Number: " + ActualphoneNumber);

        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
        Thread.sleep(1000);
        Locator fullDetails = page.locator(fullDetailsText);
        if (!fullDetails.isVisible()) {
            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        }
        String actualStatus1 = page.textContent("span:has-text('"+Stage+"')").trim();
            System.out.println("Actual Status: " + actualStatus1);
            assert actualStatus1.equals(Stage): "Follow Up Stage mismatch. Expected: "+ Stage + ", Got: " + actualStatus1;
        fullDetails.click();
        Thread.sleep(500);

        Locator AddFollowupbtn = page.locator("(//span[contains(text(), 'Add-Followup')])[1]");
        if (AddFollowupbtn.count() > 0) {
        	AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        	AddFollowupbtn.click();
        } else {
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
	        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
	        Thread.sleep(1000);
	        if (!fullDetails.isVisible()) {
	            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
	        }
	        fullDetails.click();
            AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            AddFollowupbtn.click();
        }

      
        String ActualChildStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[10]").trim();
        String ActualStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[6]").trim();
        String ActualComments = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[8]").trim();
        String ActualSubStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[9]").trim();

     SoftAssert softAssert = new SoftAssert();

     softAssert.assertEquals(ActualStage, Stage,
             "Stage mismatch. Expected: " + Stage + ", Got: " + ActualStage);
     softAssert.assertEquals(ActualComments, Remark,
             "Remark mismatch. Expected: " + Remark + ", Got: " + ActualComments);
     softAssert.assertEquals(ActualSubStage, SubStage,
             "Sub Stage mismatch. Expected: " + SubStage + ", Got: " + ActualSubStage);
     softAssert.assertEquals(ActualChildStage, ChildStage,
             "Sub Stage mismatch. Expected: " + ChildStage + ", Got: " + ActualChildStage);

     System.out.println("Validation is done from History");

     // history close button logic
     Locator closebtn = page.locator("(//button[@type='button'])[4]");

     // Keep clicking until the button is no longer visible
     while (closebtn.isVisible()) {
         if (closebtn.isEnabled()) {
             closebtn.click();
             System.out.println("Clicked history close button...");
         } else {
             System.out.println("History close button is disabled, waiting...");
         }
         page.waitForTimeout(1000); // Small wait to avoid tight loop
     }

     System.out.println("History close button is no longer visible.");
     Thread.sleep(500);

     // Collect all soft assertion results at the end
     softAssert.assertAll();


    }
    public void addFollowUpForunitnumber(String Stage, String SubStage, String UnitNo,String Remark, String ExpectedFilter) throws InterruptedException {
    	actualPhone = page.textContent(fullDetailsText).trim();
    	page.waitForSelector("button:has-text('Add Lead Follow up')", 
    			new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    		page.click("button:has-text('Add Lead Follow up')");

        page.click("button:has-text('" + Stage + "')");
        page.click("button:has-text('" + SubStage + "')");
        page.fill("//input[@placeholder='[block number]-[flat number] (e.g., A-101)']",UnitNo);
        

       

        // Add remark
        page.fill("#remarks", Remark);

        // Submit
        Locator submitButton = page.locator("//button[@type='submit']");

     // Keep clicking until the button is no longer visible
     while (submitButton.isVisible()) {
         if (submitButton.isEnabled()) {
             submitButton.click();
         }
         page.waitForTimeout(2000);
     }

        page.click("//div[contains(text(), '"+ExpectedFilter+"')]");
        Thread.sleep(500);
        String phonePart = actualPhone.replace("Mobile no -", "").trim();
        String[] parts = phonePart.split(" ", 2); 
        String ActualphoneNumber = parts[1]; 
        System.out.println("Lead phone Number: " + ActualphoneNumber);

        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
        Thread.sleep(1000);
        Locator fullDetails = page.locator(fullDetailsText);
        if (!fullDetails.isVisible()) {
            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        }
        String actualStatus1 = page.textContent("span:has-text('"+Stage+"')").trim();
            System.out.println("Actual Status: " + actualStatus1);
            assert actualStatus1.equals(Stage): "Follow Up Stage mismatch. Expected: "+ Stage + ", Got: " + actualStatus1;
        fullDetails.click();
        Thread.sleep(500);

        Locator AddFollowupbtn = page.locator("(//span[contains(text(), 'Add-Followup')])[1]");
        if (AddFollowupbtn.count() > 0) {
        	AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        	AddFollowupbtn.click();
        } else {
            page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
	        page.click("div.cursor-pointer.flex.items-center.justify-end");
	        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", ActualphoneNumber);
	        Thread.sleep(1000);
	        if (!fullDetails.isVisible()) {
	            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
	        }
	        fullDetails.click();
            AddFollowupbtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            AddFollowupbtn.click();
        }
        
        String ActualStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[6]").trim();
        String ActualComments = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[8]").trim();
        String ActualSubStage = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[9]").trim();
        String ActualUnitNo = page.textContent("(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[10]").trim();
        System.out.println("Actual Unit No"+ActualUnitNo);
        
    

     // Inside your test method
     SoftAssert softAssert = new SoftAssert();

     softAssert.assertEquals(ActualStage, Stage,
             "Stage mismatch. Expected: " + Stage + ", Got: " + ActualStage);
     softAssert.assertEquals(ActualComments, Remark,
             "Remark mismatch. Expected: " + Remark + ", Got: " + ActualComments);
     softAssert.assertEquals(ActualSubStage, SubStage,
             "Sub Stage mismatch. Expected: " + SubStage + ", Got: " + ActualSubStage);
     softAssert.assertEquals(ActualUnitNo, UnitNo,
             "Sub Stage mismatch. Expected: " + UnitNo + ", Got: " + ActualUnitNo);
     
    
     System.out.println("Validation is done from History");

     // history close button logic
     Locator closebtn = page.locator("(//button[@type='button'])[4]");

     // Keep clicking until the button is no longer visible
     while (closebtn.isVisible()) {
         if (closebtn.isEnabled()) {
             closebtn.click();
             System.out.println("Clicked history close button...");
         } else {
             System.out.println("History close button is disabled, waiting...");
         }
         page.waitForTimeout(1000); // Small wait to avoid tight loop
     }

     System.out.println("History close button is no longer visible.");
     Thread.sleep(500);

     // Collect all soft assertion results at the end
     softAssert.assertAll();


    }
}
