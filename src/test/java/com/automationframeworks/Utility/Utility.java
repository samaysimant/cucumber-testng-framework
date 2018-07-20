package com.automationframeworks.Utility;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationframework.cucumber.stepdefinitions.Hooks;

public class Utility {

	private static WebDriver waitDriver;

	// this stuff probably should move to a model at some point
	private static By loadingSpinner = By.cssSelector("div.forceDotsSpinner");
	private static By loadingGlass = By.cssSelector("div.modal-glass");
	private static By spinnerContainer = By.cssSelector("div.slds-spinner_container");

	// Get the driver
	public static void setDriver(WebDriver driver) {
		waitDriver = driver;
	}

	/*
	 * method first checks for the staleness of an element which was an issue on DOM
	 * reposts it then checks that JS document ready status is complete, followed by
	 * a check of the invisibility of the loading elements. it then checks for the
	 * presence and visibility of the locator targeted.
	 */
	public static void repostWait(By locator) {
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
				highlightElement(waitDriver.findElement(locator));
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
		waitDriver.manage().timeouts().implicitlyWait(WebdriverManager.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	/*
	 * method first checks for the staleness of an element which was an issue on DOM
	 * reposts it then checks that JS document ready status is complete, followed by
	 * a check of the invisibility of the loading elements. it then checks for the
	 * presence and visibility of the locator targeted.
	 */
	public static void fastWait(By locator) {
		waitDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			// try and wait for the staleness of the element during repost
			(new WebDriverWait(waitDriver, 0, 200))
					.until(ExpectedConditions.stalenessOf(waitDriver.findElement(locator)));
		} catch (Exception e) {
			// System.out.println("element didnt go stale");
		} finally {
			// wait for presence and visibility of the locator
			(new WebDriverWait(waitDriver, 20)).until(ExpectedConditions.presenceOfElementLocated(locator));
			(new WebDriverWait(waitDriver, 20)).until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		waitDriver.manage().timeouts().implicitlyWait(WebdriverManager.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

	/*
	 * Wait for various forms of load spinners and modals to disappear locators
	 * probably should be moved to a model somewhere
	 */
	public static void waitForLoadScreenInvisiblity() {

		WebDriverWait wait = new WebDriverWait(waitDriver, 2);
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

	public static void waitForElementToNotExist(By locator) {
		WebDriverWait wait = new WebDriverWait(waitDriver, 2);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			// System.out.println("exception waiting for load spinners to become
			// invisible");
		}
	}

	/*
	 * Wait for JS ready state
	 */
	private static void waitUntilJSReady() {
		WebDriverWait wait = new WebDriverWait(waitDriver, 5);
		JavascriptExecutor jsExec = (JavascriptExecutor) waitDriver;

		// Wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) waitDriver)
				.executeScript("return document.readyState").toString().equals("complete");
	}

	/*
	 * moves to an element
	 */
	public static void moveToElement(By locator) {
		Actions actions = new Actions(waitDriver);
		actions.moveToElement(waitDriver.findElement(locator));
		actions.sendKeys("");
		actions.build().perform();
	}

	public static String getRandomString(int length) {
		String SEEDCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder rndStringBuilder = new StringBuilder();
		Random rnd = new Random();
		while (rndStringBuilder.length() < length) {
			int index = (int) (rnd.nextFloat() * SEEDCHARS.length());
			rndStringBuilder.append(SEEDCHARS.charAt(index));
		}
		String rndStr = rndStringBuilder.toString();
		return rndStr;
	}

	public static String getBrowserVersion() {
		Capabilities cap = ((RemoteWebDriver) waitDriver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		String version = cap.getVersion().toString();
		return browserName + " " + version;
	}

	public static String getOS() {
		Capabilities cap = ((RemoteWebDriver) waitDriver).getCapabilities();
		return cap.getPlatform().toString();
	}

	/*
	 * this method is primarily built for demonstration purposes. it highlights the
	 * element being targeted with a yellow highlight and red border
	 */
	public static void highlightElement(By locator) {
		WebElement element = waitDriver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) waitDriver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px black');", element);
		dumbWait(50);
		// js.executeScript("arguments[0].setAttribute('style','background: none;');",
		// element);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}

	public static void highlightElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) waitDriver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px black');", element);
		dumbWait(50);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}

	/*
	 * currently in use for demonstration purposes to slow tests on verifications
	 */
	public static void dumbWait(int timeoutMS) {
		try {
			Thread.sleep(timeoutMS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void scrollToCentre(WebDriver driver) {
		JavascriptExecutor jsexec = (JavascriptExecutor) driver;
		;
		jsexec.executeScript("window.scrollTo( 0, screen.height/2 );");
	}
}