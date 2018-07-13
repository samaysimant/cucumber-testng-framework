package com.automationframework.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.automationframeworks.cucumber_selenium_testng.WebdriverManager;

import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Hello world!
 *
 */
public class BaseTest 


{
	WebDriver driver;
	
	
    @Before
    public void before() {
    	System.out.println("Initiate Driver");
    	driver=WebdriverManager.intializeDriver();
    }
    
    @After
    public void after() {
    	System.out.println("Close Driver");
    	WebdriverManager.quitDriver();
    }
}
