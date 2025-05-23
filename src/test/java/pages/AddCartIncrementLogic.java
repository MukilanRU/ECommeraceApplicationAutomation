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

import utils.ConfigReader;

public class AddCartIncrementLogic {
	public static WebDriver driver;

	//This Class is used for logic writting not part of the framework
	
	public static void main(String[] args) throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.demoblaze.com/");//launch url
		WebElement hLoginBtn = driver.findElement(By.id("login2"));
		waitMethod(hLoginBtn, 10).click();
		WebElement userName = driver.findElement(By.id("loginusername"));
		waitMethod(userName, 10).sendKeys("test@123");
		WebElement passWord = driver.findElement(By.id("loginpassword"));
		waitMethod(passWord, 10).sendKeys("test@123");
		WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
		waitMethod(loginBtn, 10).click();//login
		Thread.sleep(3000);
		WebElement cartBtn = driver.findElement(By.id("cartur"));
		waitMethod(cartBtn, 30).click();//click cart
		//Thread.sleep(5000);
		List<WebElement> cartElements1 = driver.findElements(By.xpath("//table//tbody//tr"));
		int addCartBefore = cartElements1.size();//find cart size
		ConfigReader.set("initalCartValue", String.valueOf(addCartBefore));//store in config file
		//Thread.sleep(5000);
		WebElement homePage = driver.findElement(By.xpath("//a[text()='Home ']"));
		homePage.click();//click homepage
		WebElement product = driver.findElement(By.xpath("//a[contains(text(),'Samsung galaxy s6')]"));
		waitMethod(product, 10).click();//click product
		//Thread.sleep(3000);
		WebElement addCartBtn = driver.findElement(By.xpath("//a[contains(text(),'Add to cart')]"));
		waitMethod(addCartBtn, 10).click();//click add cart
		waitForAlert();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(5000);
		WebElement cartBtn1 = driver.findElement(By.id("cartur"));
		waitMethod(cartBtn1, 10).click();//click cartbutton
		List<WebElement> cartElements2 = driver.findElements(By.xpath("//table//tbody//tr"));
		int finalCartValue = cartElements2.size();//find size
		int intialcartValue = Integer.parseInt(ConfigReader.get("initalCartValue"));// fetch inital size from config
		System.out.println("inital cart value "+intialcartValue +" Final cart value "+finalCartValue);
		Assert.assertTrue(finalCartValue > intialcartValue); //assertion
		driver.quit();
	}

	public static WebElement waitMethod(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static Alert waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public static List<WebElement> waitMethodMulitpleElements( By locator,int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((locator)));
	}

}
