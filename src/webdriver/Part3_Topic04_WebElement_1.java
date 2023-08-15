package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Part3_Topic04_WebElement_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	By emailLocator = By.cssSelector("#mail");
	By ageUnder18Locator = By.cssSelector("#under_18");
	By educationLocator = By.name("user_edu");
	By javaLanguageLocator = By.cssSelector("#java");
	
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
	
	//@Test
	public void TC_01_CheckDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if((driver.findElement(emailLocator).isDisplayed())){
			System.out.println("Email is displayed");
			driver.findElement(emailLocator).sendKeys("Automation Testing");
		}
		else {
			System.out.println("Email is not displayed");
		}
		
		if(driver.findElement(ageUnder18Locator).isDisplayed()) {
			System.out.println("Age under 18 is displayed");
			driver.findElement(ageUnder18Locator).click();
		}
		else {
			System.out.println("Age under 18 is not displayed");
		}
		
		if (driver.findElement(educationLocator).isDisplayed()) {
			System.out.println("Education is displayed");
			driver.findElement(educationLocator).sendKeys("Automation Testing");
		}
		else {
			System.out.println("Education is not displayed");
		}
		
		if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
			System.out.println("Name: User 5 is displayed");
		}
		else {
			System.out.println("Name: User 5 is not displayed");
		}
	}
	
	//@Test
	public void TC_02_CheckEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (driver.findElement(emailLocator).isEnabled()) {
			System.out.println("Email is enabled");
		}
		else {
			System.out.println("Email is disabled");
		}
		
		if (driver.findElement(ageUnder18Locator).isEnabled()) {
			System.out.println("Age under 18 is enabled");
		}
		else {
			System.out.println("Age under 18 is disabled");
		}
		
		if (driver.findElement(educationLocator).isEnabled()) {
			System.out.println("Education is enabled");
		}
		else {
			System.out.println("Education is disabled");
		}
		
		if (driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println("Job role 1 is enabled");
		}
		else {
			System.out.println("Job role 1 is disabled");
		}
		
		if (driver.findElement(By.name("user_job2")).isEnabled()) {
			System.out.println("Job role 2 is enabled");
		}
		else {
			System.out.println("Job role 2 is disabled");
		}
		
		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println("Development checkbox is enabled");
		}
		else {
			System.out.println("Development checkbox is disabled");
		}
		
		if (driver.findElement(By.name("slider-1")).isEnabled()) {
			System.out.println("Slide 1 is enabled");
		}
		else {
			System.out.println("Slide 1 is disabled");
		}
		
		if (driver.findElement(By.id("disable_password")).isEnabled()) {
			System.out.println("Password is enabled");
		}
		else {
			System.out.println("Password is disabled");
		}
		
		if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println("Age - Radio button is enabled");
		}
		else {
			System.out.println("Age - Radio button is disabled");
		}
		
		if (driver.findElement(By.id("bio")).isEnabled()) {
			System.out.println("Biography is enabled");
		}
		else {
			System.out.println("Biography is disabled");
		}
		
		if (driver.findElement(By.id("job3")).isEnabled()) {
			System.out.println("Job role 03 is enabled");
		}
		else {
			System.out.println("Job role 03 is disabled");
		}
		
		if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
			System.out.println("Interest checkbox is enabled");
		}
		else {
			System.out.println("Interest checkbox is disabled");
		}
		
		if (driver.findElement(By.id("slider-2")).isEnabled()) {
			System.out.println("Slide 2 is enabled");
		}
		else {
			System.out.println("Slide 2 is disabled");
		}
	}
	
	@Test
	public void TC_03_CheckSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertFalse(driver.findElement(ageUnder18Locator).isSelected());
		Assert.assertFalse(driver.findElement(javaLanguageLocator).isSelected());
		
		driver.findElement(ageUnder18Locator).click();
		driver.findElement(javaLanguageLocator).click();
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(ageUnder18Locator).isSelected());
		Assert.assertTrue(driver.findElement(javaLanguageLocator).isSelected());
		
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
		
	}
	
	public void sleepInSecond(long timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
