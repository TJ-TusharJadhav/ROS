package utils;

import java.nio.file.Paths;
import java.util.Arrays;

import org.testng.ITestResult;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ScreenshotUtil {

    public static void captureforCRM(Page page, ITestResult result, String phone) throws InterruptedException {
    		if (ITestResult.FAILURE == result.getStatus()) {

    	        Object[] parameters = result.getParameters();
    	        String testData = "";
    	        if (parameters != null && parameters.length > 0) {
    	            testData = "_" + String.join("_",
    	                    Arrays.stream(parameters)
    	                            .map(Object::toString)
    	                            .toArray(String[]::new));
    	        }

    	        String fileName = phone +"_"+result.getName() + testData + "_" +  ".png";
    	        String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    	        try {
    	            if (page != null) {
    	                page.screenshot(new Page.ScreenshotOptions()
    	                        .setPath(Paths.get("screenshots/"+today+"/"+result.getName()+"/" + fileName))
    	                        .setFullPage(true)     // 👈 safer than true
    	                        .setTimeout(60000));    // 👈 shorter timeout
    	                System.out.println("❌ Screenshot saved: " + fileName);
    	            }
    	        } catch (Exception e) {
    	            System.err.println("⚠️ Could not take screenshot: " + e.getMessage());
    	        }
    	        Locator closeBtn = page.locator("//button[contains(@class,'mantine-Modal-close')]");
    	        if (closeBtn.isVisible()) {
    	            closeBtn.click();
    	            System.out.println("Fail: Modal close button clicked!");
    	        } else {
    	            System.out.println("Fail: Modal close button not found, skipping...");
    	        }
    	    } else {
    	        Locator closeBtn = page.locator("//button[contains(@class,'mantine-Modal-close')]");
    	        if (closeBtn.isVisible()) {
    	            closeBtn.click();
    	            System.out.println("Pass: Modal close button clicked!");
    	        } else {
    	            System.out.println("Pass: Modal close button not found, skipping...");
    	        }
//                page.waitForSelector("div.cursor-pointer.flex.items-center.justify-end");
//    	        page.click("div.cursor-pointer.flex.items-center.justify-end");
//    	        Thread.sleep(1000);
    	    }
    	
    }
	public static void captureforEmp(Page page, ITestResult result) throws InterruptedException {
    		if (ITestResult.FAILURE == result.getStatus()) {

    	        Object[] parameters = result.getParameters();
    	        String testData = "";
    	        if (parameters != null && parameters.length > 0) {
    	            testData = "_" + String.join("_",
    	                    Arrays.stream(parameters)
    	                            .map(Object::toString)
    	                            .toArray(String[]::new));
    	        }

    	        String fileName = result.getName() + testData + "_" +  ".png";
    	        String today = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    	        try {
    	            if (page != null) {
    	                page.screenshot(new Page.ScreenshotOptions()
    	                        .setPath(Paths.get("screenshots/"+today+"/"+result.getName()+"/" + fileName))
    	                        .setFullPage(true)     // 👈 safer than true
    	                        .setTimeout(60000));    // 👈 shorter timeout
    	                System.out.println("❌ Screenshot saved: " + fileName);
    	            }
    	        } catch (Exception e) {
    	            System.err.println("⚠️ Could not take screenshot: " + e.getMessage());
    	        }
    	        
    	    }
    	
    }
}


