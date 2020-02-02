package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Config;

public class ProductPage extends PageBase {
	WebDriver driver;
	LoginPage loginpage2;
	Homepage hmpage2;

	public ProductPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@id='main-nav-men']")
	private WebElement menuMen;

	@FindAll(@FindBy(xpath = "//span[@id='main-nav-men']"))
	private List<WebElement> findAllMenus;

	@FindBy(xpath = "//div[@id='division-menu-item-brands']")
	private WebElement brandLink;

	@FindBy(xpath = "//a[@class='productLink']")
	private WebElement firstProduct;

	@FindAll(@FindBy(xpath = "//a[@class='productLink']"))
	private List<WebElement> findAllProducts;

	@FindAll(@FindBy(xpath = "//a[@id='goToMyBag']/div"))
	private List<WebElement> findCartCount;

	@FindBy(xpath = "//button[contains(@class,'moveToBag')]/span")
	private WebElement movetoBag;

	@FindBy(xpath = "//span[contains(text(),'Item added to the cart')]")
	private WebElement itemexists;

	@FindBy(xpath = "//a[@id='goToMyBag']//*[@class='fH6iX']")
	private WebElement gotoCart;

	@FindBy(xpath = "//span[contains(text(),'Checkout')]")
	private WebElement checkoutButton;

	@FindBy(xpath = "//div[text()='Yousef Arif']")
	private WebElement selectAddress;

	@FindBy(xpath = "//span[contains(text(),'Continue')]")
	private WebElement continuebutton;

	@FindBy(xpath = "//span[contains(text(),'Pay by cash')]")
	private WebElement codPayment;

	@FindBy(xpath = "//span[contains(text(),'Place order and pay')]")
	private WebElement placeOrderButton;

	@FindBy(xpath = "//*[text()='You have already reached maximum available quantity.']")
	private WebElement fadeMessage;

	@FindBy(xpath = "//div[contains(text(),'Thank you for your order')]")
	private WebElement confirmationMessage;

	@FindBy(xpath = "//div[contains(text(),'Thank you for your order')]/following-sibling::p[1]")
	private WebElement orderNumber;

	@FindBy(xpath = "//a[text()='Creo']")
	private WebElement creoProduct;

	@FindBy(xpath = "//span[text()='Continue shopping']")
	private WebElement continueShopping;

	public boolean isMenuVisible() {
		waitForElement(driver, menuMen, 10);
		return menuMen.isDisplayed();
	}

	public int menuSize() {
		return findAllMenus.size();
	}

	public boolean isBrandsVisible() {
		waitForElement(driver, brandLink, 20);
		return brandLink.isDisplayed();
	}

	public boolean isFirstProductVisible() {
		waitForElement(driver, firstProduct, 20);
		return firstProduct.isDisplayed();
	}

	public boolean isBagVisible() {
		waitForElement(driver, movetoBag, 10);
		return movetoBag.isDisplayed();
	}

	public boolean isItemAdded() {
		waitForElement(driver, itemexists, 10);
		return itemexists.isDisplayed();
	}

	public boolean isgotoCartDisplayed() {
		waitForElement(driver, gotoCart, 20);
		return gotoCart.isDisplayed();
	}

	public boolean isCheckoutDisplayed() {
		waitForElement(driver, checkoutButton, 20);
		return checkoutButton.isDisplayed();
	}

	public boolean isAddressDisplayed() {
		waitForElement(driver, selectAddress, 10);
		return selectAddress.isDisplayed();
	}

	public boolean isContinueButtonDisplayed() {
		waitForElement(driver, continuebutton, 10);
		return continuebutton.isDisplayed();
	}

	public boolean isCODDisplayed() {
		waitForElement(driver, codPayment, 10);
		return codPayment.isDisplayed();
	}

	public WebElement getAllProducts(int i) {
		return findAllProducts.get(i);

	}

	public boolean isPlaceOrderDisplayed() {
		waitForElement(driver, placeOrderButton, 10);
		return placeOrderButton.isDisplayed();
	}

	public boolean isCreoBrandVisible() {
		waitForElement(driver, creoProduct, 10);
		return creoProduct.isDisplayed();
	}

	public boolean isConfirmationVisible() {
		waitForElement(driver, confirmationMessage, 10);
		return confirmationMessage.isDisplayed();
	}

	public String getOrderNumber() {
		String orderNum = (orderNumber.getText()).substring(13, 26);
		return orderNum;
	}

	public void singleTransaction(long orderCount) throws InterruptedException {

		if (menuSize() == 0) {
			driver.navigate().to("http://elabelz.com/ae/c/men");
			System.out.println("Menu Menu not visible");
			hmpage2 = new Homepage(driver);
			hmpage2.clickLogin();
			Thread.sleep(3000);
			loginpage2 = new LoginPage(driver);
			loginpage2.isLoginTextVisible();
			loginpage2.login(Config.getUsername(), Config.getPassword());
			Thread.sleep(3000);
			isMenuVisible();
			menuMen.click();
			System.out.println("Clicked on Menu : Men");

		} else {
			isMenuVisible();
			menuMen.click();
			System.out.println("Clicked on Menu : Men");
		}

		isBrandsVisible();
		brandLink.click();
		System.out.println("Clicked on Submenu : Brands");

		isCreoBrandVisible();
		creoProduct.click();
		Thread.sleep(3000);

		System.out.println("Clicked on Creo");

		for (int i = 0; i < orderCount; i++) {
			getAllProducts(i + 1).click();
			System.out.println("Clicked on the product: " + i);
			if (movetoBag.getText().equalsIgnoreCase("Move to bag")) {
				Thread.sleep(3000);
				movetoBag.click();
				System.out.println(i + " product added to the cart");
				Thread.sleep(3000);
				continueShopping.click();
			} else if (movetoBag.getText().equalsIgnoreCase("Item added to the cart")) {
				driver.navigate().back();
				System.out.println("Item is already added to cart");
			} else if (movetoBag.getText().equalsIgnoreCase("Item was sold out")) {
				driver.navigate().back();
				System.out.println("Item is sold out");
			}
		}

		System.out.println("All Products added to the cart");


		Thread.sleep(3000);
		driver.navigate().to("https://www.elabelz.com/ae/my-bag");
		System.out.println("Go to Cart");

		isCheckoutDisplayed();
		Thread.sleep(4000);
		checkoutButton.click();
		System.out.println("Checkout click");

		selectAddress.click();
		System.out.println("Address click");

		isContinueButtonDisplayed();
		continuebutton.click();
		System.out.println("Continue click");

		Thread.sleep(3000);
		isCODDisplayed();
		codPayment.click();
		System.out.println("COD click");

		Thread.sleep(2000);
		isPlaceOrderDisplayed();
		System.out.println("Place Order displayed");
		placeOrderButton.click();
		System.out.println("Place Order clicked");

		isConfirmationVisible();
		String confmessage = confirmationMessage.getText();
		System.out.println("Test completed with message: " + confmessage);
	}
}