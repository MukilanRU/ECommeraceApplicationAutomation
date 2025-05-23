package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC002_LoginFunctionTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetName = "loginData";
		testName = "Login Test";
		testDescription = "To Verify the login Functionality";
		testCategory = "Smoke Test";
		testAuthor = "Tester";
	}

	@Test(dataProvider = "readData")
	public void loginTest(String userName, String passWord, String expected) {
		HomePage obj = new HomePage(driver);
		obj.testLoginButtonVisible().testLoginButtonClickable().clickLoginMenuBtn().enterUserName(userName)
				.enterPassWord(passWord).clickLoginBtn().userInfoValidation(expected);
	}

}
