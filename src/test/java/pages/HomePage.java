package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import base.ProjectSpecificationMethods;

public class HomePage extends ProjectSpecificationMethods {

	@FindBy(id = "signin2")
	WebElement signUpBtn;

	@FindBy(id = "login2")
	WebElement loginBtn;

	@FindBy(id = "nameofuser")
	WebElement nameInfoBtn;

	@FindBy(xpath = "//a[contains(text(),'Home ')]")
	WebElement homeBtn;

	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	WebElement contactBtn;

	@FindBy(xpath = "//a[contains(text(),'About us')]")
	WebElement aboutUsBtn;

	@FindBy(xpath = "//a[contains(text(),'Phones')]")
	WebElement phonesBtn;

	@FindBy(xpath = "//a[contains(text(),'Laptops')]")
	WebElement laptopsBtn;

	@FindBy(xpath = "//a[contains(text(),'Monitors')]")
	WebElement monitorsBtn;

	@FindBy(xpath = "//a[contains(text(),'Cart')]")
	WebElement cartBtn;

	@FindBy(id = "logout2")
	WebElement logoutBtn;

	@FindBy(xpath = "(//img[@src='bm.png'])[1]")
	WebElement logo;

	@FindBy(xpath = "//a[contains(text(),'Samsung galaxy s6')]")
	public WebElement product1;

	@FindBy(xpath = "//a[contains(text(),'PRODUCT STORE')]")
	public WebElement productStoreText;

	// public static String productName;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//Test if the "Sign up" button is visible on the homepage.
	public HomePage testSignUpButtonVisible() {
		Assert.assertTrue(waitForVisibilityOfElement(signUpBtn, 10).isDisplayed(),
				"Sign Up button is not visible on the homepage");
		Reporter.log("Signup Button is Visible on the HomePage", true);
		return this;
	}

//Verify that the "Sign Up" button is clickable.
	public HomePage testSignUpButtonClickable() {
		Assert.assertTrue(waitForClickabilityOfElement(signUpBtn, 10).isEnabled(), "Sign Up button is not clickable");
		Reporter.log("Signup Button is clickable on the HomePage", true);
		return this;
	}

	public SignupWindowPage testSignUpWindowOpens() {
		waitForClickabilityOfElement(signUpBtn, 10).click();
		return new SignupWindowPage(driver);
	}

//Test if the "Log in" button is visible on the homepage
	public HomePage testLoginButtonVisible() {
		Assert.assertTrue(waitForVisibilityOfElement(loginBtn, 10).isDisplayed(),
				"Login button is not visible on the homepage");
		Reporter.log("Login Button is Visible on the HomePage", true);
		return this;
	}

// Verify that the "Log in" button is clickable.
	public HomePage testLoginButtonClickable() {
		Assert.assertTrue(waitForClickabilityOfElement(loginBtn, 10).isEnabled(), "Login button is not clickable");
		Reporter.log("Login Button is Clickable", true);
		return this;
	}

	public LoginPage clickLoginMenuBtn() {
		waitForVisibilityOfElement(loginBtn, 10).click();
		return new LoginPage(driver);
	}

//Validate redirection to the user dashboard using valid credentials
	public HomePage userInfoValidation(String expected) {
		String actual = waitForVisibilityOfElement(nameInfoBtn, 10).getText();
		Assert.assertEquals(actual, expected);
		Reporter.log("redirection to the user dashboard using valid credentials is successful", true);
		return this;
	}

//Test “Welcome user” is displayed on the homepage.
	public HomePage welcomeUserValidation() {
		Assert.assertTrue(waitForVisibilityOfElement(nameInfoBtn, 10).isDisplayed());
		Reporter.log("“Welcome user” is displayed on the homepage", true);
		return this;
	}

//Verify all menu items are displayed on the homepage.(e.g.home,contact)
	public HomePage allMenuDisplayValidation() {
		Assert.assertTrue(waitForVisibilityOfElement(homeBtn, 10).isDisplayed(), "Home button is not displayed");
		Assert.assertTrue(waitForVisibilityOfElement(contactBtn, 10).isDisplayed(), "Contact button is not displayed");
		Assert.assertTrue(waitForVisibilityOfElement(aboutUsBtn, 10).isDisplayed(), "AboutUs button is not displayed");
		Assert.assertTrue(waitForVisibilityOfElement(cartBtn, 10).isDisplayed(), "Cart button is not displayed");
		Assert.assertTrue(waitForVisibilityOfElement(logoutBtn, 10).isDisplayed(), "Logout button is not displayed");
		Reporter.log("All menu items are displayed on the homepage.", true);
		return this;
	}

//Verify all categories are listed on the homepage.(e.g.,phones, laptops)
	public HomePage allcategoryDisplayValidation() {
		Assert.assertTrue(waitForVisibilityOfElement(phonesBtn, 10).isDisplayed(), "Phones button is not displayed");
		Assert.assertTrue(waitForVisibilityOfElement(laptopsBtn, 10).isDisplayed(), "Laptops button is not displayed");
		Assert.assertTrue(waitForVisibilityOfElement(monitorsBtn, 10).isDisplayed(),
				"Monitors button is not displayed");
		Reporter.log("All categories are listed on the homepage.", true);
		return this;
	}

//Verify application logo is displayed on the homepage.
	public HomePage logoVerificaton() {
		Assert.assertTrue(waitForVisibilityOfElement(logo, 10).isDisplayed(), "Logo is not displayed in the homepage");
		Reporter.log("Application logo is displayed on the homepage", true);
		return this;
	}

	public ProductDetailsPage clickAProduct() {
		// productName = waitForVisibilityOfElement(product1, 10).getText();
		waitforSeconds(2);
		product1.click();
		// waitForVisibilityOfElement(product1, 10).click();
		return new ProductDetailsPage(driver);
	}

	public CartPage clickCartBtn() {
		waitforSeconds(2);
		cartBtn.click();
		return new CartPage(driver);
	}

//Ensure redirection to the home page after clicking "Log out" button.
	public HomePage homePageValidation() {
		Assert.assertTrue(waitForVisibilityOfElement(productStoreText, 10).isDisplayed(),
				"The driver is not in homepage");
		Reporter.log("Redirection to the home page after clicking Log out button verified", true);
		return this;
	}

	public HomePage clickLogOutBtn() {
		waitForVisibilityOfElement(logoutBtn, 10).click();
		return this;
	}

//Test visibility of the "Log out" button in the user dashboard.
	public HomePage logOutBtnValidation() {
		Assert.assertTrue(waitForVisibilityOfElement(logoutBtn, 10).isDisplayed(), "Logout button is not displayed");
		Reporter.log("visibility of the Log out button in the user dashboard verified", true);
		return this;
	}

	public HomePage clickHomeBtn() {
		homeBtn.click();
		return this;
	}
}
