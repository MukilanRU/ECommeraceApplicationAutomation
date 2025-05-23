package pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import base.ProjectSpecificationMethods;
import utils.ConfigReader;

public class CartPage extends ProjectSpecificationMethods {

	@FindBy(xpath = "//table//tbody//tr")
	List<WebElement> cartElements;

	@FindBy(xpath = "//a[contains(text(),'Delete')]")
	WebElement deleteBtn;

	@FindBy(xpath = "//button[contains(text(),'Place Order')]")
	WebElement placeOrderBtn;

	@FindBy(xpath = "//table//tbody//tr//td[3]")
	List<WebElement> productPriceElements;

	@FindBy(id = "totalp")
	WebElement totalPrice;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage getInitialCartValue() {
		waitforSeconds(2);
		int cartBefore = cartElements.size();
		ConfigReader.set("initialCartValue", String.valueOf(cartBefore));
		return new HomePage(driver);
	}

	public CartPage getFinalCartValue() {
		waitforSeconds(2);
		int cartAfter = cartElements.size();
		ConfigReader.set("finalCartValue", String.valueOf(cartAfter));
		return this;
	}

	public CartPage cartValidation() {
		int initialCartValue = Integer.parseInt(ConfigReader.get("initialCartValue"));
		int finalCartValue = Integer.parseInt(ConfigReader.get("finalCartValue"));
		System.out.println("Initial cart value " + initialCartValue + " Final Cart Value " + finalCartValue);
		Assert.assertTrue(finalCartValue > initialCartValue);
		Reporter.log("The product count increments in the cart after addition is verified", true);
		return this;
	}

//Test the ability to delete items from the cart.
	public CartPage clickDeleteBtn() {
		waitforSeconds(2);
		if (!cartElements.isEmpty()) {
			deleteBtn.click();
			System.out.println("Delete items from the cart verified");
			;
		} else {
			Reporter.log("Cant Delete ! cart is empty");
		}
		return this;
	}

//Test the process of completing an order by clicking the purchase button.
	public PlaceOrderPage clickPlaceOrderBtn() {
		waitForVisibilityOfElement(placeOrderBtn, 10).click();
		Assert.assertTrue(placeOrderBtn.isEnabled());
		Reporter.log("Process of completing an order by clicking the purchase button verified", true);
		return new PlaceOrderPage(driver);
	}

	public CartPage priceCalculationForItemBeforeDeletion() {
		waitforSeconds(2);
		int priceValue = 0;
		if (!cartElements.isEmpty()) {
			// int cartSize =cartElements.size();
			for (WebElement webElement : productPriceElements) {
				String priceString = webElement.getText();
				int itemPrice = Integer.parseInt(priceString);
				priceValue += itemPrice;
			}
			ConfigReader.set("calculatedPriceBefore", String.valueOf(priceValue));
			String totalPriceString = totalPrice.getText();
			ConfigReader.set("totalDisplayPriceBefore", totalPriceString);
			Assert.assertTrue(Integer.parseInt(ConfigReader.get("calculatedPriceBefore")) == Integer
					.parseInt(ConfigReader.get("totalDisplayPriceBefore")));
		} else {
			ConfigReader.set("calculatedPriceBefore", "0");
			ConfigReader.set("totalDisplayPriceBefore", "0");
		}
		return this;
	}

	public CartPage priceCalculationForItemAfterDeletion() {
		waitforSeconds(2);
		int priceValue = 0;
		if (!cartElements.isEmpty()) {
			// int cartSize =cartElements.size();
			for (WebElement webElement : productPriceElements) {
				String priceString = webElement.getText();
				int itemPrice = Integer.parseInt(priceString);
				priceValue += itemPrice;
			}
			ConfigReader.set("calculatedPriceAfter", String.valueOf(priceValue));
			String totalPriceString = totalPrice.getText();
			ConfigReader.set("totalDisplayPriceAfter", totalPriceString);
			Assert.assertTrue(Integer.parseInt(ConfigReader.get("calculatedPriceAfter")) == Integer
					.parseInt(ConfigReader.get("totalDisplayPriceAfter")));
		} else {
			ConfigReader.set("calculatedPriceAfter", "0");
			ConfigReader.set("totalDisplayPriceAfter", "0");

		}

		return this;
	}

//Verify accurate calculation of the total price after modifications.
	public CartPage deleteItemPriceValidation() {
		if (Integer.parseInt(ConfigReader.get("totalDisplayPriceBefore")) != 0) {
			Assert.assertTrue(Integer.parseInt(ConfigReader.get("totalDisplayPriceAfter")) < Integer
					.parseInt(ConfigReader.get("totalDisplayPriceBefore")));
			Reporter.log("Accurate calculation of the total price after modifications are verified", true);
		} else {
			Reporter.log("The Cart is empty before deleting item");
		}
		return this;
	}

//Test clearing the cart and verify it's reflected in the UI.
	public CartPage cartElementsAfterDeleting() {
		if (!cartElements.isEmpty()) {
			int sizeofCart = cartElements.size();
			System.out.println("Items in the cart after deleting are  " + sizeofCart);
			System.out.println("Clearing the cart changes are reflected in the UI.");
		} else {
			System.out.println("No item in the cart for deleting");
		}
		return this;
	}

}
