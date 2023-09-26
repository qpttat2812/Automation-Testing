package testng.listener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import testng.Demo_Listener;

public class ListenerReport implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		TakesScreenshot screenshot = (TakesScreenshot) Demo_Listener.driver;

		// Capture the screenshot as a file object
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

		// Save the screenshot to a desired location
		try {
			File destFile = new File("./screenshot/screenshot" + result.getName() + ".png");

			FileUtils.copyFile(srcFile, destFile);
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src ='" + destFile.getAbsolutePath() + "' height='80' width='80'/> </a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
