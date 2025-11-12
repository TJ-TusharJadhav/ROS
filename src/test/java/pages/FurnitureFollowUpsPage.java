package pages;

import java.text.ParseException;
import java.util.Map;

import org.testng.asserts.SoftAssert;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import utils.DateTimeUtil;

public class FurnitureFollowUpsPage {
    private Page page;
    private String actualPhone;

    private String fullDetailsText = "(//p[@class='text-gray-500 mb-3 text-base break-words'])[1]";
    private String dateField = "//input[@class='MuiPickersInputBase-input MuiPickersOutlinedInput-input css-1ftw2zb']";
    private String remark="//textarea[@placeholder='Enter remarks']";
    private String submit = "//button[contains(text(), 'Submit')]";
    
    private String StageInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[1]";
    private String CommentsInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[3]";
    private String SubStageInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[4]";
    private String DateTimeInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[2]";
    private String ChildStageInHistory = "(//p[@class='font-medium text-xs sm:text-sm break-words whitespace-normal'])[5]";

Map<String, String> dateTimeMap = DateTimeUtil.getFormattedDateTimePlus20Mins();
	private String Enterdatetime = dateTimeMap.get("Enterdatetime");
	// private String Verifydatetime = dateTimeMap.get("Verifydatetime");
    
    public FurnitureFollowUpsPage(Page page) {
        this.page = page;
    }

    /** Common close button logic */
    private void closeHistoryPopup() {
        Locator closeBtn = page.locator("(//button[@type='button'])[4]");
        while (closeBtn.isVisible()) {
            if (closeBtn.isEnabled()) closeBtn.click();
            page.waitForTimeout(1000);
        }
    }

    /** Adds Follow Up with DateTime field */
    public void addFollowUP(String Stage, String SubStage, String Remark, String ExpectedFilter)
            throws InterruptedException, ParseException {

        actualPhone = page.textContent(fullDetailsText).trim();
        System.out.println("actualPhone: " + actualPhone);

        page.waitForSelector("button:has-text('Add Lead Follow up')", 
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        page.click("button:has-text('Add Lead Follow up')");
        page.click("button:has-text('" + Stage + "')");
        page.click("button:has-text('" + SubStage + "')");

        Thread.sleep(1000);
        page.click("//div[@class='MuiFormControl-root MuiFormControl-fullWidth MuiPickersTextField-root css-2abjvj']");
        Thread.sleep(1000);

        
        page.fill(dateField, Enterdatetime);
        
        page.fill(remark, Remark);

        Locator submitButton = page.locator(submit);
        while (submitButton.isVisible()) {
            if (submitButton.isEnabled()) submitButton.click();
            page.waitForTimeout(2000);
        }

        page.click("//div[contains(text(), '" + ExpectedFilter + "')]");
        Thread.sleep(1000);

        String phonePart = actualPhone.replace("Mobile no -", "").trim().split(" ", 2)[1];
        System.out.println("Lead phone Number: " + phonePart);

        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", phonePart);
        Thread.sleep(1000);

        Locator fullDetails = page.locator(fullDetailsText);
        if (!fullDetails.isVisible()) {
            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        }
        fullDetails.click();
        Thread.sleep(1000);

        Locator addFollowupBtn = page.locator("(//span[contains(text(), 'Add-Followup')])[1]");
        if (addFollowupBtn.count() == 0) {
            page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", phonePart);
            Thread.sleep(1000);
            fullDetails.click();
        }
        addFollowupBtn.click();
        String actualStage =page.textContent(StageInHistory).trim();
        String actualComments =page.textContent(CommentsInHistory).trim();
        String actualSubStage =page.textContent(SubStageInHistory).trim();
        String actualDateTime =page.textContent(DateTimeInHistory).trim();
        

       

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualStage, Stage, "Stage mismatch");
        softAssert.assertEquals(actualComments, Remark, "Remark mismatch");
        softAssert.assertEquals(actualSubStage, SubStage, "SubStage mismatch");
        
        softAssert.assertEquals(actualDateTime, Enterdatetime,
                "FollowUp date and time mismatch. Expected: " + Enterdatetime + ", Got: " + actualDateTime);
        System.out.println("Validation done from History");
        closeHistoryPopup();
        softAssert.assertAll();
    }

    /** Adds Follow Up without child stage */
    public void addfollowUPWithOutChildStage(String Stage, String SubStage, String Remark, String ExpectedFilter)
            throws InterruptedException {

        actualPhone = page.textContent(fullDetailsText).trim();
        page.click("button:has-text('Add Lead Follow up')");
        page.click("button:has-text('" + Stage + "')");
        page.click("button:has-text('" + SubStage + "')");
        
        page.fill(remark, Remark);

        Locator submitButton = page.locator(submit);
        while (submitButton.isVisible()) {
            if (submitButton.isEnabled()) submitButton.click();
            page.waitForTimeout(2000);
        }

        page.click("//div[contains(text(), '" + ExpectedFilter + "')]");
        Thread.sleep(500);

        String phonePart = actualPhone.replace("Mobile no -", "").trim().split(" ", 2)[1];
        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", phonePart);
        Thread.sleep(1000);

        Locator fullDetails = page.locator(fullDetailsText);
        if (!fullDetails.isVisible()) {
            fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
        }
        fullDetails.click();
        Thread.sleep(1000);

        Locator addFollowupBtn = page.locator("(//span[contains(text(), 'Add-Followup')])[1]");
        if (addFollowupBtn.count() == 0) {
            page.click("div.cursor-pointer.flex.items-center.justify-end");
            page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", phonePart);
            Thread.sleep(1000);
            fullDetails.click();
        }
        addFollowupBtn.click();
        Thread.sleep(500);
        
        String actualStage =page.textContent(StageInHistory).trim();
        String actualComments =page.textContent(CommentsInHistory).trim();
        String actualSubStage =page.textContent(SubStageInHistory).trim();

        
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualStage, Stage, "Stage mismatch");
        softAssert.assertEquals(actualComments, Remark, "Remark mismatch");
        softAssert.assertEquals(actualSubStage, SubStage, "SubStage mismatch");

        closeHistoryPopup();
        softAssert.assertAll();
    }

    /** Adds Follow Up for lost stage (with child stage) */
    public void addFollowUpForLostStage(String Stage, String SubStage, String ChildStage, String Remark, String ExpectedFilter)
            throws InterruptedException {

        actualPhone = page.textContent(fullDetailsText).trim();
        page.click("button:has-text('Add Lead Follow up')");
        page.click("button:has-text('" + Stage + "')");
        page.click("button:has-text('" + SubStage + "')");
        page.click("button:has-text('" + ChildStage + "')");
        page.fill(remark, Remark);

        Locator submitButton = page.locator(submit);
        while (submitButton.isVisible()) {
            if (submitButton.isEnabled()) submitButton.click();
            page.waitForTimeout(2000);
        }

        page.click("//div[contains(text(), '" + ExpectedFilter + "')]");
        Thread.sleep(500);

        String phonePart = actualPhone.replace("Mobile no -", "").trim().split(" ", 2)[1];
        page.fill("//input[@placeholder='Search Leads by Name or Mobile No.']", phonePart);
        Thread.sleep(1000);

        Locator fullDetails = page.locator(fullDetailsText);
        fullDetails.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        fullDetails.click();
        Thread.sleep(1000);

        Locator addFollowupBtn = page.locator("(//span[contains(text(), 'Add-Followup')])[1]");
        addFollowupBtn.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        addFollowupBtn.click();
        
        String actualStage =page.textContent(StageInHistory).trim();
        String actualComments =page.textContent(CommentsInHistory).trim();
        String actualSubStage =page.textContent(SubStageInHistory).trim();
        String actualChildStage =page.textContent(ChildStageInHistory).trim();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualStage, Stage, "Stage mismatch");
        softAssert.assertEquals(actualComments, Remark, "Remark mismatch");
        softAssert.assertEquals(actualSubStage, SubStage, "SubStage mismatch");
        softAssert.assertEquals(actualChildStage, ChildStage, "Child stage mismatch");

        closeHistoryPopup();
        softAssert.assertAll();
    }

}
