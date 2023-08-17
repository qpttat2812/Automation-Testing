package webdriver;



import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
	String employeeId;
	String loginUserName = "Admin";
	String loginPassword = "admin123";
	String newUserName = "quynhpham123";
	String newPassword = "quynh123";
	By firstNameLocator = By.name("firstName");
	By lastNameLocator = By.name("lastName");
	By employeeIdLocator = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div");
	
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
	
	//@Test
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
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//login
		driver.findElement(By.name("username")).sendKeys(loginUserName);
		driver.findElement(By.name("password")).sendKeys(loginPassword);
		driver.findElement(By.cssSelector(".oxd-button")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
		
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
		
		//input employee first name, lastname, choose create login details
		driver.findElement(firstNameLocator).sendKeys(firstName);
		driver.findElement(lastNameLocator).sendKeys(lastName);
		
		employeeId = driver.findElement(employeeIdLocator).getText();
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		
		//input create user detail
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div")).sendKeys(newUserName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div")).sendKeys(newPassword);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div")).sendKeys(newPassword);
		driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();
		sleepInSecond(1);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("pim/viewPersonalDetails/empNumber/"));
		
		//verify new info of user
		Assert.assertTrue(driver.findElement(firstNameLocator).getText().contains(newUserName));
		Assert.assertTrue(driver.findElement(lastNameLocator).getText().contains(newPassword));
		Assert.assertTrue(driver.findElement(employeeIdLocator).getText().contains(employeeId));
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
	
	//@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
