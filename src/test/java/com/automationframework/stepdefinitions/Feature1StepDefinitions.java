package com.automationframework.stepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Feature1StepDefinitions {
	@Given("^I am in home page$")
	public void i_am_in_home_page() throws Throwable {
	  System.out.println("i am in given");
	}

	@Given("^I cliked My account link to navigate to signup page$")
	public void i_cliked_My_account_link_to_navigate_to_signup_page() throws Throwable {
		 System.out.println("i am in given2");
	}

	@When("^I click Register link$")
	public void i_click_Register_link() throws Throwable {
		 System.out.println("i am in when");
	}

	@Then("^Registration page must be displayed$")
	public void registration_page_must_be_displayed() throws Throwable {
		 System.out.println("i am in then");
	}

	@When("^I entered username and email$")
	public void i_entered_username_and_email() throws Throwable {
		 System.out.println("i am in when2");
	}

	@When("^clicked on Register button$")
	public void clicked_on_Register_button() throws Throwable {
		 System.out.println("i am in when3");
	}

	@Then("^I must get Registration confirmation message$")
	public void i_must_get_Registration_confirmation_message() throws Throwable {
		 System.out.println("i am in then2");
	}
	
	
	@When("^I enter my credentials and pressed submit$")
	public void i_enter_my_credentials_and_pressed_submit() throws Throwable {
	    System.out.println("Entered username and password");
	}

	@Then("^I should be at landing page$")
	public void i_should_be_at_landing_page() throws Throwable {
	  System.out.println("I am at landing page");
	}
}
