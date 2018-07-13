package com.automationframeworks.pageobjects;

import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseNavigation{
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public RegistrationPage goToRegistrationPage() {
		driver.findElement(btnRegister).click();
		return registrationPage==null?registrationPage=new RegistrationPage(driver):registrationPage;
	}
}
