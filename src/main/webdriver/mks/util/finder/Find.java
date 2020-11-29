package mks.util.finder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

public class Find {

	private long DRIVER_IMPLICIT_WAIT_SEC = 0;
	private long DRIVER_IMPLICIT_WAIT_POLL_INTERVAL_MS = 500;

	public Wait<WebDriver> DEFAULT_WAIT = null;
	public static Wait<WebDriver> NO_WAIT = null;

	public Find(WebDriver driver, long implicitWait) {
		DRIVER_IMPLICIT_WAIT_SEC = implicitWait;
		DEFAULT_WAIT = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(DRIVER_IMPLICIT_WAIT_SEC))
				.pollingEvery(Duration.ofMillis(DRIVER_IMPLICIT_WAIT_POLL_INTERVAL_MS))
				.ignoreAll(Arrays.asList(NoSuchElementException.class));
		NO_WAIT = new FluentWait<WebDriver>(driver).withTimeout(Duration.ZERO);
	}

	public Find(WebDriver driver) {
		this(driver, 0);
	}

	public WebElement element(By locator) {
		System.out.println("Finding WebElement for locator " + locator);
		try {
			WebElement result = DEFAULT_WAIT.until(ExpectedConditions.presenceOfElementLocated(locator));
			System.out.println("Found WebElement for locator " + locator);
			return result;
		} catch (TimeoutException e) {
			String exceptionString = String.format("No WebElement found for locator: %s", locator);
			System.out.println(exceptionString);
			throw new NoSuchElementException(exceptionString, e);
		}
	}

	public List<WebElement> elements(By locator) {
		System.out.println("Finding All WebElement(s) for locator " + locator);
		List<WebElement> elements;
		try {
			elements = DEFAULT_WAIT.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			elements = new ArrayList<WebElement>();
		}

		if (elements.isEmpty()) {
			System.out.println("Found No WebElement(s) for locator {} " + locator);
		} else {
			System.out.println("Found WebElement(s) for locator {} " + locator);
		}
		return elements;
	}

	public WebElement child(WebElement parentWebElement, By locator) {
		System.out.println("Finding child WebElement for locator " + locator);
		try {
			WebElement result = DEFAULT_WAIT
					.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentWebElement, locator));
			System.out.println("Found child WebElement for locator " + locator);
			return result;
		} catch (TimeoutException e) {
			throw new NoSuchElementException(String
					.format("No WebElement found for the givern parent, and the given child locator: %s", locator), e);
		}
	}

	public List<WebElement> children(By parentLocator, By locator) {
		System.out.println("Finding child WebElement for locator " + locator);
		try {
			List<WebElement> result = DEFAULT_WAIT
					.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parentLocator, locator));
			System.out.println("Found child WebElement for locator " + locator);
			return result;
		} catch (TimeoutException e) {
			throw new NoSuchElementException(String
					.format("No WebElement found for the givern parent, and the given child locator: %s", locator), e);
		}
	}
}
