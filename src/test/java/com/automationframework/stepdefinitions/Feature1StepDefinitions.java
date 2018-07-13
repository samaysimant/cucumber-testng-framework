package com.automationframework.stepdefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automationframeworks.cucumber_selenium_testng.WebdriverManager;
import com.automationframeworks.pageobjects.HomePage;
import com.automationframeworks.pageobjects.LoginPage;
import com.automationframeworks.pageobjects.RegistrationPage;
import com.cucumber.listener.Reporter;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Feature1StepDefinitions {
	
	WebDriver driver;
	HomePage homePage=new HomePage(driver);
	LoginPage loginPage;
	RegistrationPage registrationPage ;
	
	@Given("^I am in home page$")
	public void i_am_in_home_page() throws Throwable {
		driver=WebdriverManager.getDriver();
		homePage=new HomePage(driver);
	  driver.get("http://store.demoqa.com/");
	}

	@Given("^I cliked My account link to navigate to signup page$")
	public void i_cliked_My_account_link_to_navigate_to_signup_page() throws Throwable {
		loginPage= homePage.goToLoginPage();
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
		registrationPage.enterEmail("samaysimant123@gmail.com");
		registrationPage.enterUserName("samaysimant");
	
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
	    System.out.println("Entered username and password");
	    Reporter.addStepLog("Entered UserName and Password------");
	}

	@Then("^I should be at landing page$")
	public void i_should_be_at_landing_page() throws Throwable {
	  System.out.println("I am at landing page");
	  Assert.assertTrue(false);
	}
}
