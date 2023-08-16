package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Part3_Topic_05_WebElement_2 {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By myAccountLocator = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By emailLocator = By.cssSelector("#email");
	By passwordLocator = By.cssSelector("#pass");
	By loginButtonLocator = By.cssSelector("#send2");
	String loginPageURL;
	
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_01_InputEmptyEmailPassword(){
		driver.get("http://live.techpanda.org/");
		
		if(driver.findElement(myAccountLocator).isDisplayed()) {
			driver.findElement(myAccountLocator).click();
		}
		else {
			System.out.println("My Account is not found!");
		}
		
		sleepInSecond(1);
		
		loginPageURL = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(loginButtonLocator).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
	
	@Test
	public void TC_02_InvalidEmail(){
		driver.get("http://live.techpanda.org/");
		
		if(driver.findElement(myAccountLocator).isDisplayed()) {
			driver.findElement(myAccountLocator).click();
		}
		else {
			System.out.println("My Account is not found!");
		}
		
		sleepInSecond(1);
		
		loginPageURL = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(emailLocator).sendKeys("12345@12345");
		driver.findElement(passwordLocator).sendKeys("123456");
		driver.findElement(loginButtonLocator).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_PasswordLessThan6Characters(){
		driver.get("http://live.techpanda.org/");
		
		if(driver.findElement(myAccountLocator).isDisplayed()) {
			driver.findElement(myAccountLocator).click();
		}
		else {
			System.out.println("My Account is not found!");
		}
		
		sleepInSecond(1);
		
		loginPageURL = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(emailLocator).sendKeys("automationtest@gmail.com");
		driver.findElement(passwordLocator).sendKeys("123");
		driver.findElement(loginButtonLocator).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void TC_04_IncorrectEmailOrPassword(){
		driver.get("http://live.techpanda.org/");
		
		if(driver.findElement(myAccountLocator).isDisplayed()) {
			driver.findElement(myAccountLocator).click();
		}
		else {
			System.out.println("My Account is not found!");
		}
		
		sleepInSecond(1);
		
		loginPageURL = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageURL, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(emailLocator).sendKeys("automationtest@gmail.com");
		driver.findElement(passwordLocator).sendKeys("123123123");
		driver.findElement(loginButtonLocator).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).isDisplayed());
		
	}
	
	public void sleepInSecond(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
