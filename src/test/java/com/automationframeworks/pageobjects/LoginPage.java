package com.automationframeworks.pageobjects;

import org.openqa.selenium.WebDriver;


import com.automationframeworks.Utility.Utility;



public class LoginPage extends BaseNavigation{
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public RegistrationPage goToRegistrationPage() {
		Utility.repostWait(btnRegister);
		driver.findElement(btnRegister).click();
		return registrationPage==null?registrationPage=new RegistrationPage(driver):registrationPage;
	}
}
