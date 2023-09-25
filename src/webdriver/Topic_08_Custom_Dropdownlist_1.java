package webdriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdownlist_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	Actions action;
	
	String[] expectedMonthArr_1 = { "March", "June", "September" };
	String[] expectedMonthArr_2 = { "March", "June", "September", "October", "November" };
	String[] expectedMonthArr_3 = { "[Select all]" };
	
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
	}

//	@Test
	public void TC_01_JqueryDropdown() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdownlist("span#speed-button", "ul#speed-menu div[class *='ui-menu-item-wrapper']", "Slow");
		Assert.assertEquals(driver.findElement(By.cssSelector("span.ui-selectmenu-text")).getText(), "Slow");

		selectItemInDropdownlist("span#speed-button", "ul#speed-menu div[class *='ui-menu-item-wrapper']", "Faster");
		Assert.assertEquals(driver.findElement(By.cssSelector("span.ui-selectmenu-text")).getText(), "Faster");

	}

//	@Test
	public void TC_02_ReactDropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInDropdownlist("div[role='listbox']", "div[role='listbox'] span", "Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

		selectItemInDropdownlist("div[role='listbox']", "div[role='listbox'] span", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
	}

//	@Test
	public void TC_03_VueDropdown() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInDropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

		selectItemInDropdownlist("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

	}

//	@Test
	public void TC_04_SelectEditableDropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		driver.findElement(By.cssSelector("div[role='combobox']")).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role='option']//span")));

		WebElement expectedElementLocator = driver.findElement(By.xpath("//span[text()='Belgium']"));

		clickToElement(expectedElementLocator);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
	}

//	@Test
	public void TC_05_InputAndSelectItemDropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		inputAndSelectItemInDropdownlist("input.search", "div[role='option'] span", "Albania");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");

		inputAndSelectItemInDropdownlist("input.search", "div[role='option'] span", "Austria");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Austria");
	}

//	@Test
	public void TC_06_JqueryCustomDropdown() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");

		if (driver.findElement(By.cssSelector("div.cookies")).isDisplayed()) {
			driver.findElement(By.cssSelector("button.btn-primary")).click();
		}

		scrollIntoView(driver.findElement(By.xpath("//span[text()='TỔNG CỘNG (VNĐ)']")));

		// select Chọn loại xe dropdownlist
		driver.findElement(By.cssSelector("button#selectize-input")).click();
		WebElement expectedCarElement = driver.findElement(By.xpath("//a[text()='CIVIC G (Trắng ngọc)']"));
		clickToElement(expectedCarElement);

		// select Tỉnh/TP
		new Select(driver.findElement(By.cssSelector("select#province"))).selectByVisibleText("Thái Nguyên");

		// select Khu vực
		new Select(driver.findElement(By.cssSelector("select#registration_fee"))).selectByVisibleText("Khu vực II");

		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CIVIC G (Trắng ngọc)");
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#province"))).getFirstSelectedOption().getText(), "Thái Nguyên");
		Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select#registration_fee"))).getFirstSelectedOption().getText(), "Khu vực II");

	}

//	@Test
	public void TC_07_AngularCustomDropdownList() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		scrollIntoView(driver.findElement(By.cssSelector("button.btn-outline-danger")));

		sleepInSecond(5);
		// city
		selectItemInDropdownlist("ng-select[formcontrolname='provinceCode'] span.ng-arrow-wrapper", "div[role='option'] span.ng-option-label", "Thành phố Đà Nẵng");
		sleepInSecond(5);

		// state
		selectItemInDropdownlist("ng-select[formcontrolname='districtCode'] span.ng-arrow-wrapper", "div[role='option'] span.ng-option-label", "Quận Sơn Trà");
		sleepInSecond(5);

		// ward
		selectItemInDropdownlist("ng-select[formcontrolname='wardCode'] span.ng-arrow-wrapper", "div[role='option'] span.ng-option-label", "Phường Phước Mỹ");
		sleepInSecond(5);
	}

	@Test
	public void TC_08_MultipleSelectRaw() {

		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		sleepInSecond(5);

		// javascript executor
//		clickToElement(driver.findElement(By.xpath("(//div[contains(@class,'multiple-select')])[position()=1]/button")));
		clickToElement(driver.findElement(By.cssSelector("div.form-group:nth-of-type(2) div.multiple-select>button")));

		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("(//div[contains(@class,'multiple-select')])[position()=1]")).getAttribute("class").contains("ms-parent-open"));

		List<WebElement> monthList = driver.findElements(By.cssSelector("div.form-group:nth-of-type(2) div.ms-drop span"));
//		List<WebElement> monthList = driver.findElements(By.xpath("(//div[contains(@class, 'ms-drop')])[position()=1]//span"));

		int countSelectedMonth = 0;
		for (WebElement month : monthList) {
			for (int i = 0; i < expectedMonthArr_1.length; i++) {
				if (month.getText().equals(expectedMonthArr_1[i])) {
					clickToElement(month);
//					sleepInSecond(3);
					countSelectedMonth++;
				}
			}
		}

		String selectedMonthList = driver.findElement(By.cssSelector("div.form-group:nth-of-type(2) button>span")).getText();

		String expectedMonthResult = String.join(", ", expectedMonthArr_1);

		boolean selectAllFlag = false;
		if (expectedMonthArr_1.length <= 3) {
			for (int k = 0; k < expectedMonthArr_1.length; k++) {
				if (expectedMonthArr_1[k].equals("[Select all]")) {
					selectAllFlag = true;
					break;
				}
			}
			if (selectAllFlag == false) {
				Assert.assertEquals(selectedMonthList, expectedMonthResult);
				System.out.println("===== Less than or equal to 3 is executed!!! ======");
			} else {
				Assert.assertEquals(selectedMonthList, "All selected");
				System.out.println("===== All selected is executed!!! ======");
			}
		} 
		else if ((expectedMonthArr_1.length > 3) && (expectedMonthArr_1.length < 12))

		{
			Assert.assertEquals(selectedMonthList, String.format("%d of 12 selected", countSelectedMonth));
			System.out.println(String.format("%d of 12 selected", countSelectedMonth));
			System.out.println("===== Greather than 4 and less than 12 is executed!!! ======");
		}

	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);
	}

	public void clickToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}

	public void selectItemInDropdownlist(String parentDropdownLocator, String allItemsLocator, String expectedValue) {
		driver.findElement(By.cssSelector(parentDropdownLocator)).click();

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemsLocator)));

		List<WebElement> dropDownElements = driver.findElements(By.cssSelector(allItemsLocator));

		for (WebElement element : dropDownElements) {
			if (element.getText().trim().equals(expectedValue)) {
				clickToElement(element);
				break;
			}
		}
	}

	public void inputAndSelectItemInDropdownlist(String parentDropdownLocator, String allItemsLocator, String expectedValue) {
		driver.findElement(By.cssSelector(parentDropdownLocator)).sendKeys(expectedValue);

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemsLocator)));

		List<WebElement> dropDownElements = driver.findElements(By.cssSelector(allItemsLocator));

		for (WebElement element : dropDownElements) {
			if (element.getText().trim().equals(expectedValue)) {
				element.click();
				break;
			}
		}
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
