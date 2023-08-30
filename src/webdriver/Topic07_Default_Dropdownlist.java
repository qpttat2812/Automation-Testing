package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Default_Dropdownlist {
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriver driver;
	String firstName = "Quynh";
	String lastName = "Pham";
	String day = "11";
	String month = "January";
	String year = "1998";
	String email = getEmail();
	String password = "123123";

	// declare Locators
	By firstNameLocator = By.id("FirstName");
	By lastNameLocator = By.id("LastName");
	By emailLocator = By.id("Email");
	By passwordLocator = By.id("Password");
	By confirmPasswordLocator = By.id("ConfirmPassword");

	@BeforeClass
	public void BeforeClass() {
		if (osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	//@Test
	public void TC_01_HandleDropdownlist_1() {
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.cssSelector("a.ico-register")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Register']")).getText(), "Register");

		// input info
		driver.findElement(firstNameLocator).sendKeys(firstName);
		driver.findElement(lastNameLocator).sendKeys(lastName);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(day);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
		new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);
		driver.findElement(emailLocator).sendKeys(email);
		driver.findElement(passwordLocator).sendKeys(password);
		driver.findElement(confirmPasswordLocator).sendKeys(password);

		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.cssSelector("a.ico-login")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Welcome, Please Sign In!']")).getText(), "Welcome, Please Sign In!");

		// login
		driver.findElement(emailLocator).sendKeys(email);
		driver.findElement(passwordLocator).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();

		// verify birthday
		driver.findElement(By.cssSelector("a.ico-account")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My account - Customer info']")).getText(), "My account - Customer info");
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

	}

//	@Test
	public void TC_02_HandleDropdownlist_2() {
		driver.get("https://www.rode.com/wheretobuy");
		
		if(driver.findElement(By.cssSelector("div.cookie__floating__wrap")).isDisplayed()) {
			driver.findElement(By.cssSelector("button[class *= 'btn-success']")).click();
			if (driver.findElement(By.cssSelector("div[class *= 'swal2-popup']")).isDisplayed()) {
				driver.findElement(By.cssSelector("button[class *= 'swal2-confirm']")).click();
			}
		}
		
		Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());
		new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#country"))).getFirstSelectedOption().getText(), "Vietnam");
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
		List<WebElement> elements = driver.findElements(By.cssSelector("h4.text-left"));
		
		for(WebElement element:elements) {
			System.out.println("List of Dealers: " + element.getText());
		}
		
	}

	@Test
	public void TC_03_HandleDropdownlist_3() {
		driver.get("https://applitools.com/automating-tests-chrome-devtools-recorder-webinar/");
		scrollIntoView(driver.findElement(By.xpath("//button[text()='REGISTER NOW']")));
		new Select(driver.findElement(By.cssSelector("select#Person_Role__c"))).selectByVisibleText("SDET / Test Automation Engineer");
		new Select(driver.findElement(By.cssSelector("select#Test_Framework__c"))).selectByVisibleText("Selenium");
		new Select(driver.findElement(By.cssSelector("select#Self_Report_Country__c"))).selectByVisibleText("Germany");
		
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#Person_Role__c"))).getFirstSelectedOption().getText(), "SDET / Test Automation Engineer");
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#Test_Framework__c"))).getFirstSelectedOption().getText(), "Selenium");
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#Self_Report_Country__c"))).getFirstSelectedOption().getText(), "Germany");
		
	}

	public String getEmail() {
		Random rnd = new Random();
		int number = rnd.nextInt(9999);
		return "quynh" + number + "@yopmail.com";
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);
	}

//	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
