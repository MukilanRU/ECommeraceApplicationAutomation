package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethods;
import pages.HomePage;

public class TC004_ProductDetails_and_AddingToCartTest extends ProjectSpecificationMethods {

	@BeforeTest
	public void setup() {
		sheetName = "productData";
		testName = "ProductDetail and Add To CartTest";
		testDescription = "To Verify the Product Details and Adding to Cart Functionality";
		testCategory = "Smoke Test";
		testAuthor = "Tester";
	}

	@Test(dataProvider = "readData")
	public void productAndCartTest(String userName, String passWord, String expectedProdName, String expectedProdPrice,
			String expectedProdDescription) {
		HomePage obj = new HomePage(driver);
		obj.clickLoginMenuBtn().enterUserName(userName).enterPassWord(passWord).clickLoginBtn().clickCartBtn()
				.getInitialCartValue().clickHomeBtn().clickAProduct().productDetailPageValidation()
				.displayInformationAccuracy(expectedProdName, expectedProdPrice, expectedProdDescription).clickAddCart()
				.clickCartBtn().getFinalCartValue().cartValidation().clickPlaceOrderBtn();

	}
}
