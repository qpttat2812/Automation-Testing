package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DateTimePicker {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void BeforeClass() {
		if (osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	@Test
	public void TC_01_HandleDateTimePicker() {
		driver.get("https://www.makemytrip.com/");
		
		//turn off popup
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class *='displayBlock modalLogin']")));
		driver.findElement(By.cssSelector("span.commonModal__close")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[class *='displayBlock modalLogin']")));
		
		//choose date from datetimepicker
		By departureDate = By.cssSelector("label[for='departure']");
		explicitWait.until(ExpectedConditions.elementToBeClickable(departureDate));
		driver.findElement(departureDate).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.datePickerContainer")));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.datePickerContainer")));
		//click next months
		driver.findElement(By.cssSelector("span.DayPicker-NavButton--next")).click();
		List<WebElement> dateElements = driver.findElements(By.cssSelector("div.DayPicker-Month"));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(dateElements));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='December 2023']/parent::div/following-sibling::div[@class='DayPicker-Body']//div[contains(@aria-label, 'Dec 04 2023')]")));
		
		WebElement selectDate = driver.findElement(By.xpath("//div[text()='December 2023']/parent::div/following-sibling::div[@class='DayPicker-Body']//div[contains(@aria-label, 'Dec 04 2023')]"));
		selectDate.click();
		
		WebElement selectedMonth = driver.findElement(By.xpath("//p[@data-cy='departureDate']/span[text()='Dec']"));
		WebElement selectedDay = driver.findElement(By.xpath("//p[@data-cy='departureDate']/span[text()='4']"));
		WebElement selectedYear = driver.findElement(By.xpath("//p[@data-cy='departureDate']/span[@class='shortYear']"));
		
		Assert.assertTrue(selectedMonth.getText().contains("Dec"));
		Assert.assertTrue(selectedDay.getText().contains("4"));
		Assert.assertTrue(selectedYear.getText().contains("23"));
		
		//checked selected date in datetimepicker
		driver.findElement(departureDate).click();
		
		List<WebElement> dateElementsafterSelectedDate = driver.findElements(By.cssSelector("div.DayPicker-Month"));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(dateElementsafterSelectedDate));
		
		WebElement selectedDate = driver.findElement(By.xpath("//div[text()='December 2023']/parent::div/following-sibling::div[@class='DayPicker-Body']//div[contains(@aria-label, 'Dec 04 2023')]"));
		String selectedStatus = selectedDate.getAttribute("aria-selected");
		Assert.assertEquals(selectedStatus, "true");
	}
	
	public void clickOnElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
