package com.automationframeworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseNavigation {

	public WebDriver driver;
	RegistrationPage registrationPage;
	LoginPage loginPage;

	public BaseNavigation(WebDriver driver) {
		this.driver = driver;
	}

	public BaseNavigation() {

	}

	By linkMyAccount = By.xpath("//a[text()='My Account']");
	By btnRegister = By.xpath("//a[text()='Register']");

	public LoginPage goToLoginPage() {
		driver.findElement(linkMyAccount).click();
		return loginPage==null?loginPage=new LoginPage(driver):loginPage;
	}

	public RegistrationPage goToRegistrationPage() {
		driver.findElement(btnRegister).click();
		return registrationPage==null?registrationPage=new RegistrationPage(driver):registrationPage;
	}

}
