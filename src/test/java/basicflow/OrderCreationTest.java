package basicflow;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.Homepage;
import pages.LoginPage;
import pages.ProductPage;
import pages.SwitchCountryPage;
import utility.Config;

public class OrderCreationTest extends TestBase {
	WebDriver driver;
	Homepage hmpage;
	LoginPage loginpage;
	ProductPage pdtpage;
	SwitchCountryPage swpage;
	Actions ac;

	@Test
	public void orderCreationUAE() throws InterruptedException {
		hmpage = new Homepage(getDriver());
		Assert.assertEquals(hmpage.isLoginButtonVisible(), true, "Login button not visible");
		reporter().log(LogStatus.PASS, "Clicked on SignIn button");
		Thread.sleep(3000);
		hmpage.clickLogin();

		loginpage = new LoginPage(getDriver());
		loginpage.isLoginTextVisible();
		loginpage.login(Config.getUsername(), Config.getPassword());
		Assert.assertEquals(loginpage.getPageTitle(), "Account | Elabelz");
		reporter().log(LogStatus.PASS, "Successfully Logged in to user account");
		Thread.sleep(2000);
		
		pdtpage = new ProductPage(getDriver());
		pdtpage.singleTransaction(Config.getOrderCount());
		System.out.println("Order placed successfully. Order Number is: " + pdtpage.getOrderNumber());
		reporter().log(LogStatus.PASS, "Order placed successfully. Order Number is: " + pdtpage.getOrderNumber());
	}
}