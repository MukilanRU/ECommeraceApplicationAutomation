package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import base.ProjectSpecificationMethods;

public class ProductDetailsPage extends ProjectSpecificationMethods {

	@FindBy(xpath = "//div[@id='tbodyid']/h2")
	WebElement productName;

	@FindBy(xpath = "//div[@id='tbodyid']/h3")
	WebElement productPrice;

	@FindBy(xpath = "//div[@id='more-information']/p")
	WebElement productDescription;

	@FindBy(xpath = "//a[contains(text(),'Add to cart')]")
	WebElement addCartBtn;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//Test redirection to the product details page upon clicking a product
	public ProductDetailsPage productDetailPageValidation() {
		//String expected = HomePage.productName;
		String expected = "Samsung galaxy s6";
		String actual = productName.getText();
		Assert.assertEquals(actual, expected, "Not In Correct Product Page");
		Reporter.log("Redirection to the product details page upon clicking a product is verified", true);
		return this;
	}

//Verify the displayed information accuracy (price, description, etc.)
	public ProductDetailsPage displayInformationAccuracy(String productNameValue, String productPriceValue,
			String productDescriptionValue) {
		String actualprodName = productName.getText();
		String expectedprodName = productNameValue;
		Assert.assertEquals(actualprodName, expectedprodName, "Product Name Wrong");
		String actualprodPrice = productPrice.getText();
		String expectedprodPrice = productPriceValue;
		Assert.assertEquals(actualprodPrice, expectedprodPrice, "Product Price Wrong");
		String actualprodDescription = productDescription.getText();
		String expectedprodDescription = productDescriptionValue;
		Assert.assertEquals(actualprodDescription, expectedprodDescription, "Product Description Wrong");
		Reporter.log("The displayed information accuracy successfully", true);
		return this;
	}

//Test adding a product to the cart from the product details page.
	public HomePage clickAddCart() {
		waitForVisibilityOfElement(addCartBtn, 10).click();
		waitForPresenceOfAlert(10);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("Item is Added to the cart successfully");
		return new HomePage(driver);
	}
}
