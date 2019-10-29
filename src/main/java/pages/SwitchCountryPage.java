package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchCountryPage extends PageBase {
	WebDriver driver;

	public SwitchCountryPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[contains(@src,'/static')]")
	private WebElement switchCountry;
	
	public boolean isSwitchCountryVisible() {
		waitForElement(driver, switchCountry, 10);
		return switchCountry.isEnabled();
	}
	
	public void clickSwitchCountry()
	{
		if(isSwitchCountryVisible())
		{
			switchCountry.click();
		}
		else
		{
			System.out.println("Switch Country not visible");
		}	
	}
	
	@FindBy(xpath="(//div[contains(@class,'selectField__control')])[1]")
	private WebElement selectCountry;
	
	public void isCountryVisible() {
		waitForElement(driver, selectCountry, 10);
	}

	public WebElement clickSelectCountry()
	{
		isCountryVisible();
		return selectCountry;
		}
	
	@FindBy(xpath="(//div[contains(@class,'selectField__control')])[3]")
	private WebElement selectLanguage;
	
	public void isLanguageVisible() {
		waitForElement(driver, selectLanguage, 10);
		
	}
	
	public WebElement clickLanguageSelect()
	{
		isLanguageVisible();
		return selectLanguage;
	}
	
	
	@FindBy(xpath="//span[text()='Update']")
	private WebElement updateButton;
	
	public void clickUpdateButton()
	{
		updateButton.click();
	}		
}