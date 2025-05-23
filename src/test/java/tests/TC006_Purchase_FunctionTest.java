package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC006_Purchase_FunctionTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetName = "purchaseData";
		testName = "Purchase Function Test";
		testDescription = "To Verify the Purchase Functionality";
		testCategory = "Smoke Test";
		testAuthor = "Tester";
	}

	@Test(dataProvider = "readData")
	public void purchaseFunctionTest(String userName, String passWord, String expectedData1, String name,
			String country, String city, String ccNum, String month, String year, String expectedData2) {
		HomePage obj = new HomePage(driver);
		obj.clickLoginMenuBtn().enterUserName(userName).enterPassWord(passWord).clickLoginBtn().clickCartBtn()
				.clickPlaceOrderBtn().placOrderPageValidation(expectedData1).userDetailsValidation().enterName(name)
				.enterCountry(country).enterCity(city).enterCreditCardNumber(ccNum).enterMonth(month).enterYear(year)
				.clickPurchaseBtn().paymentCompleteValidation(expectedData2);
	}

}
