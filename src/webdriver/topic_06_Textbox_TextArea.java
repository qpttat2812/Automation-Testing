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
	String newUserName = "quynhpham" + String.valueOf(randomNumber());
	String newPassword = "quynh123";
	String passportNumber = "1234-5678-9090";
	By firstNameLocator = By.name("firstName");
	By lastNameLocator = By.name("lastName");
	By employeeIdLocator = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
	
	
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
		sleepInSecond(5);
		
		//input employee first name, lastname, choose create login details
		driver.findElement(firstNameLocator).sendKeys(firstName);
		driver.findElement(lastNameLocator).sendKeys(lastName);
		
		employeeId = driver.findElement(employeeIdLocator).getAttribute("value");
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		
		//input create user detail
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(newUserName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(newPassword);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(newPassword);
		driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();
		sleepInSecond(10);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("pim/viewPersonalDetails/empNumber/"));
		
		//verify new info of user
		Assert.assertTrue(driver.findElement(firstNameLocator).getAttribute("value").contains(firstName));
		Assert.assertTrue(driver.findElement(lastNameLocator).getAttribute("value").contains(lastName));
		Assert.assertTrue(driver.findElement(employeeIdLocator).getAttribute("value").contains(employeeId));
		
		//click Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("pim/viewImmigration/empNumber"));
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div//button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportNumber);
		
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys("new passport\nhas inputed");
		
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
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
	
	public int randomNumber() {
		Random rnd = new Random();
		return rnd.nextInt(9999);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
