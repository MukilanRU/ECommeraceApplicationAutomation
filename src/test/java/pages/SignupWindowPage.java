package pages;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import base.ProjectSpecificationMethods;

public class SignupWindowPage extends ProjectSpecificationMethods {

	@FindBy(id = "sign-username")
	public static WebElement userName;

	@FindBy(id = "sign-password")
	public WebElement passWord;

	@FindBy(xpath = "//button[contains(text(),'Sign up')]")
	public WebElement signupBtn;

	@FindBy(id = "signInModalLabel")
	public WebElement signUpText;

	public SignupWindowPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SignupWindowPage enterUserName(String userNameValue) {
		waitForVisibilityOfElement(userName, 10).sendKeys(userNameValue);
		return this;
	}

	public SignupWindowPage enterpassWord(String passWordValue) {
		waitForVisibilityOfElement(passWord, 10).sendKeys(passWordValue);
		return this;
	}

	public void clickSignupBtn() {
		waitForClickabilityOfElement(signupBtn, 10).click();
		waitforSeconds(1);
		waitForPresenceOfAlert(10);
		Alert alert = driver.switchTo().alert();
		waitforSeconds(1);
		alert.accept();
	}

// Ensure opening the sign up window after clicking "Sign up".
	public SignupWindowPage signupPageValidation(String expectedValue) {
		String expected = expectedValue;
		String actual = waitForVisibilityOfElement(signUpText, 10).getText();
		Assert.assertEquals(actual, expected);
		Reporter.log("opening the sign up window after clicking Sign up is Successful", true);
		return this;
	}
}
