package webdriver;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_ExplicitWait_Part3 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	//images path
	String imagePath_1 = projectPath + "\\uploadFiles\\1.jpg";
	String imagePath_2 = projectPath + "\\uploadFiles\\2.jpg";
	
	//images name
	String image_1 = "1.jpg";
	String image_2 = "2.jpg";
	
	@BeforeClass
	public void BeforeClass() {
		if(osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_DateTimePicker() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait = new WebDriverWait(driver, 15);
		
		//wait for calendar visibility
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_RadCalendar1")));
		
		//wait for date that being selected is clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='rcMainTable']//a[text()='21']")));
		
		//verify date is not selected
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
//		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display.")));
		
		//click on date
		driver.findElement(By.xpath("//table[@class='rcMainTable']//a[text()='21']")).click();
		
		//wait for loading icon is invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='ContentPlaceholder1_RadCalendar1']>div.raDiv")));
		
		//wait for selected date is clickable
		//tc failed when minimize window and cookie overlaps datetimepicker
//		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='rcSelected']/a[text()='21']")));
		
		//verify date is selected
		//because the text is reloaded so cannot use old locator
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Thursday, September 21, 2023");
	}
	
	@Test
	public void TC_02_UploadFiles() {
		driver.get("https://gofile.io/welcome");
		
		explicitWait = new WebDriverWait(driver, 45);
		
		//wait for loading icon invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));
		
		//wait for upload files button visible and click 
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-secondary"))).click();
		
		//wait for loading icon invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));
		
		//wait for add files button visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button[class*='filesUploadButton']")));
		
		//upload images
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imagePath_1 + "\n" + imagePath_2);
		
		//wait for loading icon invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.mainUploadInitInfo div.spinner-border")));
		
		System.out.println("Start time 1: " + showTime());
		//wait for uploading progress bars are invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.progress-bar")));
		System.out.println("End time 1: " + showTime());
		
		System.out.println("Start time 2: " + showTime());
		//wait for message is visible and verify
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccess')]//div[contains(text(),'Your files have been successfully uploaded')]"))).isDisplayed());
		//another check verify message
//		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.xpath("//div[contains(@class,'mainUploadSuccess')]//div"), "Your files have been successfully uploaded")));
		System.out.println("End time 2: " + showTime());
		
		//click on download link
		driver.findElement(By.cssSelector("div.mainUploadSuccessLink a")).click();
		
		//wait for loading icon invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#filesLoading div.spinner-border")));
		
		//wait for download button visible and verify
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + image_1 + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + image_2 + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']"))).isDisplayed());
		
		//wait for play button visible and verify
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + image_1 + "']/parent::a/parent::div/following-sibling::div//button//span[text()='Play']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + image_2 +"']/parent::a/parent::div/following-sibling::div//button//span[text()='Play']"))).isDisplayed());
	}
	
	public String showTime() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
