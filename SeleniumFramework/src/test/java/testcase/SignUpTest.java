package testcase;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;






import util.TestUtil;

public class SignUpTest {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "E:/Drivers/ChromeDriver/NewDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://cai.tools.sap/");
		
	}
	
	@DataProvider
	public Object[][] getData(){
		Object data[][] = TestUtil.getTestData("Login");
		return data;
	}
	
	@Test(dataProvider = "getData")
	public void signupForm(String Firstname, String Lastname, String Mail,String Password, String RePassword) throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[3]/div[3]/a/div/div")).click();
		driver.switchTo().frame("IDS_UI_Window");
		driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys(Firstname);
		driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys(Lastname);
		driver.findElement(By.xpath("//*[@id='mail']")).sendKeys(Mail);
		driver.findElement(By.xpath("//*[@id='newPasswordInput']")).sendKeys(Password);
		driver.findElement(By.xpath("//*[@id='retypeNewPasswordInput']")).sendKeys(RePassword);
		System.out.println("Reistration form filled with required inputs");

		//checkbox #1
		WebElement checkboxFirst = driver.findElement(By.id("pdAccept"));
		JavascriptExecutor jseOne = (JavascriptExecutor)driver;
		jseOne.executeScript("arguments[0].click()", checkboxFirst);
		
		//checkbox #2
		WebElement checkboxSecond = driver.findElement(By.id("touAccept"));
		JavascriptExecutor jseTwo = (JavascriptExecutor)driver;
		jseTwo.executeScript("arguments[0].click()", checkboxSecond);
		
		//register button
		WebElement registerButton = driver.findElement(By.id("sapStoreRegisterFormSubmit"));
		JavascriptExecutor jseThree = (JavascriptExecutor)driver;
		jseTwo.executeScript("arguments[0].click()", registerButton);
		System.out.println("Registration is done and activation link is sent to the user's mail");
	}
	
	@AfterMethod
	public void tearDown(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
