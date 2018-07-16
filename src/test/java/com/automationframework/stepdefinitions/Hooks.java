package com.automationframework.stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.automationframeworks.Utility.ExcelReader;
import com.automationframeworks.Utility.WebdriverManager;
import com.codoid.products.exception.FilloException;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Hello world!
 *
 */
public class Hooks 



{
	WebDriver driver;
	public static final long IMPLICIT_WAIT=30;
	
    @Before
    public void before(Scenario scenario) {
    	System.out.println("Initiate Driver");
    	driver=WebdriverManager.intializeDriver();
    	System.out.println("Current scenario:"+scenario.getName());
    	try {
			ExcelReader.feedInputData(scenario.getName());
		} catch (FilloException e) {
			
			e.printStackTrace();
		}
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
