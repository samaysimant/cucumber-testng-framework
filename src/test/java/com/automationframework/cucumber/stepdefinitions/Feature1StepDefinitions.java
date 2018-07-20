package com.automationframework.cucumber.stepdefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automationframeworks.pageobjects.HomePage;
import com.automationframeworks.pageobjects.LoginPage;
import com.automationframeworks.pageobjects.RegistrationPage;
import com.automationframeworks.utilities.DataHandler;
import com.automationframeworks.utilities.WebdriverManager;
import com.cucumber.listener.Reporter;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Feature1StepDefinitions {

	WebDriver driver;
	HomePage homePage = new HomePage(driver);
	LoginPage loginPage;
	RegistrationPage registrationPage;

	@Given("^I am in home page$")
	public void i_am_in_home_page() throws Throwable {
		driver = WebdriverManager.getDriver();
		homePage = new HomePage(driver);
		driver.get("http://store.demoqa.com/");
	}

	@Given("^I cliked My account link to navigate to signup page$")
	public void i_cliked_My_account_link_to_navigate_to_signup_page() throws Throwable {
		loginPage = homePage.goToLoginPage();
	}

	@When("^I click Register link$")
	public void i_click_Register_link() throws Throwable {
		registrationPage = loginPage.goToRegistrationPage();
	}

	@Then("^Registration page must be displayed$")
	public void registration_page_must_be_displayed() throws Throwable {
		System.out.println("i am in then");
	}

	@When("^I entered username and email$")
	public void i_entered_username_and_email() throws Throwable {

		registrationPage.enterUserName(DataHandler.getDataFromMap("username"));
		registrationPage.enterEmail(DataHandler.getDataFromMap("email"));

	}

	@When("^clicked on Register button$")
	public void clicked_on_Register_button() throws Throwable {
		registrationPage.clickRegister();
	}

	@Then("^I must get Registration confirmation message$")
	public void i_must_get_Registration_confirmation_message() throws Throwable {
		System.out.println("i am in then2");
	}

	@When("^I enter my credentials and pressed submit$")
	public void i_enter_my_credentials_and_pressed_submit() throws Throwable {
		loginPage.enterUserName(DataHandler.getDataFromMap("username"));
		loginPage.enterPassword(DataHandler.getDataFromMap("password"));
		loginPage.clickLogin();
	}

	@Then("^I should be at landing page$")
	public void i_should_be_at_landing_page() throws Throwable {
		Assert.assertEquals(loginPage.getErrorMsg(),"password is incorrect");

	}
}
