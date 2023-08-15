package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Part3_Topic04_WebBrowser_1 {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	By myAccountXpath = By.xpath("//div[@class='footer']//a[text()='My Account']");
	By createAnAccountBtn = By.xpath("//span[text()='Create an Account']");
	
	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_VerifyURL() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountXpath).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(createAnAccountBtn).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_02_VerifyTitle() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountXpath).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(createAnAccountBtn).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigate() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountXpath).click();
		driver.findElement(createAnAccountBtn).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.navigate().back();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.navigate().forward();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_04_GetPageSource() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(myAccountXpath).click();
		
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(createAnAccountBtn).click();
		
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}	
		
	@AfterClass 
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInSeconds){
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
