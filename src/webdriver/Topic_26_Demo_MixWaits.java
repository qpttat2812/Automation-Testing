package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Demo_MixWaits {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
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
	}
	
//	@Test 
	public void TC_01_ImplicitWait_Only() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Start time 1 = " + showTime());
		try {
			driver.findElement(By.cssSelector("input#automation"));
		} catch (Exception e) {
			
		}
		
		System.out.println("End time 1 = " + showTime());
	}
	
//	@Test 
	public void TC_02_ExplicitWait_Only() {
		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		System.out.println("Start time 2 = " + showTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#automation")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Start time 2 = " + showTime());
	}
	
//	@Test 
	public void TC_03_MixWaits_Equals() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		System.out.println("Start time 3 = " + showTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#automation")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Start time 3 = " + showTime());
	}
	
//	@Test 
	public void TC_04_MixWaits_LessThan() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		System.out.println("Start time 4 = " + showTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#automation")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Start time 4 = " + showTime());
		
	}
	
//	@Test 
	public void TC_05_MixWaits_GreaterThan() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		System.out.println("Start time 5 = " + showTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#automation")));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Start time 5 = " + showTime());
	}
	
//	@Test 
	public void TC_06_MixWaits_UseWebElement() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		System.out.println("Start time 6 = " + showTime());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Start time 6 = " + showTime());
	}
	
	
	@Test 
	public void TC_07_MixWaits_UseWebElement() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		System.out.println("Start time 7 = " + showTime());
		try {
			explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("input#automation"))));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("Start time 7 = " + showTime());
	}
	
	public String showTime() {
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
