package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@BeforeClass
	public void BeforeClass() {
		if(osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckdodriver");

		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
//	@Test
	public void TC_01_HandleTab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		scrollToElement(driver.findElement(By.xpath("//legend[contains(string(),'Table')]")));
		String basePageTitle = "Selenium WebDriver";
		
		//switch to google.com.vn
		String baseWindowID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		switchToWindowByID(baseWindowID);
		Assert.assertEquals(driver.getTitle(), "Google");
		
		//back to base page
		String googleWindowID = driver.getWindowHandle();
		switchToWindowByID(googleWindowID);
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), basePageTitle);
		
		//switch to facebook page
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(8);
		swithToWindowByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
		
		//back to base page
		swithToWindowByTitle("Selenium WebDriver");
		Assert.assertEquals(driver.getTitle(), basePageTitle);
		
		//switch to tiki page
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(5);
		swithToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		//close all windows and back to base page
		closeAllWindowsExceptBasePage(basePageTitle);
		sleepInSecond(3);
		swithToWindowByTitle(basePageTitle);
		Assert.assertEquals(driver.getTitle(), basePageTitle);
	}
	
//	@Test
	public void TC_02_HandleWindow() {
		driver.get("https://kyna.vn/");
		sleepInSecond(5);
		
		String basePageTitle = "Kyna.vn - Học online cùng chuyên gia";
		scrollToElement(driver.findElement(By.xpath("//h4[text()='Kết nối với Kyna']")));
		
		//click facebook fanpage
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		driver.findElement(By.cssSelector("a[title='Kyna.vn']")).click();
		sleepInSecond(3);
		
		//switch to kyna facebook page
		swithToWindowByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn | Ho Chi Minh City | Facebook");
		
		//back to kyna page
		swithToWindowByTitle(basePageTitle);
		Assert.assertEquals(driver.getTitle(), basePageTitle);
		
		//click to youtube link
		driver.findElement(By.cssSelector("div#k-footer a[href*='youtube']")).click();
		sleepInSecond(5);
		swithToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		//back to kyna page
		swithToWindowByTitle(basePageTitle);
		Assert.assertEquals(driver.getTitle(), basePageTitle);
		
		//switch to "Ministry of Industry and Trade" page
		driver.findElement(By.cssSelector("div#k-footer-copyright a[href*='HomePage/CustomWebsiteDisplay']")).click();
		sleepInSecond(5);
		swithToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		
		//close all tabs except kyna page
		closeAllWindowsExceptBasePage(basePageTitle);
		sleepInSecond(3);
	}
	
	@Test
	public void TC_03_HandleWindow() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Mobile']")).getText(), "MOBILE");
		String basePageTitle = "Mobile";
		
		//add Sony product to compare
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(3);
		
		//add Iphone product to compare
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The product IPhone has been added to comparison list.");
		
		//click to compare button
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSecond(3);
		swithToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		//close tab
		closeAllWindowsExceptBasePage(basePageTitle);
		sleepInSecond(3);
		swithToWindowByTitle(basePageTitle);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepInSecond(3);
		driver.switchTo().alert().accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg")).getText(), "The comparison list was cleared.");
	}
	
	//apply only for 2 tabs/windows
	public void switchToWindowByID(String baseWindowID) {
		Set<String> allWindowsID = driver.getWindowHandles();
		
		for(String windowID : allWindowsID) {
			if(!windowID.equals(baseWindowID)) {
				driver.switchTo().window(windowID);
			}
		}
	}
		
	//apply for >= 2 windows/tabs
	public void swithToWindowByTitle(String expectedTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		
		for(String windowID : allWindowsID) {
			driver.switchTo().window(windowID);
			if(driver.getTitle().equals(expectedTitle)) {
				break;
			}
		}
	}
	
	public void closeAllWindowsExceptBasePage(String basePageTitle) {
		Set<String> allWindowsID = driver.getWindowHandles();
		
		for(String windowID : allWindowsID) {
			driver.switchTo().window(windowID);
			if(!driver.getTitle().equals(basePageTitle)) {
				driver.close();
			}
		}
	}
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

}
