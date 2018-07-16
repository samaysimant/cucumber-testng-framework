package com.automationframeworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.automationframeworks.Utility.Utility;
import com.cucumber.listener.Reporter;



public class LoginPage extends BaseNavigation{
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	By userName=By.id("log");
	By password=By.id("pwd");
	By btnLogin = By.id("login");
	By errorMsg =By.cssSelector(".response");
	
	
	public RegistrationPage goToRegistrationPage() {
		Utility.repostWait(btnRegister);
		driver.findElement(btnRegister).click();
		return registrationPage==null?registrationPage=new RegistrationPage(driver):registrationPage;
	}
	
	public void enterUserName(String userNameVal) {
		Utility.repostWait(userName);
		driver.findElement(userName).sendKeys(userNameVal);
		Reporter.addStepLog("Filled UserName");
		
	}
	public void enterPassword(String passwordVal) {
		Utility.repostWait(password);
		driver.findElement(password).sendKeys(passwordVal);
		Reporter.addStepLog("Filled Password");
		
	}
	
	public void clickLogin() {
		Utility.repostWait(btnLogin);
		driver.findElement(btnLogin).click();
		Reporter.addStepLog("Clicked on Login");
		
	}
	
	public String getErrorMsg() {
		Utility.repostWait(errorMsg);
		return driver.findElement(errorMsg).getText();
	}
}
