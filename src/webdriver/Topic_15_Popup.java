package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void BeforeClass() {
		if(osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath +  "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath +  "/browserDrivers/geckodriver.exe");
		}
		
//		//turn off notification in firefox
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		options.addPreference("app.update.enabled", false);
		options.addPreference("geo.enabled", false);
		driver = new FirefoxDriver(options);
		
//		Map prefs = new HashMap();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		driver = new ChromeDriver(options);
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_1_PopupInDOM() {
		driver.get("https://ngoaingu24h.vn/");
		
		driver.findElement(By.cssSelector("button.login_")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("(//div[@id='modal-login-v1'])[1]/div")).isDisplayed());
		
		driver.findElement(By.xpath("(//input[(@name='account-input')])[1]")).sendKeys("automationfc");
		driver.findElement(By.xpath("(//input[@id='password-input'])[1]")).sendKeys("automationfc");
		driver.findElement(By.xpath("(//button[text()='Đăng nhập'])[2]")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.xpath("(//button[@class='close'])[2]")).click();
		
		Assert.assertFalse(driver.findElement(By.xpath("(//div[@id='modal-login-v1'])[1]/div")).isDisplayed());
		
	}
	
	@Test
	public void TC_2_PopupInDOM() {
		driver.get("https://skills.kynaenglish.vn");
		
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		
		Assert.assertFalse(driver.findElement(By.cssSelector("div#k-popup-account-login-mb div.modal-content")).isDisplayed());

	}
	
	@Test
	public void TC_3_PopupNotInDOM() {
		driver.get("https://tiki.vn/");
		
		sleepInSecond(3);
		driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("(//div[@role='dialog']/div)[2]")).isDisplayed());
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).getText(), "Mật khẩu không được để trống");
		
		driver.findElement(By.cssSelector("button.btn-close")).click();
		
		Assert.assertEquals(driver.findElements(By.xpath("(//div[@role='dialog']/div)[2]")).size(), 0);
	}
	
	@Test
	public void TC_4_PopupNotInDOM() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[text()='Create new account']")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).isDisplayed());
		
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Quynh");
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Pham");
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("quynh123@gmail.com");
		driver.findElement(By.cssSelector("input[name='reg_passwd__']")).sendKeys("123456");
		new Select(driver.findElement(By.cssSelector("select#day"))).selectByVisibleText("14");
		new Select(driver.findElement(By.cssSelector("select#month"))).selectByVisibleText("Oct");
		new Select(driver.findElement(By.cssSelector("select#year"))).selectByVisibleText("2020");
		driver.findElement(By.xpath("//label[text()='Female']/following-sibling::input")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div/img")).click();
		
		Assert.assertEquals(driver.findElements(By.xpath("//div[text()='Sign Up']/parent::div/parent::div")).size(), 0);
		
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
