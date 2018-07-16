package com.automationframework.stepdefinitions;


import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	
	static WebDriver driver;

	public Utilities(WebDriver driver) {
		super();
		this.driver = driver;
	}
	private static By loadingSpinner = By.cssSelector("div.forceDotsSpinner");
	private static By loadingGlass = By.cssSelector("div.modal-glass");
	private static By spinnerContainer = By.cssSelector("div.slds-spinner_container");

	public static void repostWait(By locator) {
		WebDriver waitDriver=driver;
		waitDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			// try and wait for the staleness of the element during repost
			(new WebDriverWait(waitDriver, 0, 600))
					.until(ExpectedConditions.stalenessOf(waitDriver.findElement(locator)));
		} catch (Exception e) {

		} finally {
			// wait for javascript completion
			// waitUntilJSReady();
			try {
				// wait for loading table to disappear
				waitForLoadScreenInvisiblity();

				// wait for presence and visibility of the locator
				(new WebDriverWait(waitDriver, 20)).until(ExpectedConditions.presenceOfElementLocated(locator));
				(new WebDriverWait(waitDriver, 20)).until(ExpectedConditions.visibilityOfElementLocated(locator));
				(new WebDriverWait(waitDriver, 20)).until(ExpectedConditions.elementToBeClickable(locator));
			} catch (Exception e2) {

			}
			try {
				(new WebDriverWait(waitDriver, 20))
						.until(ExpectedConditions.visibilityOf(waitDriver.findElement(locator)));
			} catch (Exception e3) {

			}

			try {
				highlightElement(locator);
			} catch (Exception e) {

			}

			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(waitDriver)

						.withTimeout(60, TimeUnit.SECONDS)

						.pollingEvery(2, TimeUnit.SECONDS)

						.ignoring(NoSuchElementException.class);

				wait.until(new Function<WebDriver, WebElement>() {

					public WebElement apply(WebDriver driver) {

						return driver.findElement(locator);
					}

				});
			} catch (Exception e3) {

			}

		}
		waitDriver.manage().timeouts().implicitlyWait(BaseTestSuite.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	
	public static void waitForLoadScreenInvisiblity() {

		WebDriverWait wait = new WebDriverWait(driver, 2);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingGlass));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerContainer));
		} catch (Exception e) {
			// System.out.println("exception waiting for load spinners to become
			// invisible");
		}

	}
	
	public static void highlightElement(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px black');", element);
		dumbWait(50);
		// js.executeScript("arguments[0].setAttribute('style','background: none;');",
		// element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}

	public static void dumbWait(int timeoutMS) {
		try {
			Thread.sleep(timeoutMS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
