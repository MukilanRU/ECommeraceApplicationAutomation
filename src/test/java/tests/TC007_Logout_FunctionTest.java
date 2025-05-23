package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC007_Logout_FunctionTest extends ProjectSpecificationMethods {
	@BeforeTest
	public void setup() {
		sheetName = "logoutData";
		testName = "LogOut Test";
		testDescription = "To Verify the logout Functionality";
		testCategory = "Smoke Test";
		testAuthor = "Tester";
	}

	@Test(dataProvider = "readData")
	public void logoutTest(String userName, String passWord) {
		HomePage obj = new HomePage(driver);
		obj.clickLoginMenuBtn().enterUserName(userName).enterPassWord(passWord).clickLoginBtn().logOutBtnValidation()
				.clickLogOutBtn().homePageValidation();
	}
}
