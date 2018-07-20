package com.automationframeworks.testng.tests;

import org.testng.annotations.Test;

import com.automationframeworks.pageobjects.HomePage;
import com.automationframeworks.pageobjects.LoginPage;
import com.automationframeworks.pageobjects.RegistrationPage;
import com.automationframeworks.testng.baseclass.BaseClass;
import com.automationframeworks.utilities.DataHandler;
import com.automationframeworks.utilities.WebdriverManager;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class StoreTest extends BaseClass {

	HomePage homePage = new HomePage(driver);
	LoginPage loginPage;
	RegistrationPage registrationPage;

	@Test
	public void registerUser() {
		test = extent.createTest("registerUser", "Registers an user");
		homePage = new HomePage(driver);
		driver.get("http://store.demoqa.com/");
		loginPage = homePage.goToLoginPage();
		registrationPage = loginPage.goToRegistrationPage();
		registrationPage.enterUserName(DataHandler.getDataFromMap("username"));
		registrationPage.enterEmail(DataHandler.getDataFromMap("email"));
		registrationPage.clickRegister();

		test.log(Status.INFO, MarkupHelper.createLabel("Resigsterd an user", ExtentColor.BLUE));
	}

	@Test
	public void createUser() {
		test = extent.createTest("createUser", "Creates an user");
		homePage = new HomePage(driver);
		driver.get("http://store.demoqa.com/");
		loginPage = homePage.goToLoginPage();
		loginPage.enterUserName(DataHandler.getDataFromMap("username"));
		loginPage.enterPassword(DataHandler.getDataFromMap("password"));
		loginPage.clickLogin();
		Assert.assertEquals(loginPage.getErrorMsg(), "password is incorrect");

		test.log(Status.INFO, MarkupHelper.createLabel("Created an user", ExtentColor.BLUE));
	}

}
