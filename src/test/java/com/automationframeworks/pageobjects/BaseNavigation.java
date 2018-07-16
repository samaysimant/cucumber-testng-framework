package com.automationframeworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automationframeworks.Utility.Utility;

public class BaseNavigation {

	public WebDriver driver;
	RegistrationPage registrationPage;
	LoginPage loginPage;

	public BaseNavigation(WebDriver driver) {
		this.driver = driver;
		Utility.setDriver(driver);
	}

	public BaseNavigation() {

	}

	By linkMyAccount = By.xpath("//a[text()='My Account']");
	By btnRegister = By.xpath("//a[text()='Register']");

	public LoginPage goToLoginPage() {
		Utility.repostWait(linkMyAccount);
		driver.findElement(linkMyAccount).click();
		return loginPage==null?loginPage=new LoginPage(driver):loginPage;
	}

	

}
