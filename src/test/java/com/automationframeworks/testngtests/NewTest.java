package com.automationframeworks.testngtests;

import org.testng.annotations.Test;

import com.automationframeworks.Utility.WebdriverManager;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class NewTest {
	WebDriver driver;
  @Test
  public void f() {
	  driver.get("https://www.google.com");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Initiate Driver");
  	driver=WebdriverManager.intializeDriver();
  }

  @AfterMethod
  public void afterMethod() {
	  WebdriverManager.quitDriver(); 
  }

}
