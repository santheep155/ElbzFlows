package basicflow;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.BrowserInit;
import utility.Config;



public class TestBase {
	
	private WebDriver driver;
	private ExtentReports extentReport;
	private ExtentTest testReport;

	
	@BeforeSuite
	public void initSuite() throws Exception {
		Config.initConfigRead();
		extentReport = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\extentreport.html",true);
		}
	
	@BeforeMethod
	public void initTest(Method test) throws Exception {
		this.driver = BrowserInit.getDriver();
		this.driver.get(Config.getURL());
		testReport = extentReport.startTest(test.getName());
		
	}
		
	public WebDriver getDriver() {
		return driver;
		}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null) driver.quit();
		extentReport.endTest(testReport);
	}
	
	@AfterSuite
	public void writeReport() {
		extentReport.flush();
		extentReport.close();
	}
	public ExtentTest reporter() {
		return testReport;
	}
	
	public void report(boolean status,String pass,String fail) {
		if(status) {
			reporter().log(LogStatus.PASS,pass);
		}else {
			reporter().log(LogStatus.FAIL,fail);
		}
	}
	


}