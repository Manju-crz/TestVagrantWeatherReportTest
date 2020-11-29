package mks.util.connectors;

import org.openqa.selenium.WebDriver;

import mks.util.browser.ChromeInstantiator;
import mks.util.connectors.exceptions.ConnectionException;

public class DriverConnector {
	
	private final String CHROME_VERSION = "3.3";
	
	
	private String defaultBrowser = "chrome";
	private String defaultBrowserVersion = null;
	
	private WebDriver driver = null;
	
	
	public DriverConnector() {
		
	}
	
	public DriverConnector(String browser) {
		defaultBrowser = browser.toLowerCase();
	}

	public DriverConnector(String browser, String browserVersion) {
		this(browser);
		defaultBrowserVersion = browserVersion;
	}

	public WebDriver getWebDriver() throws Exception {
		if (driver == null) {
			triggerBrowser();
		}
		return driver;
	}
	
	private void triggerBrowser() throws Exception {

		switch (defaultBrowser) {
		case "chrome":
			if (defaultBrowserVersion == null)
				defaultBrowserVersion = CHROME_VERSION;
			driver = new ChromeInstantiator(defaultBrowserVersion).launchChrome();
			break;
		case "firefox":

		default:
			throw new ConnectionException("Invalid browser input, and it is " + defaultBrowser);
		}
		driver.manage().window().maximize();
	}

	public void endDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
