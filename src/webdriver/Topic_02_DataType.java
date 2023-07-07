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
			driver.findElement(By.name("advs")).click();
		}
		
		@Test
		public void TC_04_TagName() {
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
		
		@Test
		public void TC_07_CSS() {
			driver.get("https://demo.nopcommerce.com/register");
			
			//solution 1
			driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Quynh");
			
			//solution 2
			driver.findElement(By.cssSelector("input[name = LastName]")).sendKeys("Pham");
			
			//solution 3
			driver.findElement(By.cssSelector("input[id = Email]")).sendKeys("test123@yopmail.com");
			
		}
		
		@Test
		public void TC_08_Xpath() {
			//solution 1
			driver.findElement(By.xpath("//input[@id = 'FirstName']")).sendKeys("Quynh1");
			
			//solution 2
			driver.findElement(By.xpath("//input[@name = 'LastName']")).sendKeys("Pham1");
			
			//solution 3
			driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("test123@yopmail.com");
		}
		
		@AfterClass
		public void afterClass() {
//			driver.quit();
		}
}
