package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethods;

public class LoginPage extends ProjectSpecificationMethods {

	@FindBy(id = "loginusername")
	WebElement userName;

	@FindBy(id = "loginpassword")
	WebElement passWord;

	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LoginPage enterUserName(String userNameValue) {
		waitForVisibilityOfElement(userName, 10).sendKeys(userNameValue);
		return this;
	}

	public LoginPage enterPassWord(String passWordValue) {
		waitForVisibilityOfElement(passWord, 10).sendKeys(passWordValue);
		return this;
	}

	public HomePage clickLoginBtn() {
		waitForVisibilityOfElement(loginBtn, 10).click();
		return new HomePage(driver);
	}

}
