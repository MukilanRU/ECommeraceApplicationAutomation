package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC001_SignUpFunctionTest extends ProjectSpecificationMethods {
	@BeforeTest
	public void setup() {
		sheetName = "signupData";
		testName = "Signup Test";
		testDescription = "To Verify the signup Functionality";
		testCategory = "Smoke Test";
		testAuthor = "Tester";
	}

	@Test(dataProvider = "readData")
	public void SignUpTest(String userName, String passWord, String expected ) {
		HomePage obj = new HomePage(driver);
		obj.testSignUpButtonVisible().testSignUpButtonClickable().testSignUpWindowOpens().signupPageValidation(expected).enterUserName(userName)
				.enterpassWord(passWord).clickSignupBtn();
	}
}
