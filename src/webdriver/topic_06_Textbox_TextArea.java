package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName = "Brian";
	String lastName = "Pham";
	String emailValue = getEmail(firstName);
	String password = "123456";
	String employeeId = String.valueOf(randomNumber());
	String loginUserName = "Admin";
	String loginPassword = "admin123";
	String newUserName = "quynhpham" + String.valueOf(randomNumber());
	String newPassword = "quynh123";
	String passportNumber = "1234-5678-9090";
	String commentValue = "new passport\nhas inputed";
	By firstNameLocator = By.name("firstName");
	By lastNameLocator = By.name("lastName");	
	By employeeIdLocator = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
	By passportLocator = By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input");
	By commentLocator = By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea");
	By loginUserNameLocator = By.name("username");
	By loginPasswordLocator = By.name("password");
	By loginButtonLocator = By.cssSelector(".oxd-button");
	
	//declare variables - new customer info
	String gender = "female";
	String dateOfBirth = "1999-11-11";
	String address = "123 Los Angeles\nUnited States";
	String addressOutput = "123 Los Angeles United States";
	String city = "New York";
	String state = "Buffalo";
	String pin = "123452";
	String telephoneNumber = "0981123456";
	String customerID;
	
	//declare variables - edit customer info
	String editAddress = "456 Tracy\nColombia";
	String editAddressOutput = "456 Tracy Colombia";
	String editCity = "Hawaii";
	String editState = "New Yersey";
	String editPin = "212458";
	String editTelephoneNumber = "0919113456";
	String editEmail = getEmail(firstName);
	
	//declare locator - tc 03
	By customerNameLocator = By.name("name");
	By femailLocator = By.xpath("//input[(@name='rad1') and (@value='f')]");
	By dobLocator = By.name("dob");
	By addressLocator = By.name("addr");
	By cityLocator = By.name("city");
	By stateLocator = By.name("state");
	By pinLocator = By.name("pinno");
	By telephoneLocator = By.name("telephoneno");
	By emailLocator = By.name("emailid");
	By passwordLocator = By.name("password");
	By submitButtonLocator = By.name("sub");
	By headerLocator = By.cssSelector("p.heading3");
	
	//declare edited info Locator
	By customerIDInfoLocator = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By firstNameInfoLocator = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By genderInfoLocator = By.xpath("//td[text()='Gender']/following-sibling::td");
	By dobInfoLocator = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressInfoLocator = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityInfoLocator = By.xpath("//td[text()='City']/following-sibling::td");
	By stateInfoLocator = By.xpath("//td[text()='State']/following-sibling::td");
	By pinInfoLocator = By.xpath("//td[text()='Pin']/following-sibling::td");
	By telephoneInfoLocator = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailInfoLocator = By.xpath("//td[text()='Email']/following-sibling::td");
	
	//declare login id and password
	String loginCustomerID, loginCustomerPassword;
	
	@BeforeClass
	public void BeforeClass() {
		if (osName.contains("Window")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//@Test
	public void TC_01_VerifyLoginInfo() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		 
		sleepInSecond(1);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		driver.findElement(By.cssSelector("#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(emailValue);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains(firstName + " " + lastName));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains(emailValue));
		
	}
	
		
	

//	@Test
	public void TC_02_VerifyEmployeeInfo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//login
		driver.findElement(loginUserNameLocator).sendKeys(loginUserName);
		driver.findElement(loginPasswordLocator).sendKeys(loginPassword);
		driver.findElement(loginButtonLocator).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
		
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");
		sleepInSecond(5);
		
		//input employee first name, lastname, choose create login details
		driver.findElement(firstNameLocator).sendKeys(firstName);
		driver.findElement(lastNameLocator).sendKeys(lastName);
		//delete by using CRTL + A and Delete keyboard
		driver.findElement(employeeIdLocator).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(employeeIdLocator).sendKeys(Keys.DELETE);
		
		driver.findElement(employeeIdLocator).sendKeys(employeeId);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		sleepInSecond(3);
		
		//input create user detail
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(newUserName);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(newPassword);
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(newPassword);
		driver.findElement(By.xpath("//button[contains(string(), 'Save')]")).click();
		sleepInSecond(10);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("pim/viewPersonalDetails/empNumber/"));
		
		//verify new info of user
		Assert.assertEquals(driver.findElement(firstNameLocator).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(lastNameLocator).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIdLocator).getAttribute("value"), employeeId);
		
		//click Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("pim/viewImmigration/empNumber"));
		
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/parent::div//button")).click();
		
		driver.findElement(passportLocator).sendKeys(passportNumber);
		
		driver.findElement(commentLocator).sendKeys(commentValue);
		
		driver.findElement(By.xpath("//button[contains(.,'Save')]")).click();
		
		//edit passport
		driver.findElement(By.xpath("//div[text()='Actions']/ancestor::div[@class='oxd-table']//i[contains(@class,'pencil')]/parent::button")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(passportLocator).getAttribute("_value"), passportNumber);
		Assert.assertEquals(driver.findElement(commentLocator).getAttribute("_value"), commentValue);

		//logout
		driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
		if (driver.findElement(By.xpath("//ul[@class='oxd-dropdown-menu']")).isDisplayed()) {
			driver.findElement(By.xpath("//a[text()='Logout']")).click();
		}
		
		
		//login the second time with new account
		driver.findElement(loginUserNameLocator).sendKeys(newUserName);
		driver.findElement(loginPasswordLocator).sendKeys(newPassword);
		driver.findElement(loginButtonLocator).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		
		driver.findElement(By.xpath("//span[text()='My Info']")).click();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(firstNameLocator).getAttribute("_value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameLocator).getAttribute("_value"), lastName);
		Assert.assertEquals(driver.findElement(employeeIdLocator).getAttribute("_value"), employeeId);
		
	}
	
	@Test
	public void TC_03_VerifyCustomerInfo() {
		driver.get("http://demo.guru99.com/v4");
		
		//get account to login
		driver.findElement(By.xpath("//a[text()='here']")).click();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Enter your email')]")).isDisplayed());
		driver.findElement(By.name("emailid")).sendKeys(getEmail(firstName));
		driver.findElement(By.name("btnLogin")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//h2[text()='Access details to demo site.']")).getText(), "Access details to demo site.");
		loginCustomerID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		loginCustomerPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		//login
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.name("uid")).sendKeys(loginCustomerID);
		driver.findElement(By.name("password")).sendKeys(loginCustomerPassword);
		driver.findElement(By.name("btnLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=concat(\"Welcome To Manager's \", 'Page of Guru99 Bank')]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/v4/manager/addcustomerpage.php");
		
		//input customer info
		driver.findElement(customerNameLocator).sendKeys(firstName);
		driver.findElement(femailLocator).click();
		removeAttribute(driver.findElement(dobLocator));
		driver.findElement(dobLocator).sendKeys(dateOfBirth);
		driver.findElement(addressLocator).sendKeys(address);
		driver.findElement(cityLocator).sendKeys(city);
		driver.findElement(stateLocator).sendKeys(state);
		driver.findElement(pinLocator).sendKeys(pin);
		driver.findElement(telephoneLocator).sendKeys(telephoneNumber);
		driver.findElement(emailLocator).sendKeys(emailValue);
		driver.findElement(passwordLocator).sendKeys(password);
		driver.findElement(submitButtonLocator).click();
		sleepInSecond(5);
		
		//Verify customer info
		Assert.assertEquals(driver.findElement(headerLocator).getText(), "Customer Registered Successfully!!!");
		Assert.assertTrue((driver.findElement(customerIDInfoLocator).isDisplayed()));
		//get customer id
		customerID = driver.findElement(customerIDInfoLocator).getText();
		
		Assert.assertEquals(driver.findElement(firstNameInfoLocator).getText(), firstName);
		Assert.assertEquals(driver.findElement(genderInfoLocator).getText(), gender);
		Assert.assertEquals(driver.findElement(dobInfoLocator).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressInfoLocator).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(cityInfoLocator).getText(), city);
		Assert.assertEquals(driver.findElement(stateInfoLocator).getText(), state);
		Assert.assertEquals(driver.findElement(pinInfoLocator).getText(), pin);
		Assert.assertEquals(driver.findElement(telephoneInfoLocator).getText(), telephoneNumber);
		Assert.assertEquals(driver.findElement(emailInfoLocator).getText(), emailValue);
		
		// open edit customer form
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(headerLocator).getText(), "Edit Customer Form");
		
		//input customer id
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		sleepInSecond(5);
		Assert.assertTrue(driver.getCurrentUrl().contains("manager/editCustomerPage.php"));
		
		//verify customer name and address
		Assert.assertEquals(driver.findElement(customerNameLocator).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(addressLocator).getText(), address);
		
		//clear customer 
		driver.findElement(addressLocator).clear();
		driver.findElement(cityLocator).clear();
		driver.findElement(stateLocator).clear();
		driver.findElement(pinLocator).clear();
		driver.findElement(telephoneLocator).clear();
		driver.findElement(emailLocator).clear();
		sleepInSecond(3);
		
		//edit customer
		driver.findElement(addressLocator).sendKeys(editAddress);
		driver.findElement(cityLocator).sendKeys(editCity);
		driver.findElement(stateLocator).sendKeys(editState);
		driver.findElement(pinLocator).sendKeys(editPin);
		driver.findElement(telephoneLocator).sendKeys(editTelephoneNumber);
		driver.findElement(emailLocator).sendKeys(editEmail);
		sleepInSecond(5);
		driver.findElement(submitButtonLocator).click();
		
		//verify info after editing
		//Verify customer info
//		Assert.assertEquals(driver.findElement(headerLocator).getText(), "Customer details updated Successfully!!!");
//		Assert.assertEquals(driver.findElement(customerIDInfoLocator).getText(), customerID);
//		Assert.assertEquals(driver.findElement(firstNameInfoLocator).getText(), firstName);
//		Assert.assertEquals(driver.findElement(genderInfoLocator).getText(), gender);
//		Assert.assertEquals(driver.findElement(dobInfoLocator).getText(), dateOfBirth);
//		Assert.assertEquals(driver.findElement(addressInfoLocator).getText(), editAddressOutput);
//		Assert.assertEquals(driver.findElement(cityInfoLocator).getText(), editCity);
//		Assert.assertEquals(driver.findElement(stateInfoLocator).getText(), editState);
//		Assert.assertEquals(driver.findElement(pinInfoLocator).getText(), editPin);
//		Assert.assertEquals(driver.findElement(telephoneInfoLocator).getText(), editTelephoneNumber);
//		Assert.assertEquals(driver.findElement(emailInfoLocator).getText(), editEmail);
	}
	
	
	public void removeAttribute(WebElement element) {
		JavascriptExecutor obj = (JavascriptExecutor) driver;
		obj.executeScript("arguments[0].removeAttribute('type')", element);
	}
	
	public void sleepInSecond(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getEmail(String name) {
		Random rnd = new Random();
		String emailValue = name + rnd.nextInt(9999) +"@yopmail.com";
		return emailValue;
	}
	
	public int randomNumber() {
		Random rnd = new Random();
		return rnd.nextInt(9999);
	}
	
	//@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
