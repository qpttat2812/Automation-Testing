package testng;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Parameters_TestNG {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@Parameters("browser")
	@BeforeClass 
	public void BeforeClass(String browserName) {
		switch(browserName) {
		
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_RegisterAccount() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Quynh");
		driver.findElement(By.xpath("//input[@name='middlename']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@title='Last Name']")).sendKeys("Testing");
		driver.findElement(By.cssSelector("input#email_address")).sendKeys(getEmailAddress());
		driver.findElement(By.cssSelector("input#password")).sendKeys("123123");
		driver.findElement(By.cssSelector("input#confirmation")).sendKeys("123123");
		
	}
	
	public String getEmailAddress() {
		Random rnd = new Random();
		return String.valueOf("quynh" + rnd.nextInt(9999) + "@yopmail.com");
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
