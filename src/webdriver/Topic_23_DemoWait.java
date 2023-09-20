package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic24_DemoWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_Visibility() {
		driver.get("https://www.facebook.com/");

		// Element visible in UI (compulsory)
		// Element in DOM (compulsory)
		// wait for create an account element visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='open-registration-form-button']")));

	}

	@Test
	public void TC_02_InvisibilityInDOM() {
		driver.get("https://www.facebook.com/");

		// wait for create an account button is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='open-registration-form-button']")));

		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		driver.findElement(By.cssSelector("input[name*='reg_email']")).sendKeys("abc@gmail.com");
		sleepInSecond(3);

		// wait for re-enter email is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name*='email_confirmation']")));

		// Element can be hidden in website/it's located in another page (compulsory)
		// Element in DOM
		driver.findElement(By.cssSelector("input[name*='reg_email']")).clear();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name*='email_confirmation']")));

	}

	@Test
	public void TC_03_InvisibilityNotInDOM() {
		driver.get("https://www.facebook.com/");

		// wait for create an account button is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='open-registration-form-button']")));
		// click create an account button
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// wait for create form visibble
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._8ien")));
		// click close
		clickToElementByJS(driver.findElement(By.cssSelector("img._8idr.img")));
		// wait for create form invisibility
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div._8ien")));
	}

	@Test
	public void TC_04_Presence() {
		driver.get("https://www.facebook.com/");

		// wait for create an account button is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='open-registration-form-button']")));
		// click create an account button
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// wait for confirm email visible in DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name*='email_confirmation']")));
	}

	@Test
	public void TC_05_Staleness() {
		driver.get("https://www.facebook.com/");

		// wait for create an account button is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-testid='open-registration-form-button']")));
		// click create an account button
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

		// wait for create form visibble
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div._8ien")));
		// click close
		clickToElementByJS(driver.findElement(By.cssSelector("img._8idr.img")));
		
		//wait for sign up button in create form is staleness
		//not visible in UI(compulsory)
		//not in DOM(compulsory)
		explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("button[name='websubmit']"))));
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickToElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
