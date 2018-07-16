package com.automationframeworks.suiterunners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automationframeworks.Utility.WebdriverManager;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/FeaturesTest", format = { "pretty", "html:target/site/cucumber-pretty",
		"rerun:target/rerun.txt",
		"json:target/cucumber1.json" }, glue = { "com/automationframework/stepdefinitions" }, plugin = {
				"com.cucumber.listener.ExtentCucumberFormatter:test-output/extent-reports/report.html" }
        ,tags= {"@smoke"}         
		)
public class SuiteRunnerTest {
	private TestNGCucumberRunner testNGCucumberRunner;

	
	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}
	


	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		Reporter.loadXMLConfig("src/test/resources/extent-config.xml");
		
		// Reporter.lo.loadXMLConfig(new
		// File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
		testNGCucumberRunner.finish();
	}

	
	
}
