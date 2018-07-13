package com.automationframework.stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automationframeworks.cucumber_selenium_testng.WebdriverManager;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
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
    public void after(Scenario scenario) throws IOException {
    	System.out.println("Close Driver");
    	if (scenario.isFailed()) {
    		
    		Reporter.addScreenCaptureFromPath(WebdriverManager.addScreenshot());
    	
    }
    	WebdriverManager.quitDriver();
    }
}
