package com.automationframeworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automationframeworks.utilities.Utility;
import com.cucumber.listener.Reporter;

public class RegistrationPage extends BaseNavigation{
	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By userName= By.name("user_login");
	By email = By.name("user_email");
	By btnRegister = By.xpath("//input[@value='Register']");
	
	public void enterUserName(String userName) {
		Utility.repostWait(this.userName);
		driver.findElement(this.userName).sendKeys(userName);
	//	Reporter.addStepLog("Entered UserName");
	}
	
	public void enterEmail(String emailadd) {
		Utility.repostWait(email);
		driver.findElement(email).sendKeys(emailadd);
	}
	
	public void clickRegister() {
		Utility.repostWait(btnRegister);
		driver.findElement(this.btnRegister).click();
	}
	
	
}
