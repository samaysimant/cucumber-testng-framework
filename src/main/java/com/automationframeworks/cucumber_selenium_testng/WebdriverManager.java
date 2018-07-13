package com.automationframeworks.cucumber_selenium_testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverManager {
	
	private static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static WebDriver intializeDriver() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		driver=new ChromeDriver();
		return driver;
	}

	public static void quitDriver() {
		driver.quit();
	}
}
