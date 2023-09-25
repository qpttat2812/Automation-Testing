package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.accessibility.AccessibleStateSet;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_JavascriptExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor obj;
	String loginUserID, loginPassword;
	String customerName = "Auto Auto";
	String lastName = "Pham";
	String gender = "female";
	String dob = "1996-11-15";
	String inputAddress = "123 Ngo Quyen\\nLe Huu Trac";
	String outputAddress = "123 Ngo Quyen Le Huu Trac";
	String city = "Vietnam";
	String state = "Danang";
	String pin = "444999";
	String mobilePhone = "1289567870";
	String email = getEmail();
	String password = "190300";
	
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
		
		obj = (JavascriptExecutor)driver;
		
	}
	
	@Test
	public void TC_01_JavaScriptExecutor() {
		navigateURL("http://live.techpanda.org/");
		sleepInSecond(5);
		
		//get domain
		Assert.assertEquals(getDomain(), "live.techpanda.org");
		
		//get url
		Assert.assertEquals(getURL(), "http://live.techpanda.org/");
		
		//open page
		clickElement("//a[text()='Mobile']");
		sleepInSecond(5);
		Assert.assertEquals(getURL(), "http://live.techpanda.org/index.php/mobile.html");
		
		//add to cart - samsung galaxy
		clickElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(5);
		Assert.assertTrue(getInnterText().contains("Samsung Galaxy was added to your shopping cart."));
		
		//open Customer Service page
		clickElement("//a[text()='Customer Service']");
		sleepInSecond(5);
		Assert.assertEquals(getTitle(), "Customer Service");
		
		//scroll to Newsletter page
		scrollIntoElement("//input[@id='newsletter']");
		sendKeyToElement("//input[@id='newsletter']", getEmail());
		clickElement("//button[@title='Subscribe']");
		sleepInSecond(5);
		
		//verify message
		Assert.assertTrue(getInnterText().contains("Thank you for your subscription."));
		
		//navigate domain guru99
		navigateURL("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		Assert.assertEquals(getDomain(), "demo.guru99.com");
	}
	
	@Test
	public void TC_02_VerifyHTML5ValidateMessage_1() {
		navigateURL("https://automationfc.github.io/html5/index.html");
		clickElement("//input[@name='submit-btn']");
		sleepInSecond(3);
		Assert.assertEquals(getValidateMessage("//input[@id='fname']"), "Please fill out this field.");
		
		sendKeyToElement("//input[@id='fname']", "quynhpham");
		clickElement("//input[@name='submit-btn']");
		sleepInSecond(3);
		Assert.assertEquals(getValidateMessage("//input[@id='pass']"), "Please fill out this field.");
		
		sendKeyToElement("//input[@id='pass']", "123456");
		clickElement("//input[@name='submit-btn']");
		sleepInSecond(3);
		Assert.assertEquals(getValidateMessage("//input[@id='em']"), "Please fill out this field.");
		
		sendKeyToElement("//input[@id='em']", "123@45!!2");
		clickElement("//input[@name='submit-btn']");
		sleepInSecond(3);
		Assert.assertEquals(getValidateMessage("//input[@id='em']"), "Please enter an email address.");
		
//		sendKeyToElement("//input[@id='em']", "123@456");
//		clickElement("//input[@name='submit-btn']");
//		sleepInSecond(3);
//		Assert.assertEquals(getValidateMessage("//input[@id='em']"), "Please match the request format.");
		
		
		
		sendKeyToElement("//input[@id='em']", "quynh123@yopmail.com");
		clickElement("//input[@name='submit-btn']");
		sleepInSecond(3);
		Assert.assertEquals(getValidateMessage("//label[@for='em']/following-sibling::select"), "Please select an item in the list.");
	}
	
	@Test
	public void TC_03_VerifyHTML5ValidateMessage_2() {
		navigateURL("https://login.ubuntu.com/");
		
		if (getElement("//div[@class='p-modal__dialog']").isDisplayed()) {
			clickElement("//button[@id='cookie-policy-button-accept']");
		}
		else {
			System.out.println("No dialog is displayed");
		}
		
		sendKeyToElement("//div[@class='login-form']//input[@id='id_email']", "abc");
		clickElement("//span[text()='Log in']");
		sleepInSecond(5);
		Assert.assertEquals(getValidateMessage("//div[@class='login-form']//input[@id='id_email']"), "Please enter an email address.");
		
		navigateURL("https://sieuthimaymocthietbi.com/account/register");
		sleepInSecond(3);
		clickElement("//button[@class='btn btn-style btn-blues']");
		sleepInSecond(3);
		Assert.assertEquals(getValidateMessage("//input[@id='lastName']"), "Please fill out this field.");
		
		navigateURL("https://warranty.rode.com/login");
		clickElement("//a[contains(text(),'Create an Account')]");
		sleepInSecond(3);
		Assert.assertEquals(getURL(), "https://warranty.rode.com/register");
		clickElement("//button[contains(.,'Register')]");
		sleepInSecond(3);
		Assert.assertEquals(getValidateMessage("//input[@id='name']"), "Please fill out this field.");
		
	}
	
	
	@Test
	public void TC_04_RemoveAttribute() {
		navigateURL("http://demo.guru99.com/");
		sleepInSecond(10);
		
		//get id and password
		sendKeyToElement("//input[@name='emailid']", getEmail());
		clickElement("//input[@name='btnLogin']");
		sleepInSecond(10);
		
		loginUserID = getTextJavascriptExecutor("//td[text()='User ID :']/following-sibling::td"); //mngr524501
		loginPassword = getTextJavascriptExecutor("//td[text()='Password :']/following-sibling::td"); //jEzUpun
		
		//navigate login page
		navigateURL("http://demo.guru99.com/v4");
		sleepInSecond(10);
		sendKeyToElement("//input[@name='uid']", loginUserID);
		sendKeyToElement("//input[@name='password']", loginPassword);
		clickElement("//input[@name='btnLogin']");
		sleepInSecond(10);
		Assert.assertTrue(getInnterText().contains("Welcome To Manager's Page of Guru99 Bank"));
		
		//new customer
		clickElement("//a[text()='New Customer']");
		sleepInSecond(10);
		Assert.assertEquals(getURL(), "https://demo.guru99.com/v4/manager/addcustomerpage.php");
		
		sendKeyToElement("//input[@name='name']", customerName);
		clickElement("//input[@value='f']");
		removeAttribute("//input[@id='dob']", "type");
		sendKeyToElement("//input[@id='dob']", dob);
		sendKeyToElementByDocument("addr", inputAddress);
		sendKeyToElement("//input[@name='city']", city);
		sendKeyToElement("//input[@name='state']", state);
		sendKeyToElement("//input[@name='pinno']", pin);
		sendKeyToElement("//input[@name='telephoneno']", mobilePhone);
		sendKeyToElement("//input[@name='emailid']", email);
		sendKeyToElement("//input[@name='password']", password);
		clickElement("//input[@name='sub']");
		sleepInSecond(10);
		
		//Verify customer info
		Assert.assertEquals(getTextJavascriptExecutor("//p[@class='heading3']"), "Customer Registered Successfully!!!");
		Assert.assertTrue(getElement("//td[text()='Customer ID']/following-sibling::td").isDisplayed());
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='Customer Name']/following-sibling::td"), customerName);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='Gender']/following-sibling::td"), gender);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='Birthdate']/following-sibling::td"), dob);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='Address']/following-sibling::td"), outputAddress);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='City']/following-sibling::td"), city);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='State']/following-sibling::td"), state);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='Pin']/following-sibling::td"), pin);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='Mobile No.']/following-sibling::td"), mobilePhone);
		Assert.assertEquals(getTextJavascriptExecutor("//td[text()='Email']/following-sibling::td"), email);
	}
	
	@Test
	public void TC_05_CreateAnAccount() {
		navigateURL("http://live.techpanda.org/");
		clickElement("//div[@id='header-account']//a[text()='My Account']");
		sleepInSecond(10);
		Assert.assertTrue(getInnterText().contains("Login or Create an Account"));
		clickElement("//span[text()='Create an Account']");
		sleepInSecond(10);
		
		//input register form
		sendKeyToElement("//input[@id='firstname']", customerName);
		sendKeyToElement("//input[@id='lastname']", lastName);
		sendKeyToElement("//input[@id='email_address']", email);
		sendKeyToElement("//input[@id='password']", password);
		sendKeyToElement("//input[@id='confirmation']", password);
		clickElement("//span[text()='Register']");
		sleepInSecond(10);
		
		Assert.assertTrue(getInnterText().contains("Thank you for registering with Main Website Store."));
		
		clickElement("//a[text()='Log Out']");
		
		Assert.assertTrue(getElement("//h2[contains(text(), 'This is demo site for')]").isDisplayed());
	
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
	
//	public Object executeBrowser(String javaScript) {
//		return obj.executeScript(javaScript);
//	}
	
	public void navigateURL(String url) {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		obj.executeScript("window.location = '" + url + "'");
	}
	
	public String getDomain() {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		return (String)(obj.executeScript("return document.domain"));
	}
	
	public String getURL() {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		return (String)(obj.executeScript("return document.URL"));
	}
	
	public void clickElement(String locator) {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		obj.executeScript("arguments[0].click()", getElement(locator));
	}
	
	public String getInnterText() {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		return (String) obj.executeScript("return document.documentElement.innerHTML");
	}
	
	public String getTitle() {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		return (String) obj.executeScript("return document.title");
	}
	
	public void scrollIntoElement(String locator) {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		obj.executeScript("arguments[0].scrollIntoView(false)", getElement(locator));
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public void sendKeyToElement(String locator, String value) {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		obj.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public void sendKeyToElementByDocument(String nameAttribute, String sendKeyValue) {
//		JavascriptExecutor obj = (JavascriptExecutor)driver;
		System.out.println(obj.executeScript("document.getElementsByName('" + nameAttribute + "')[0].value=" + "\"" + sendKeyValue + "\""));
	}
	
	public String getValidateMessage(String locator) {
		return (String) obj.executeScript("return arguments[0].validationMessage", getElement(locator));
	}
	
	public String getTextJavascriptExecutor(String locator) {
		return (String) obj.executeScript("return arguments[0].innerText", getElement(locator));
	}
	
	public void removeAttribute(String locator, String value) {
		 obj.executeScript("return arguments[0].removeAttribute('" + value + "')", getElement(locator));
	}
	
	public String getEmail() {
		Random rnd = new Random();
		int number = rnd.nextInt(9999);
		return "quynh" + number + "@yopmail.com";
	}
}
