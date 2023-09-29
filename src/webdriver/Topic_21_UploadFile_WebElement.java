package webdriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_UploadFile_WebElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	// files
	String imagePath_1, imagePath_2, imagePath_3;
	String imageName_1 = "1.jpg";
	String imageName_2 = "2.jpg";
	String imageName_3 = "3.jpg";

	@BeforeClass
	public void BeforeClass() {
		if (osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		if (osName.contains("Window")) {
			imagePath_1 = projectPath + "\\uploadFiles\\1.jpg";
			imagePath_2 = projectPath + "\\uploadFiles\\2.jpg";
			imagePath_3 = projectPath + "\\uploadFiles\\3.jpg";
		} else {
			imagePath_1 = projectPath + "/uploadFiles/1.jpg";
			imagePath_2 = projectPath + "/uploadFiles/2.jpg";
			imagePath_3 = projectPath + "/uploadFiles/3.jpg";
		}
	}

	public void TC_01_UploadSingleFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// load each file
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imagePath_1);
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imagePath_2);
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imagePath_3);
		sleepInSecond(3);

		// verify load file
		Assert.assertEquals(driver.findElement(By.cssSelector("tbody.files tr:nth-child(1) p.name")).getText(), imageName_1);
		Assert.assertEquals(driver.findElement(By.cssSelector("tbody.files tr:nth-child(2) p.name")).getText(), imageName_2);
		Assert.assertEquals(driver.findElement(By.cssSelector("tbody.files tr:nth-child(3) p.name")).getText(), imageName_3);

		// upload files
		List<WebElement> startButtonList = driver.findElements(By.cssSelector("tbody.files button.btn-primary.start"));

		for (WebElement startButton : startButtonList) {
			startButton.click();
			sleepInSecond(3);
		}

		// verify uploaded images successfully
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_1 + "']"))));
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_2 + "']"))));
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_3 + "']"))));

		// verify image name files correctly
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_1 + "']")).getText(), imageName_1);
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_2 + "']")).getText(), imageName_2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_3 + "']")).getText(), imageName_3);
	}

	public void TC_02_UploadMultipleFiles() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// load multiple files
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imagePath_1 + "\n" + imagePath_2 + "\n" + imagePath_3);
		sleepInSecond(3);

		// verify load file
		Assert.assertEquals(driver.findElement(By.cssSelector("tbody.files tr:nth-child(1) p.name")).getText(), imageName_1);
		Assert.assertEquals(driver.findElement(By.cssSelector("tbody.files tr:nth-child(2) p.name")).getText(), imageName_2);
		Assert.assertEquals(driver.findElement(By.cssSelector("tbody.files tr:nth-child(3) p.name")).getText(), imageName_3);

		// upload files button
		List<WebElement> startButtonList = driver.findElements(By.cssSelector("tbody.files button.btn-primary.start"));

		for (WebElement startButton : startButtonList) {
			startButton.click();
			sleepInSecond(3);
		}

		// verify uploaded images successfully
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_1 + "']"))));
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_2 + "']"))));
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_3 + "']"))));

		// verify image name files correctly
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_1 + "']")).getText(), imageName_1);
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_2 + "']")).getText(), imageName_2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_3 + "']")).getText(), imageName_3);
	}

	@Test
	public void TC_03_UploadMultipleFiles() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		List<String> filesPath = new ArrayList<String>();

		filesPath.add(imagePath_1);
		filesPath.add(imagePath_2);
		filesPath.add(imagePath_3);

		for (String file : filesPath) {
			System.out.println(file);
		}

		uploadMultipleFiles("//input[@type='file']", filesPath);
		sleepInSecond(5);

		// upload files button
		List<WebElement> startButtonList = driver.findElements(By.cssSelector("tbody.files button.btn-primary.start"));

		for (WebElement startButton : startButtonList) {
			startButton.click();
			sleepInSecond(3);
		}

		// verify uploaded images successfully
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_1 + "']"))));
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_2 + "']"))));
		Assert.assertTrue(checkUploadedImage(driver.findElement(By.cssSelector("span.preview img[src*='" + imageName_3 + "']"))));

		// verify image name files correctly
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_1 + "']")).getText(), imageName_1);
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_2 + "']")).getText(), imageName_2);
		Assert.assertEquals(driver.findElement(By.cssSelector("p.name>a[title='" + imageName_3 + "']")).getText(), imageName_3);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement getElement(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public void uploadMultipleFiles(String xpathLocator, List<String> filesPath) {

		for (String filePath : filesPath) {
			getElement(xpathLocator).sendKeys(filePath);
		}

	}

	public boolean checkUploadedImage(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
