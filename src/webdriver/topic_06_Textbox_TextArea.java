package webdriver;



import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName = "Quynh";
	String lastName = "Pham";
	String emailValue = getEmail();
	String password = "123456";
	
	@BeforeClass
	public void BeforeClass() {
		if (osName.contains("Window")) {
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
	public void TC_01_VerifyLoginInfo() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(emailValue);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains(firstName + " " + lastName));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains(emailValue));
		
//		System.out.println(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText());
	}
	
		
	

	@Test
	public void TC_02_VerifyEmployeeInfo() {
		
	}
	
	@Test
	public void TC_03_VerifyCustomerInfo() {
		
	}
	
	public void sleepInSecond(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getEmail() {
		Random rnd = new Random();
		String emailValue = "quynhpham@" + rnd.nextInt(9999) +"yopmail.com";
		return emailValue;
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
