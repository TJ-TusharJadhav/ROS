package tests;

import org.testng.annotations.Test;

import base.BaseTest;

public class add_Employee extends BaseTest{
    @Test
    public void addemp() throws InterruptedException{
        page.click("(//p[@data-size='sm'])[9]");
        Thread.sleep(1000);
        page.click("(//li[normalize-space(text())='Employee List'])[1]");
        page.click("//button[normalize-space(text())='+ Add Employee']");
        page.fill("#firstName", "Tushar");
        page.fill("#lastName", "Jadhav");
        page.fill("#mobile", "8378845340");
        page.click("text=Basic Details");
        Thread.sleep(500);
    	page.click("text=Job Information");
        page.click("#designation");
        page.fill("#designation", "Carpenter");
        page.locator("(//div[contains(@class,'option')])[1]").click();
        page.click("#department");
        page.fill("#department", "HR");
        page.locator("(//div[contains(@class,'option')])[1]").click();
        page.type("#dateOfJoin", "12/11/2025");
        page.selectOption("#employeeType", "Full Time");
        page.fill("#employeeId", "Tech001");

        page.click("#branch");
        page.fill("#branch", "Gandhinagar");
        page.locator("(//div[contains(@class,'option')])[1]").click();
        
        page.click("#seatingOffice");
        page.fill("#seatingOffice", "Wave Site");
        page.locator("(//div[contains(@class,'option')])[1]").click();
        
        page.click("#shiftManagement");
        page.fill("#shiftManagement", "QA ( 10:30 AM to 4 PM )");
        page.locator("(//div[contains(@class,'option')])[1]").click();
        
        page.click("#geoFencingLocations");
        page.fill("#geoFencingLocations", "Wave Site");
        page.locator("(//div[contains(@class,'option')])[1]").click();

        page.click("//button[@type='submit']");
        Thread.sleep(10000);

    }
    
}
