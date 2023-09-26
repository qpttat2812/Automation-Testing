package testng;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(testng.listener.ListenerReport.class)
public class Demo_Listener {
	public static WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass 
	public void BeforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register");
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
		driver.findElement(By.cssSelector("button[title='Register_demo']")).click();
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
