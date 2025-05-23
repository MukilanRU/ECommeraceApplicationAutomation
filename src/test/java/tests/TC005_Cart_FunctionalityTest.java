package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC005_Cart_FunctionalityTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetName = "cartData";
		testName = "Cart Function Test";
		testDescription = "To Verify the Cart Functionality";
		testCategory = "Smoke Test";
		testAuthor = "Tester";
	}

	@Test(dataProvider = "readData")
	public void cartFunctionTest(String userName, String passWord) {
		HomePage obj = new HomePage(driver);
		obj.clickLoginMenuBtn().enterUserName(userName).enterPassWord(passWord).clickLoginBtn().clickCartBtn()
				.priceCalculationForItemBeforeDeletion().clickDeleteBtn().priceCalculationForItemAfterDeletion()
				.deleteItemPriceValidation().cartElementsAfterDeleting();

	}
}
