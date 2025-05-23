package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC003_Product_Categories_and_DisplayTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetName = "loginData";
		testName = "Login Test";
		testDescription = "To Verify the Product category and display information";
		testCategory = "Smoke Test";
		testAuthor = "Tester";
	}

	@Test(dataProvider = "readData")
	public void productTest(String userName, String passWord, String expected) {
		HomePage obj = new HomePage(driver);
		obj.clickLoginMenuBtn().enterUserName(userName).enterPassWord(passWord).clickLoginBtn().welcomeUserValidation()
				.allMenuDisplayValidation().allcategoryDisplayValidation().logoVerificaton();
	}
}
