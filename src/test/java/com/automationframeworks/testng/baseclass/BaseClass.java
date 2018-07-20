package com.automationframeworks.testng.baseclass;

import org.testng.annotations.Test;

import com.automationframeworks.utilities.ExcelReader;
import com.automationframeworks.utilities.Utility;
import com.automationframeworks.utilities.WebdriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codoid.products.exception.FilloException;
import com.google.common.io.Files;
import com.sun.jmx.snmp.Timestamp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public abstract class BaseClass {

	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static final int IMPLICIT_WAIT = 10;
	private static final String fileName = "Screenshot";
	private static Integer fileNumber = 1;
	private static String url;
	private static String reportPath;
	private static String currTimeStamp;
	String dataFilePath;

	@BeforeSuite(alwaysRun = true)
	public void createReporter() throws UnknownHostException {

		reportPath = System.getProperty("user.dir") + "/test-output/testng-extent-report";

		System.out.println("The final report Path is:" + reportPath);
		File reportFolder = new File(reportPath);
		if (!reportFolder.exists()) {
			reportFolder.mkdirs();
		}
		// extent reports
		htmlReporter = new ExtentHtmlReporter(reportPath + "/Report.html");
		htmlReporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		// System.setProperty("reportFilePath", reportPath+"/Report.html");

	}

	@AfterSuite(alwaysRun = true)
	public static void completeReport() {
		System.out.println("----------3-------");
		Utility.setDriver(driver);
		extent.setSystemInfo("Environment", url);
		extent.setSystemInfo("Browser", Utility.getBrowserVersion());
		extent.setSystemInfo("Platform", System.getProperty("os.name"));
		System.out.println("----------4-------");
		String groups = System.getProperty("grouptext");
		if (groups == null) {
			groups = "All";
		}
		extent.setSystemInfo("Test Categories", groups);

		extent.flush();
		System.out.println("----------5-------");
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
			String screenshotPath = addScreenshot();
			System.out.println("------" + screenshotPath);
			if (!screenshotPath.equals("")) {
				test.log(Status.INFO, "Snapshot below: " + test.addScreenCaptureFromPath(screenshotPath));
			}

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("test pass----");
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}

		
      driver.quit();
		//WebdriverManager.quitDriver();
		System.out.println("----------2-------");
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method) throws Exception {

		driver = WebdriverManager.intializeDriver();
		System.out.println("----Current Test Running: " + this.getClass().getSimpleName() + "------------------");
		ExcelReader.setTestDataFileName("src/test/resources/testdatafortestng.xlsx");
		try {
			ExcelReader.feedInputData(method.getName());
		} catch (FilloException e) {

			e.printStackTrace();
		}

	}

	private String addScreenshot() {
		String screenshotName = fileName + fileNumber.toString();
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

			return destinationPath.toString();
		} catch (IOException e) {
		}
		return "";
	}

	public void setBuffer(String key, String data) {
		FileOutputStream fileOut = null;
		FileInputStream fileIn = null;
		try {
			Properties configProperty = new Properties();

			File file = new File("resources/data/buffer.properties");
			fileIn = new FileInputStream(file);
			configProperty.load(fileIn);
			configProperty.setProperty(key, data);
			fileOut = new FileOutputStream(file);
			configProperty.store(fileOut, "sample properties");

		} catch (Exception ex) {

		} finally {

			try {
				fileOut.close();
			} catch (IOException ex) {

			}
		}
	}

	public String getValueFromBuffer(String key) {
		Properties configProperty = new Properties();
		FileInputStream fileIn = null;
		String val = null;

		File file = new File("resources/data/buffer.properties");
		try {
			fileIn = new FileInputStream(file);
			configProperty.load(fileIn);
			val = configProperty.getProperty(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fileIn.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return val;
	}

	public String getCurrentTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timeStampVal = timestamp.toString().replace(" ", "").replace(":", "").replace("-", "").replace(".", "");
		// System.out.println(timeStampVal);
		return timeStampVal;
	}

	public void writeReportBody(String reportPath) {
		File file = new File(System.getProperty("user.dir") + "/test-output/extent-report/ReportBody.txt");
		if (file.exists()) {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// String str = "Hello";
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.write(reportPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	///

}
