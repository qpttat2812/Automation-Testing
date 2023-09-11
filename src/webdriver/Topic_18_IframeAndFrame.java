package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_IframeAndFrame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void BeforeClass() {
		if(osName.contains("Window")) {
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
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(5);
		
		scrollToElement(driver.findElement(By.cssSelector("div.pre-footer-logo>div:nth-child(9)")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.face-content>iframe")).isDisplayed());
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K followers");
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.border_overlay")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("quynh");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987678956");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		driver.findElement(By.name("message")).sendKeys("java\npython");
		sleepInSecond(3);
		
		driver.switchTo().defaultContent();
		scrollToElement(driver.findElement(By.cssSelector("nav#navDesktop")));
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.content>h4")).getText().contains("Excel"));
	}
	
	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("quynh123");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(25);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("netportal.hdfcbank.com/nb-login"));
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("123456");
		sleepInSecond(5);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
}
