package com.automationframework.cucumber.stepdefinitions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.automationframeworks.utilities.ExcelReader;
import com.automationframeworks.utilities.WebdriverManager;
import com.codoid.products.exception.FilloException;
import com.cucumber.listener.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;

public class Hooks

{
	WebDriver driver;

	@Before
	public void before(Scenario scenario) throws MalformedURLException {
		System.out.println("Initiate Driver");
		driver = WebdriverManager.intializeDriver();
		ExcelReader.setTestDataFileName("src/test/resources/testdatacucumber.xlsx");
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
