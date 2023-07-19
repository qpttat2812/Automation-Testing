package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Part_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass 
	public void BeforeClass() {
		if(osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckdriver");
		}
	
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_ID() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.id("FirstName")).sendKeys("Quynh");
	}
	
	@Test
	public void TC_02_Class() {
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.className("email")).sendKeys("tes123@yopmail.com");
	}
	
	@Test
	public void TC_03_CSS() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.cssSelector("input#email")).sendKeys("abctesting");
		driver.findElement(By.cssSelector("input#email")).clear();
		driver.findElement(By.cssSelector("input[name='login[username]']")).sendKeys("test123");
		driver.findElement(By.cssSelector("input[name='login[username]']")).clear();
		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("test234");
	}

	@Test
	public void TC_03_Name() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.name("login[password]")).sendKeys("12345");
	}
	
	@Test
	public void TC_04_PartialName() {
		driver.findElement(By.partialLinkText("Your Password")).click();
	}
	

	@Test
	public void TC_05_LinkText() {
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.linkText("Sitemap")).click();
	}
	
	@Test
	public void TC_06_TagName() {
		System.out.println("Size is" + driver.findElement(By.tagName("input")).getSize());
		
	}
	
	@Test
	public void TC_07_Xpath() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		driver.findElement(By.xpath("//a[text()='About Us']")).click();
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Quynh");
		driver.findElement(By.xpath("//input[@name='middlename']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@title='Last Name']")).sendKeys("Testing");
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.xpath("//input[@class='email']")).sendKeys("test435@gmail.com");
	}
	
	@Test
	public void TC_08_ParentNode() {
		driver.get("https://www.paypal.com/vn/home");
		driver.findElement(By.xpath("//div[@class='row steps__cta-container']//a[text()='Sign Up Now']")).click();
	}
	
	@Test
	public void TC_09_Relative_Xpath() {
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.xpath("//a[contains(text(), 'Apply')]")).click();
		driver.get("https://demo.nopcommerce.com/login");
		driver.findElement(By.xpath("//a[starts-with(text(), 'Compare')]")).click();
		driver.get("https://automationfc.github.io/basic-form/");
		//contain with absolute text()=
		System.out.println("Text of text() = " + driver.findElement(By.xpath("//h5[text()='Michael Jackson']")).getText());
		//contain with relative contains text()
		System.out.println("Text of contains text() " + driver.findElement(By.xpath("//h5[contains(text(), 'Hello World')]")).getText());
		//contain with relative contains . and string()
		System.out.println("Text of contains . or string() " + driver.findElement(By.xpath("//h5[contains(., 'Ignore Me')]")).getText());
		//text()= with concat()
		System.out.println("Text()= with concat() " + driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What', \"'s happened?\")]")).getText());
	}
	
	@Test
	public void TC_10_And_Or() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email' or @title='Email Address' or @name='password']")).sendKeys("test@gmail.com");
		driver.findElement(By.xpath("//input[@id='email' and @title='Email Address' and @name='password']")).sendKeys("test111@gmail.com");
		driver.findElement(By.xpath("//input[@id='email' and @title='Email Address' and @name='login[username]']")).sendKeys("test222@gmail.com");
	}
	
	@AfterClass 
	public void AfterClass() {
		driver.quit();
	}
}
