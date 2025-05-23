package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import base.ProjectSpecificationMethods;

public class OrderConfirmationPage extends ProjectSpecificationMethods {

	@FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase!')]")
	WebElement thankYouTxt;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement okBtn;

	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Verify successful payment processing with valid payment details.
	public OrderConfirmationPage paymentCompleteValidation(String expectedValue) {
		String expected = expectedValue;
		String actual = waitForVisibilityOfElement(thankYouTxt, 10).getText();
		Assert.assertEquals(actual, expected);
		Reporter.log("Successful payment processing with valid payment details verified",true);
		return this;
	}
	
	
	public HomePage clickOk() {
		waitForVisibilityOfElement(okBtn, 10).click();
		return new HomePage(driver);
	}

}
