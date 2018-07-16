package com.automationframeworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

public class RegistrationPage extends BaseNavigation{
	WebDriver driver;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By userName= By.name("user_login");
	By email = By.name("user_email2");
	By btnRegister = By.xpath("//input[@value='Register']");
	
	public void enterUserName(String userName) {
		driver.findElement(this.userName).sendKeys(userName);
		Reporter.addStepLog("Entered UserName");
	}
	
	public void enterEmail(String email) {
		driver.findElement(this.email).sendKeys(email);
	}
	
	public void clickRegister() {
		driver.findElement(this.btnRegister).click();
	}
	
	
}