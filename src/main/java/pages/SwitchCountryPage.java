package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwitchCountryPage extends PageBase {
	WebDriver driver;
	Actions ac;

	public SwitchCountryPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[contains(@src,'/static')]")
	private WebElement switchCountry;

	@FindBy(xpath = "(//div[contains(@class,'selectField__control')])[1]")
	private WebElement selectCountry;

	@FindBy(xpath = "(//div[contains(@class,'selectField__control')])[3]")
	private WebElement selectLanguage;

	@FindBy(xpath = "//span[text()='Update']")
	private WebElement updateButton;

	@FindBy(xpath = "//img[@src='/static/assets/flags/sa.svg']")
	private WebElement saudiFlag;

	public boolean isSwitchCountryVisible() {
		waitForElement(driver, switchCountry, 10);
		return switchCountry.isDisplayed();
	}

	public void isCountryVisible() {
		waitForElement(driver, selectCountry, 10);
	}

	public void isLanguageVisible() {
		waitForElement(driver, selectLanguage, 10);
	}

	public void isStoreSaudi() {
		waitForElement(driver, saudiFlag, 10);
		saudiFlag.isDisplayed();
		System.out.println("Saudi Store selected");
	}

	public void switchToSaudi() throws InterruptedException {
		ac = new Actions(driver);

		isSwitchCountryVisible();
		switchCountry.click();

		isCountryVisible();
		ac.moveToElement(selectCountry).click().sendKeys(Keys.ARROW_DOWN).build().perform();
		ac.moveToElement(selectCountry).sendKeys(Keys.ARROW_DOWN).build().perform();
		ac.moveToElement(selectCountry).sendKeys(Keys.ARROW_DOWN).build().perform();
		ac.moveToElement(selectCountry).sendKeys(Keys.ENTER).build().perform();

		isLanguageVisible();
		Thread.sleep(1000);
		ac.moveToElement(selectLanguage).click().sendKeys(Keys.ARROW_DOWN).build().perform();
		ac.moveToElement(selectLanguage).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(1000);
		updateButton.click();

		isStoreSaudi();
		System.out.println("Store switched to Saudi");
	}
}