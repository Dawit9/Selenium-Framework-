package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class LoginTest_TestNG {
	

WebDriver driver;
String browser = null;

    @BeforeTest
    public void readConfig() {

     Properties prob = new Properties();
    
     //inputstream//BufferedReader//FileReader//Scanner=====used to read a file 
     
     try { 
    	 InputStream input = new FileInputStream("./Session5/src/main/java/config.properties");
    	 prob.load(input);
    	 browser= prob.getProperty("browser");
    	 System.out.println("Browser used: " + browser);  
     }catch (IOException e){
    	 e.printStackTrace();
    	  
    	 
     }
     
    }
     
	@BeforeMethod 
	public void launchBrowser() {
		
		
		
		if(browser.equalsIgnoreCase("Firfox")) {
			
		
		
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		
		else if (browser.equalsIgnoreCase("Firfox")){
		
		System.setProperty("webdrier.gecho.driver", "Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
		//
		driver.get("https://techfios.com/billing/?ng=admin/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority=1)
	public void logingTest() throws InterruptedException {
		
//		Assert.assertEquals(driver.getTitle(),"Loggin - iBilling", "Wrong page!!");
				
		//storing web element
		//element library
		WebElement USERNAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		WebElement SINGIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[@name='login']"));
		
		
	
		
		USERNAME_FIELD_ELEMENT.clear();
		USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");
		PASSWORD_FIELD_ELEMENT.sendKeys("abc1234");
		SINGIN_BUTTON_ELEMENT.click();
	}
	@Test (priority =2)
	public void addCustomerTest()throws InterruptedException {
		
		

			// Element Library
			By USER_NAME_FIELD = By.id("username");
			By PASSWORD_FIELD = By.id("password");
			By SIGNIN_BUTTON = By.name("login");
			By DASHBOARD_BUTTON = By.xpath("//span[contains(text(), 'Dashboard')]");
			By CUSTOMERS_BUTTON = By.xpath("//span[contains(text(), 'Customers')]");
			By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(), 'Add Customer')]");
			By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(text(),'Add Contact')]");
			By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
			By COMPANY_NAME_FIELD = By.xpath("//select[@id='cid']");
			By EMAIL_FIELD = By.xpath("//input[@id='email']");
			By PHONE_FIELD = By.xpath("//input[@id='phone']");
			By ADDRESS_FIELD = By.xpath("//input[@id='address']");
			By CITY_FIELD = By.xpath("//input[@id='city']");
			By STATE_REGION_FIELD = By.xpath("//input[@id='state']");
			By ZIP_FIELD = By.xpath("//input[@id='zip']");
			By SUBMIT_BUTTON = By.xpath("//button[@class='btn btn-primary']");
			By LIST_CONTACTS_BUTTON = By.xpath("//a[contains(text(),'List Contacts')]");
			
			
			//LoginDate
			String loginID = "demo@techfios.com";
			String password = "abc123";
			
			//Test Data or Mock Data 
			
			String fullName = "Test October";
			String companyName = "Techfios";
			String emailAddress = "techOctober@gmail.com";
			
			driver.findElement(USER_NAME_FIELD).sendKeys(loginID);
			driver.findElement(PASSWORD_FIELD).sendKeys(password);
			driver.findElement(SIGNIN_BUTTON).click();
			
			//add customer 
			
			driver.findElement(CUSTOMERS_BUTTON).click();
			
			
			driver.findElement(ADD_CUSTOMER_BUTTON).click();
			
			//explicit wait 
			waitForElment(driver, 5,  ADD_CUSTOMER_BUTTON); 
			driver.findElement(ADD_CUSTOMER_BUTTON).click();
			
			waitForElment(driver, 5 , ADD_CUSTOMER_BUTTON);
			//generate random no
			Random rnd = new Random();
			int generatedNo = rnd.nextInt(999);
			
			
			driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + generatedNo);
			
			//dropDown
			
			
			Select sel = new Select (driver.findElement(COMPANY_NAME_FIELD));
			sel.selectByVisibleText(companyName);
	}
					
	
		
	public void waitForElment(WebDriver driver, int timeInSeconds, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}


		@AfterMethod 

	public void tearDown() {
		driver.close();
		driver.quit();
		
		}
	
	}


