package com.automationframeworks.testng.baseclass;

import org.testng.annotations.Test;

import com.automationframeworks.Utility.WebdriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class BaseClass {
	
	WebDriver driver;

	@Test(dataProvider = "dp")
	public void f(Integer n, String s) {

	}

	@BeforeMethod
	public void beforeMethod() {
		
		String browser=System.getProperty("browser");
		
		switch(browser.toLowerCase()) {
		
		case "chrome":
			driver=WebdriverManager.intializeDriver();
		}
			
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 1, "a" }, new Object[] { 2, "b" }, };
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
