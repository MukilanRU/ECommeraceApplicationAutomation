package utils;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import base.ProjectSpecificationMethods;

public class ListenerClass extends ProjectSpecificationMethods implements ITestListener {

	@Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName()); // this initializes `test`
    }
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		String screenshot = "";
		try {
			screenshot = screenShot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
			test.addScreenCaptureFromPath(screenshot, "Failure ScreenShot");
		}
	}

}
