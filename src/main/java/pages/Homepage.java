package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage extends PageBase{
	
	WebDriver driver;
	
	public Homepage(WebDriver driver){
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@id='myAccount']")
	private WebElement loginbutton;
	
	@FindBy(xpath="//a[contains(@href,'home')]")
	private WebElement homelink;
	
	public boolean isLoginButtonVisible() {
		waitForElement(driver, loginbutton, 20);
		return loginbutton.isDisplayed();
	}
	
	public boolean isHomeLinkVisible() {
		waitForElement(driver, homelink, 20);
		return homelink.isDisplayed();
	}
	
	public void clickHome()
	{
		isHomeLinkVisible();
		System.out.println("Login button visible");
		homelink.click();
		System.out.println("Login button click");
	}
	
	public void clickLogin()
	{
		isLoginButtonVisible();
		System.out.println("Login button visible");
		loginbutton.click();
		System.out.println("Login button click");
	}
	
	
	
}
