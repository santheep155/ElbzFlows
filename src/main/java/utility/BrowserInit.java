package utility;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserInit {
		
	public static WebDriver getDriver() throws Exception {	
		WebDriver driver=null;
		
		try {
			
		if((Config.getBrowserName()).equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", Config.getChromeDriverLoc());
			driver = new ChromeDriver();		
		}
		else if((Config.getBrowserName()).equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", Config.getFirefoxDriverLoc());
		    driver = new FirefoxDriver();	
		}
		}
		    catch(Exception e){
		    	System.out.println("Incorrect browser provided "+Config.getBrowserName());
		    	throw e;
		    }
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Config.getImplicitWaitTimeout(),TimeUnit.SECONDS);
		return driver;
		}
	
	
	public void getScreenshot(String result) throws WebDriverException, Exception
	{
		
			File src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(Config.getScreenshotPath()+result+"screenshot.png"));;
		
	}
}