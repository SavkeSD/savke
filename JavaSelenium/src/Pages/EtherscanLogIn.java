package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EtherscanLogIn {
	WebDriver driver;
	WebElement username;
	WebElement email;
	WebElement password;
	WebElement confirmPassword;
	WebElement agreeTerms;
	WebElement robot;
	WebElement buttonCreate;
	WebElement errorUsername;
	WebElement errorEmail;
	WebElement errorConfirmPass;
	WebElement invalidPass;
	
	public EtherscanLogIn (WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getUsername() {
		return driver.findElement(By.id("ContentPlaceHolder1_txtUserName"));
	}
	
	public void setUsername(String userName) {
		this.getUsername().clear();
		this.getUsername().sendKeys(userName);
	}

	public WebElement getEmail() {
		return driver.findElement(By.id("ContentPlaceHolder1_txtEmail"));
	}
	
	public void setEmail (String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	public WebElement getPassword() {
		return driver.findElement(By.id("ContentPlaceHolder1_txtPassword"));
	}
	
	public WebElement getConfirmPassword() {
		return driver.findElement(By.id("ContentPlaceHolder1_txtPassword2"));
	}
	
	public void setPassword (String password) {
		this.getPassword().clear();
		this.getPassword().sendKeys(password);
	}
	
	public void setConfirmPassword (String password) {
		this.getConfirmPassword().clear();
		this.getConfirmPassword().sendKeys(password);
	}

	public WebElement getAgreeTerms() {
		return driver.findElement(By.id("ContentPlaceHolder1_MyCheckBox"));
	}
	
	public void clickAgreeTerms () {
		this.getAgreeTerms().click();
	}

	public WebElement getRobot() {
		return driver.findElement(By.xpath("//*[@id=\"recaptcha-anchor\"]/div[1]"));
	}
	
	public void clickRobot() {
		this.getRobot().click();
	}

	public WebElement getButtonCreate() {
		return driver.findElement(By.cssSelector(".btn.btn-sm.btn-primary"));
	}
	
	public void clickButtonCreate() {
		this.getButtonCreate().click();
	}
	
	public WebElement getErrorUsername () {
		return driver.findElement(By.id("ContentPlaceHolder1_txtUserName-error"));
	}
	
	public WebElement getErrorEmail () {
		return driver.findElement(By.id("ContentPlaceHolder1_txtEmail-error"));
	}
	
	public WebElement getErrorConfirmPass () {
		return driver.findElement(By.id("ContentPlaceHolder1_txtPassword2-error"));
	}
	
	public WebElement getInvalidPass() {
		return driver.findElement(By.id("ContentPlaceHolder1_txtPassword-error"));
	}

	
}
