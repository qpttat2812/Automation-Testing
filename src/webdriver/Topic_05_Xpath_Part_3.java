package webdriver;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Xpath_Part_3 {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void BeforeClass() {
		if(osName.contains("Windows")) {
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
	public void TC_01_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//get css selector for button 1
//		driver.findElement(By.cssSelector("button[class='btn_pink_sm fs16']")).click();
		
		//get css selector for button 2
		driver.findElement(By.cssSelector("button.btn_pink_sm.fs16")).click();
		
		//get xpath for button
//		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		//check error messages
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Vui lòng nhập số điện thoại.");
		
	}
	
	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(By.id("txtFirstname")).sendKeys("Quynh");
		driver.findElement(By.name("txtEmail")).sendKeys("abc@abc1@abc2");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("abc@abc1@abc2");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("abc123");
		driver.findElement(By.name("txtCPassword")).sendKeys("abc123");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0163456787");
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		//check error messages
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Vui lòng nhập email hợp lệ");

	}
	
	@Test
	public void TC_03_Incorrect_Confirm_Email() {
		//clear text
		driver.findElement(By.name("txtEmail")).clear();
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).clear();

		
		//input text
		driver.findElement(By.name("txtEmail")).sendKeys("quynhpham11112@yopmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("quynhpham11112@gmail.com");
//		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		//checking error message
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(), "Email nhập lại không đúng");
		
	}
	
	@Test
	public void TC_04_Password_LessThan_6Characters() {
		//clear text
		driver.findElement(By.cssSelector("input#txtPassword")).clear();
		driver.findElement(By.name("txtCPassword")).clear();
		
		//input text
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("abc");
		driver.findElement(By.name("txtCPassword")).sendKeys("abc");
//		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		//check error messages
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		//clear text
		driver.findElement(By.cssSelector("input#txtPassword")).clear();
		driver.findElement(By.name("txtCPassword")).clear();
		
		//input text
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("abc123");
		driver.findElement(By.name("txtCPassword")).sendKeys("abc1234");
//		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		//check error message
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(), "Mật khẩu bạn nhập không khớp");

	}
	
	@Test
	public void TC_06_Invalid_PhoneNumber() {
		//clear text
		driver.findElement(By.name("txtCPassword")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		
		//input text - invalid phone number
		driver.findElement(By.name("txtCPassword")).sendKeys("abc123");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0223456789");
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		//check error messsage
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	
		//input text - phone number < 11 characters or > 12 characters
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456");
//		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		//check error messsage
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(), "Số điện thoại phải từ 10-11 số.");
			
	}
	
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
