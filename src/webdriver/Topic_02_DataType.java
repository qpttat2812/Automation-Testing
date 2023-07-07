package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_DataType {
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");
		String osName = System.getProperty("os.name");
	
		@BeforeClass 
		public void beforeClass() {
			if (osName.contains("Windows")) {
				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			} else {
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			}
			
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS );
			driver.manage().window().maximize();
			driver.get("https://demo.nopcommerce.com/register");
			
		}
		
		@Test
		public void TC_01_ID() {
			driver.findElement(By.id("FirstName")).sendKeys("Quynh");
		}
		
		@Test
		public void TC_02_Class() {
			driver.get("https://demo.nopcommerce.com/search");
			driver.findElement(By.className("search-text")).sendKeys("iPhone");
		}
		
		@Test
		public void TC_03_Name() {
			driver.get("https://demo.nopcommerce.com/search");
			driver.findElement(By.name("advs")).click();
		}
		
		@Test
		public void TC_04_TagName() {
			driver.get("https://demo.nopcommerce.com/search");
			System.out.println("There are " + driver.findElements(By.tagName("input")).size() + " input tags in website");   
		}
		
		@Test
		public void TC_05_LinkText() {
			driver.findElement(By.linkText("Contact us")).click();
			
		}
		
		@Test
		public void TC_06_PartialLinkText() {
			driver.findElement(By.partialLinkText("Compare")).click();
		}
				
		@AfterClass
		public void afterClass() {
			driver.close();
		}
}
