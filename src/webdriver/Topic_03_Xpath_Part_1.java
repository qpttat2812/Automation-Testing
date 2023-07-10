package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if(osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Xpath_1() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
	}
	
	@Test
	public void TC_02_Xpath_2() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers ']")).click();
		
	}
	
	@Test
	public void TC_02_Xpath_3() {
		driver.get("https://demo.nopcommerce.com/desktops");
		driver.findElement(By.xpath("//a[starts-with(text(), 'Lenovo')]")).click();
		driver.get("https://demo.nopcommerce.com/desktops");
		driver.findElement(By.xpath("//a[contains(text(),'Digital Storm')]")).click();
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
