package base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.UtilityClass;

public class ProjectSpecificationMethods extends UtilityClass {

	@BeforeSuite
	public void reportinitialization() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				"C:\\Users\\mukil\\eclipse-workspace\\ECommerceApp\\Resources\\Reports\\ecommeraceAppReport.html");
		reporter.config().setReportName("EcommeranceAppReport");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	public void testDetails() {
		test = extent.createTest(testName, testDescription);
		test.assignCategory(testCategory);
		test.assignAuthor(testAuthor);
	}

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void launchBrowserAndLoadingUrl(String browser, String url) {
		launchBrowser(browser, url);
	}

	@AfterMethod
	public void browseClose() {
		waitforSeconds(2);
		closeBrowser();
	}

	@DataProvider
	public String[][] readData() throws IOException {
		String data[][] = excelRead(sheetName);
		return data;
	}

	@AfterSuite
	public void closeReport() {
		extent.flush();
	}
}
