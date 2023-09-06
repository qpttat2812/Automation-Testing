package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Alert alert;
	String userName = "admin";
	String password = "admin";
	
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

	@Test
	public void TC_01_AcceptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_ConfirmAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_PromptAlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String sendKeyText = "quynhpham";

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(sendKeyText);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + sendKeyText);
	}

	@Test
	public void TC_04_Alert() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
		driver.findElement(By.cssSelector("div.loginData>a")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "Customer ID  cannot be left blank.");
		alert.accept();
	}

	@Test
	public void TC_05_AuthenticationAlertByLink() {
		
		driver.get("http://the-internet.herokuapp.com");
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		String[] newUrl = url.split("//");
		
		driver.get(newUrl[0] +"//" + userName + ":" + password + "@" + newUrl[1]);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	@Test
	public void TC_06_AuthenticationAlertByAutoIT() {
		String firefoxAuthen = projectPath + "\\autoIT\\authen_firefox.exe";
		String chromeAuthen = projectPath + "\\autoIT\\authen_chrome.exe";
		
		String authenUrl = "http://the-internet.herokuapp.com/basic_auth";
		
		if(driver.toString().contains("firefox")) {
			try {
				Runtime.getRuntime().exec(new String[] {firefoxAuthen, userName, password});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else if (driver.toString().contains("chrome")){
			try {
				Runtime.getRuntime().exec(new String[] {chromeAuthen, userName, password});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.get(authenUrl);
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
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
}
