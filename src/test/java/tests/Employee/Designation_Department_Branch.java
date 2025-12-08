package tests.Employee;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTest;
import listeners.ExtentTestNGListener;
import utils.ScreenshotUtil;
@Listeners(ExtentTestNGListener.class)
public class Designation_Department_Branch extends BaseTest{
    private final String Employee = "(//p[@data-size='sm'])[9]";

    @AfterMethod
		public void takeScreenshot(ITestResult result) throws InterruptedException {
			ScreenshotUtil.captureforEmp(page, result);
		}
    @BeforeClass
        public void before() throws InterruptedException{
            page.click(Employee);
            Thread.sleep(500);
        }
     
    @Test (priority = 4)
	public void SeatingOffice() throws InterruptedException {
        emp.AddSeatingOffice();
    }

    @Test (priority = 1)
	public void Designation() throws InterruptedException {
        emp.AddDesignations();
    }

    @Test (priority = 2)
	public void Department() throws InterruptedException {
        emp.AddDepartment();
    }
    
    @Test (priority = 3)
	public void Branch() throws InterruptedException {
        emp.AddBranch();
    }
    
}
