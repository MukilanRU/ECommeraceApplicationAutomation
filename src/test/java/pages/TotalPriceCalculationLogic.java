package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TotalPriceCalculationLogic {
	public static WebDriver driver;
	public  static int priceValue = 0;
	
	
	
	//This Class is used for logic writting not part of the framework

	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.demoblaze.com/");
		WebElement hLoginBtn = driver.findElement(By.id("login2"));
		waitMethod(hLoginBtn, 10).click();
		WebElement userName = driver.findElement(By.id("loginusername"));
		waitMethod(userName, 10).sendKeys("test@123");
		WebElement passWord = driver.findElement(By.id("loginpassword"));
		waitMethod(passWord, 10).sendKeys("test@123");
		WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
		waitMethod(loginBtn, 10).click();
		Thread.sleep(2000);
		WebElement cartBtn = driver.findElement(By.id("cartur"));
		waitMethod(cartBtn, 10).click();
		Thread.sleep(5000);
		List<WebElement> cartElements = driver.findElements(By.xpath("//table//tbody//tr//td[3]"));
		int cartsize = waitMethodMulitpleElements(cartElements, 10).size();
		for (WebElement webElement : cartElements) {
			String priceString = webElement.getText();
		//}
//		for (int i = 1; i <= cartsize; i++) {
//			String priceString = driver.findElement(By.xpath("//table//tbody//tr["+i+"]//td[3]")).getText();
			int itemPrice = Integer.parseInt(priceString);
			priceValue += itemPrice;
		}
		System.out.println(priceValue);
		WebElement TotalPrice = driver.findElement(By.id("totalp"));
		String TotalPriceString = waitMethod(TotalPrice, 10).getText();
		int TotalPriceValue = Integer.parseInt(TotalPriceString);
		System.out.println(TotalPriceValue);
		Assert.assertTrue(priceValue == TotalPriceValue);

	}

	public static WebElement waitMethod(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static Alert waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public static List<WebElement> waitMethodMulitpleElements(List<WebElement> element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

}
