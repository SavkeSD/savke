package Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BaseTest.BaseTestEtherscan;

public class TestEtherscan extends BaseTestEtherscan{
	
	@BeforeMethod
	public void pageSetup() {
		driver.manage().window().maximize();
		driver.navigate().to("https://etherscan.io/register");
	}
	
	/*@Test (priority = 10)
	public void incorectUsername() throws InterruptedException {
		for(int i = 1; i <= 4; i++ ) {
		Actions actions = new Actions(driver);
		logIn.setUsername(excel.getStringData("Sheet2", i, 0));
		System.out.println("Username is : " + excel.getStringData("Sheet2", i, 0));
		logIn.setEmail(excel.getStringData("Sheet2", 1, 4));
		logIn.setPassword(excel.getStringData("Sheet2", 1, 5));
		logIn.setConfirmPassword(excel.getStringData("Sheet2", 1, 5));
		//Thread.sleep(5000);
		actions.moveToElement(logIn.getAgreeTerms()).click().perform();
		actions.moveToElement(logIn.getButtonCreate()).click().perform();
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("https://etherscan.io/register"));
		assertTrue(logIn.getErrorUsername().getText().contains("Username is invalid"));
		assertTrue(logIn.getButtonCreate().isDisplayed());}
	}*/
	
	/*@Test (priority = 20)
	public void incorectEmail() throws InterruptedException {
		for(int i = 1; i <2 ; i++ ) {
		Actions actions = new Actions(driver);
		logIn.setUsername(excel.getStringData("Sheet2", 1, 3));
		logIn.setEmail(excel.getStringData("Sheet2", i, 1));
		logIn.setPassword(excel.getStringData("Sheet2", 1, 5));
		logIn.setConfirmPassword(excel.getStringData("Sheet2", 1, 5));
		//Thread.sleep(5000);
		actions.moveToElement(logIn.getAgreeTerms()).click().perform();
		actions.moveToElement(logIn.getButtonCreate()).click().perform();
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("https://etherscan.io/register"));
		assertTrue(logIn.getErrorEmail().getText().contains("Please enter a valid email address"));
		assertTrue(logIn.getButtonCreate().isDisplayed());}
	}*/
	
//	@Test (priority = 30)
//	public void incorectConfirmPass() throws InterruptedException {
//		for(int i = 1; i < 2; i++ ) {
//		Actions actions = new Actions(driver);
//		logIn.setUsername(excel.getStringData("Sheet2", 1, 3));
//		logIn.setEmail(excel.getStringData("Sheet2", 1, 4));
//		logIn.setPassword(excel.getStringData("Sheet2", 1, 5));
//		logIn.setConfirmPassword(excel.getStringData("Sheet2", i, 2));
//		//Thread.sleep(5000);
//		actions.moveToElement(logIn.getAgreeTerms()).click().perform();
//		actions.moveToElement(logIn.getButtonCreate()).click().perform();
//		String currentUrl = driver.getCurrentUrl();
//		assertTrue(currentUrl.contains("https://etherscan.io/register"));
//		assertTrue(logIn.getErrorConfirmPass().getText().contains("Password does not match, please check again"));
//		assertTrue(logIn.getButtonCreate().isDisplayed());}
//	}
	
//	@Test (priority = 40)
//	public void invalidPass() throws InterruptedException {
//		for(int i = 1; i < 2; i++ ) {
//		Actions actions = new Actions(driver);
//		logIn.setUsername(excel.getStringData("Sheet2", 1, 3));
//		logIn.setEmail(excel.getStringData("Sheet2", 1, 4));
//		logIn.setPassword(excel.getStringData("Sheet2", i, 6));
//		logIn.setConfirmPassword(excel.getStringData("Sheet2", i, 6));
//		//Thread.sleep(5000);
//		actions.moveToElement(logIn.getAgreeTerms()).click().perform();
//		actions.moveToElement(logIn.getButtonCreate()).click().perform();
//		String currentUrl = driver.getCurrentUrl();
//		assertTrue(currentUrl.contains("https://etherscan.io/register"));
//		assertTrue(logIn.getInvalidPass().getText().contains("Your password must be at least 5 characters long"));
//		assertTrue(logIn.getButtonCreate().isDisplayed());}
//	}
	
	@Test (priority = 40)
	public void isTermsAccepted () throws InterruptedException {
		
		Actions actions = new Actions(driver);
		logIn.setUsername(excel.getStringData("Sheet2", 1, 3));
		logIn.setEmail(excel.getStringData("Sheet2", 1, 4));
		logIn.setPassword(excel.getStringData("Sheet2", 1, 5));
		logIn.setConfirmPassword(excel.getStringData("Sheet2", 1, 5));
		//Thread.sleep(5000);
		actions.moveToElement(logIn.getAgreeTerms()).click().perform();
		actions.moveToElement(logIn.getButtonCreate()).click().perform();
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.contains("https://etherscan.io/register"));
		assertTrue(logIn.getButtonCreate().isDisplayed());
		assertTrue(logIn.getAgreeTerms().isSelected());
	
	}
	
	
	
	
	
	

}
