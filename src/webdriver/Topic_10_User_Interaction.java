package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	Keys key;

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

	}

	@Test
	public void TC_01_HoverToElement() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
	}

	@Test
	public void TC_02_HoverToElement() {
		driver.get("https://www.fahasa.com/");

		sleepInSecond(10);

		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[id*='moe-onsite-campaign']")));
		if (driver.findElement(By.cssSelector("img.moe-image")).isDisplayed()) {
			driver.findElement(By.cssSelector("button#close-icon")).click();
		}
		driver.switchTo().defaultContent();

		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
		sleepInSecond(5);
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Tiểu Thuyết']"))).perform();
		action.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Manga - Comic']"))).perform();
		sleepInSecond(5);

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Manga - Comic']")).isDisplayed());

	}

	@Test
	public void TC_03_ClickAndHoldBlock() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> elements = driver.findElements(By.cssSelector("ol#selectable>li"));

		action.clickAndHold(elements.get(0)).moveToElement(elements.get(9)).release().perform();
		sleepInSecond(3);

		List<WebElement> selectedElements = driver.findElements(By.cssSelector("li.ui-selected"));

		Assert.assertEquals(selectedElements.size(), 6);

	}	

	@Test
	public void TC_04_ClickAndHoldRandom() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> elements = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		if (osName.contains("Window")) {	
			key = key.CONTROL;
		}
		else {
			key = key.COMMAND;
		}
		
		action.keyDown(key).perform();
		action.click(elements.get(0))
			.click(elements.get(3))
			.click(elements.get(5))
			.click(elements.get(9)).perform();
		action.keyUp(key).perform();
		
		List<WebElement> selectedElements = driver.findElements(By.cssSelector("li.ui-selected"));

		Assert.assertEquals(selectedElements.size(), 4);
	}
	
	@Test 
	public void TC_05_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(5);
		
		scrollToElement(driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
		
		
	}
	
	@Test
	public void TC_06_RightClick() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']")).isDisplayed());
		
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());
		
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover"))).perform();
		sleepInSecond(3);
		
		driver.switchTo().alert().accept();
		
		Assert.assertFalse(driver.findElement(By.xpath("//span[text()='Quit']")).isDisplayed());
		
	}
	
	@Test
	public void TC_07_DragAndDrop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		
		String colorBackground = driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color");
		
		Color hexaColorBackground = Color.fromString(colorBackground);

		Assert.assertEquals(hexaColorBackground.asHex(), "#03a9f4");
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
	
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);
		
	}
}
