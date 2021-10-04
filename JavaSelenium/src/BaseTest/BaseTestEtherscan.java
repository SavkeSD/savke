package BaseTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Pages.EtherscanLogIn;

public class BaseTestEtherscan {
	public WebDriver driver;
	public EtherscanLogIn logIn;
	public ExcelReader excel;
	
	@BeforeClass
	public void setUp() throws IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe" );
		driver = new ChromeDriver();
		logIn = new EtherscanLogIn (driver);
		excel = new ExcelReader ("C:\\Users\\Savke\\Desktop\\OriginTrail.xlsx");
	}
 
	@AfterClass
	public void tearDown() {
		//driver.close();
		//driver.quit();
	}
}
