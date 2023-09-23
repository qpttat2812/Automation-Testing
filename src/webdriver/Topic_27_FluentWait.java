package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_FluentWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	FluentWait<WebDriver> fluentWait;
	
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
	public void TC_01_FluentWait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		fluentWait = new FluentWait<WebDriver>(driver);
		
		fluentWait.withTimeout(Duration.ofSeconds(12))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		//solution 1
//		String textResult = fluentWait.until(new Function<WebDriver, String>() {
//
//			@Override
//			public String apply(WebDriver t) {
//				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).getText();
//			}
//			
//		});
//	
//		Assert.assertEquals(textResult, "01:01:00");
		
		//solution 2
		
		boolean textResult = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed();
			}
			
		});
		
		Assert.assertTrue(textResult);
		
	}
	
	@Test
	public void TC_02_FluentWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		fluentWait = new FluentWait<WebDriver>(driver);
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		fluentWait.withTimeout(Duration.ofSeconds(5))
					.pollingEvery(Duration.ofSeconds(1))
					.ignoring(NoSuchElementException.class);
		
		boolean textResult = fluentWait.until(new Function<WebDriver,Boolean>(){

			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
			}
			
		});
		
		Assert.assertTrue(textResult);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
