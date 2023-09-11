package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Popup_Random {
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

		// turn off notification
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);

		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_RandomInDOM() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(20);

		if (driver.findElement(By.cssSelector("div[data-type='rectangle']")).isDisplayed()) {
			driver.findElement(By.cssSelector("input[placeholder='Your Email'],[placeholder='Enter your e-mail address']")).sendKeys(getEmail());
			driver.findElement(By.cssSelector("a[data-label='OK'], [data-label='Get the Books']")).click();
			sleepInSecond(5);
			Assert.assertTrue(driver.findElement(By.cssSelector("div[data-page='confirmation'] div.lepopup-element")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("div[data-page='confirmation'] div.lepopup-element")).getText().contains("Your sign-up request was successful."));
			sleepInSecond(10);
		}

		String bookName = "Agile Testing Explained";
		driver.findElement(By.cssSelector("input#search-input")).sendKeys(bookName);
		driver.findElement(By.cssSelector("button#search-submit")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("ul#posts-container>li:nth-child(1) h2.post-title>a")).getText(), bookName);

	}

//	@Test
	public void TC_02_RandomInDOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(30);

		// turn off chat form
		By iframeChat = By.cssSelector("div.fb_iframe_widget iframe");
		if (driver.findElement(iframeChat).isDisplayed()) {
			driver.switchTo().frame(driver.findElement(iframeChat));
			driver.findElement(By.cssSelector("div.welcomePageModalSheetContent div[aria-label='Đóng']")).click();
			driver.switchTo().defaultContent();
		}

		if (driver.findElement(By.cssSelector("div.tcb-flex-row div.tve_empty_dropzone div.tve-content-box-background")).isDisplayed()) {
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			sleepInSecond(3);
		}

		driver.findElement(By.cssSelector("a[title='Khóa học ONLINE']")).click();
		driver.findElement(By.cssSelector("a[title='Khóa Thiết kế ĐHKK']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/courses?category=5646");

		String courseName = "Khóa học thiết kế hệ thống điều hòa không khí";
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.wrap-courses>div:nth-child(1) h4")).getText(), "Khóa học thiết kế hệ thống điều hòa không khí");
	}

	@Test
	public void TC_03_RandomNotInDOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		// turn off chat form
		By iframeChat = By.cssSelector("div.fb_iframe_widget iframe");
		if (driver.findElement(iframeChat).isDisplayed()) {
			driver.switchTo().frame(driver.findElement(iframeChat));
			driver.findElement(By.cssSelector("div.welcomePageModalSheetContent div[aria-label='Đóng']")).click();
			driver.switchTo().defaultContent();
		}
		
		if (driver.findElement(By.cssSelector("div.popup-content")).isDisplayed()) {
			driver.findElement(By.cssSelector("button.close")).click();
			sleepInSecond(3);
		}
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.popup-content")).size(), 0);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getEmail() {
		Random rnd = new Random();
		String email = "quynhpham" + String.valueOf(rnd.nextInt(9999)) + "@yopmail.com";
		return email;
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
