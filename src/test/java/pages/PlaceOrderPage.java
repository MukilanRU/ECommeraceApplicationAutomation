package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import base.ProjectSpecificationMethods;

public class PlaceOrderPage extends ProjectSpecificationMethods {

	@FindBy(id = "orderModalLabel")
	WebElement placeOrderTxt;

	@FindBy(id = "name")
	WebElement name;

	@FindBy(id = "country")
	WebElement country;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "card")
	WebElement ccCard;

	@FindBy(id = "month")
	WebElement month;

	@FindBy(id = "year")
	WebElement year;

	@FindBy(xpath = "//button[contains(text(),'Purchase')]")
	WebElement purchaseBtn;

	public PlaceOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//Test redirection to the place order window from the cart page.
	public PlaceOrderPage placOrderPageValidation(String expected) {
		String actual = waitForVisibilityOfElement(placeOrderTxt, 10).getText();
		Assert.assertEquals(actual, expected);
		Reporter.log("Redirection to the place order window from the cart page is verified", true);
		return this;
	}

	public PlaceOrderPage enterName(String nameValue) {
		waitForVisibilityOfElement(name, 10).sendKeys(nameValue);
		return this;
	}

	public PlaceOrderPage enterCountry(String countryValue) {
		waitForVisibilityOfElement(country, 10).sendKeys(countryValue);
		return this;
	}

	public PlaceOrderPage enterCity(String cityValue) {
		waitForVisibilityOfElement(city, 10).sendKeys(cityValue);
		return this;
	}

	public PlaceOrderPage enterCreditCardNumber(String ccValue) {
		waitForVisibilityOfElement(ccCard, 10).sendKeys(ccValue);
		return this;
	}

	public PlaceOrderPage enterMonth(String monthValue) {
		waitForVisibilityOfElement(month, 10).sendKeys(monthValue);
		return this;
	}

	public PlaceOrderPage enterYear(String yearValue) {
		waitForVisibilityOfElement(year, 10).sendKeys(yearValue);
		return this;
	}

//Test the process of completing an order by clicking the purchase button.
	public OrderConfirmationPage clickPurchaseBtn() {
		waitForVisibilityOfElement(purchaseBtn, 10).click();
		return new OrderConfirmationPage(driver);
	}

//Verify the presence of all user details sections (name, country, city).
	public PlaceOrderPage userDetailsValidation() {
		Assert.assertTrue(waitForVisibilityOfElement(name, 10).isDisplayed(), "Name not Displayed");
		Assert.assertTrue(waitForVisibilityOfElement(country, 10).isDisplayed(), "Country not Displayed");
		Assert.assertTrue(waitForVisibilityOfElement(city, 10).isDisplayed(), "City not Displayed");
		Assert.assertTrue(waitForVisibilityOfElement(ccCard, 10).isDisplayed(), "Credit Card Numbber not Displayed");
		Assert.assertTrue(waitForVisibilityOfElement(month, 10).isDisplayed(), "Month not Displayed");
		Assert.assertTrue(waitForVisibilityOfElement(year, 10).isDisplayed(), "Year not Displayed");
		Reporter.log("Presence of all user details sections are verified", true);
		return this;
	}

}
