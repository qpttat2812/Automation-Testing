package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
	}

//	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector("li.popup-login-tab-item")).click();

		Assert.assertFalse(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

		By colorButtonLocator = By.cssSelector("button.fhs-btn-login");
		String colorButton = driver.findElement(colorButtonLocator).getCssValue("background-image");
		Assert.assertTrue(colorButton.contains("rgb(224, 224, 224)"));

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0123456789");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123123123");
		colorButton = driver.findElement(colorButtonLocator).getCssValue("background-color");
		Color loginButtonColor = Color.fromString(colorButton);
		Assert.assertEquals(loginButtonColor.asHex().toUpperCase(), "#C92127");
	}

//	@Test
	public void TC_02_DefaultCheckBox_RadioButton() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		checkSelectedCheckbox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
		checkUnSelectedCheckbox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		WebElement radioButtonLocator = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		radioButtonLocator.click();
		
		checkSelectedRadioButton(radioButtonLocator);
	}

//	@Test
	public void TC_03_DefaultCheckBox_RadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		checkSelectedRadioButton(driver.findElement(By.xpath("//label[contains(text(),'Summer')]/preceding-sibling::div/input")));
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		WebElement checkedOptionLocator = driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input"));
		WebElement indeterminateOptionLocator = driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input"));
		
		checkSelectedCheckbox(checkedOptionLocator);
		checkSelectedCheckbox(indeterminateOptionLocator);
		checkUnSelectedCheckbox(checkedOptionLocator);
		checkUnSelectedCheckbox(indeterminateOptionLocator);
		
	}

//	@Test
	public void TC_04_DefaultCheckBox_RadioButton() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		clickToElementJS(driver.findElement(By.xpath("//div[(text()= 'Đăng ký cho người thân')]/preceding-sibling::div/input")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[(text()= 'Đăng ký cho người thân')]/preceding-sibling::div/input")).isSelected());
	}

	@Test
	public void TC_05_DefaultCheckBox_RadioButton() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		WebElement cityLocator = driver.findElement(By.xpath("//span[text()='Cần Thơ']/preceding::div//div[@data-value='Cần Thơ']"));
		if(cityLocator.getAttribute("aria-checked").equals("false")) {
			cityLocator.click();
		}
		
		Assert.assertTrue(cityLocator.getAttribute("aria-checked").equals("true"));
	}

	public void checkSelectedCheckbox(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}

		Assert.assertTrue(element.isSelected());
	}

	public void checkUnSelectedCheckbox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}

		Assert.assertFalse(element.isSelected());
	}
	
	public void checkSelectedRadioButton(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
		
		Assert.assertTrue(element.isSelected());
	}
	
	public void clickToElementJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
