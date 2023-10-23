package stackoverflow;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HandleRadioButton_1 {
	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Actions action;
	
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
		driver.get("https://codepen.io/raz-luvaton/pen/eYxYard");
		action = new Actions(driver);
	}
	
	@Test
	public void TC_01() {
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[id='result']")));
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'ant-radio')]"))).click();
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class *= 'ant-radio ant']"))).click();
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
