package com.automationframeworks.cucumber_selenium_testng;

import java.io.File;
import java.net.InetAddress;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import com.google.common.io.Files;

public class WebdriverManager {
	
	private static WebDriver driver;
	private static final String fileName = "Screenshot";
	private static Integer fileNumber = 1;
	
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
	
	public static String addScreenshot() {
		String screenshotName = fileName + fileNumber.toString();
		String reportPath=System.getProperty("user.dir")+"/test-output/extent-reports";
		++fileNumber;
		System.out.println("Taking screeenshot:" + screenshotName);
		try {
			// This takes a screenshot from the driver at save it to the specified location
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			// Building up the destination path for the screenshot to save
			File screenshotFolderPath = new File(reportPath + "/screenshots/");
			File destinationPath = new File(reportPath + "/screenshots/" + screenshotName + ".png");

			// create the directory if it doesnt exist
			if (!screenshotFolderPath.exists()) {
				screenshotFolderPath.mkdirs();
			}
			Files.copy(sourcePath, destinationPath);
			System.out.println("------+++++"+System.getProperty("reportPath"));
			if (System.getProperty("reportPath") != null) {
		//	return	"http://"+InetAddress.getLocalHost().getHostAddress() + ":8082/ExecutionReport" + currTimeStamp+ "/screenshots/" + screenshotName + ".png" ;
			
			}else {
				System.out.println("Screenshot :" + destinationPath.toString());
				return destinationPath.toString();
			}
			// Copy taken screenshot from source location to destination location
		
			//System.out.println("Screenshot :" + destinationPath.toString());
			// return System.getProperty("user.dir") +
			// "/test-output/extent-report/screenshots/" + screenshotName + ".png";
			

			// This attach the specified screenshot to the test
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
