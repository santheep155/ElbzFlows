package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@id='loginFormEmail']")
	private WebElement username;
	
	@FindBy(xpath="//input[@id='loginFormPassword']")
	private WebElement password;
	
	@FindBy(xpath="//button[@id='loginFormSubmit']")
	private WebElement clickSignin;
	
	@FindBy(xpath="//meta[contains(@content,'Account | Elabelz')]")
	private WebElement title;
	
	
	
	
	public boolean isLoginTextVisible() {
		waitForElement(driver, username, 30);
		return username.isDisplayed();
	}
	
	public String getPageTitle() {
		String pageTitle = title.getAttribute("content");
		return pageTitle;
	}
	
	public boolean isSignInVisible() {
		waitForElement(driver, clickSignin, 10);
		return clickSignin.isDisplayed();
	}
	
	
	public void login(String unam, String pwd)
	{
		username.clear();
		username.sendKeys(unam);
		System.out.println("Enter Username");
		password.clear();
		password.sendKeys(pwd);
		System.out.println("Enter Password");
		isSignInVisible();
		clickSignin.click();
		System.out.println("Click SignIn");
	}
}
