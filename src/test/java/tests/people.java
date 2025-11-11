package tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import utils.CountryCodeMapper;
import utils.PhoneNumber;
import utils.ScreenshotUtil;

public class people extends BaseTest{
    public String phone;
    @AfterMethod
	public void takeScreenshot(ITestResult result) throws InterruptedException {
		ScreenshotUtil.capture(page, result, phone);
	}
    @Test
    public void Valid_add_user() throws InterruptedException{
        String firstNmae ="Roshan";
        String LastNmae ="Jadhav";
        String Country ="+91";
        String countryCode = CountryCodeMapper.getCountryCode(Country);
        String phone = PhoneNumber.generateUniquePhoneNumber(countryCode);
        String email =firstNmae+LastNmae+phone+"@yopmail.com";
        adduser.fillAddUserForm(firstNmae, LastNmae, Country, phone, email);
    }
    @Test
    public void Valid_add_user_1() throws InterruptedException{
        String firstNmae ="kunsh";
        String LastNmae ="Jadhav";
        String Country ="+91";
        String countryCode = CountryCodeMapper.getCountryCode(Country);
        String phone = PhoneNumber.generateUniquePhoneNumber(countryCode);
        String email =firstNmae+LastNmae+phone+"@yopmail.com";
        adduser.fillAddUserForm(firstNmae, LastNmae, Country, phone, email);
    }
    
}
