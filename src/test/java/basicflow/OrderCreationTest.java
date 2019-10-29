package basicflow;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
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
		hmpage.clickLogin();
		reporter().log(LogStatus.PASS, "Clicked on SignIn button");

		loginpage = new LoginPage(getDriver());
		loginpage.isLoginTextVisible();
		loginpage.login(Config.getUsername(), Config.getPassword());
		Assert.assertEquals(loginpage.getPageTitle(), "Account | Elabelz");
		reporter().log(LogStatus.PASS, "Successfully Logged in to user account");

		pdtpage = new ProductPage(getDriver());
		pdtpage.isMenuVisible();
		pdtpage.singleTransaction(Config.getOrderCount());
		System.out.println("Order placed successfully. Order Number is: "+pdtpage.getOrderNumber());
		reporter().log(LogStatus.PASS, "Order placed successfully. Order Number is: "+pdtpage.getOrderNumber());

	}

	@Test(enabled = false)
	public void orderCreationSaudi() throws InterruptedException {
		swpage = new SwitchCountryPage(getDriver());
		swpage.clickSwitchCountry();
		ac = new Actions(getDriver());
		Thread.sleep(3000);
		ac.moveToElement(swpage.clickSelectCountry()).click().sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(3000);
		ac.moveToElement(swpage.clickSelectCountry()).sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(3000);
		ac.moveToElement(swpage.clickSelectCountry()).sendKeys(Keys.ARROW_DOWN).build().perform();
		Thread.sleep(3000);
		ac.moveToElement(swpage.clickSelectCountry()).sendKeys(Keys.ENTER).build().perform();

		ac.moveToElement(swpage.clickLanguageSelect()).click().sendKeys(Keys.ARROW_DOWN).build().perform();
		ac.moveToElement(swpage.clickLanguageSelect()).sendKeys(Keys.ENTER).build().perform();

		swpage.clickUpdateButton();

		Thread.sleep(5000);

		hmpage = new Homepage(getDriver());
		Assert.assertEquals(hmpage.isLoginButtonVisible(), true, "Login button not visible");
		hmpage.clickLogin();
		System.out.println("Clicked on SignIn button");

		loginpage = new LoginPage(getDriver());
		loginpage.isLoginTextVisible();
		loginpage.login(Config.getUsername(), Config.getPassword());
		Assert.assertEquals(loginpage.getPageTitle(), "Account | Elabelz");
		System.out.println("Successfully Logged in to user account");

		pdtpage = new ProductPage(getDriver());
		pdtpage.isMenuVisible();
		pdtpage.singleTransaction(Config.getOrderCount());
		System.out.println("Order placed successfully");


	}

}
