package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_PageLoad {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void BeforeClass() {
		if (osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 30);
	}

//	@Test
	public void TC_01_PageLoad() {
		driver.get("https://admin-demo.nopcommerce.com");

		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Welcome, please sign in!']")).isDisplayed());
	}
	
	@Test
	public void TC_02_PageLoad() {
		driver.get("https://blog.testproject.io/");
		
		driver.findElement(By.cssSelector("aside#secondary input.search-field")).sendKeys("selenium");
		action.moveToElement(driver.findElement(By.cssSelector("aside#secondary input.search-field"))).sendKeys(Keys.ENTER).perform();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Search Results']")));
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.posts-wrap h3.post-title>a")).getText().contains("Selenium"));
	}

	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
