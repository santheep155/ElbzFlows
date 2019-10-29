package pages;

import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
	
	public void waitForElement(WebDriver driver, WebElement ele, long timeOut) {
		new WebDriverWait(driver,timeOut)
		.ignoring(NoSuchElementException.class)
		.pollingEvery(Duration.ofSeconds(2))
		.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public boolean verifyIfAuthenticationPageVisible(WebDriver driver, WebElement ele, long timeOut) {
		waitForElement(driver,ele,timeOut);
		return ele.isDisplayed();
	}
	
	public void elementClickable(WebDriver driver, WebElement ele, long timeOut) {
		new WebDriverWait(driver,timeOut)
		.ignoring(NoSuchElementException.class)
		.pollingEvery(Duration.ofSeconds(2))
		.until(ExpectedConditions.elementToBeClickable(ele));
	}


}