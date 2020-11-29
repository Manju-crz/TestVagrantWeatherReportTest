package mks.util.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeInstantiator {

	private String CHROME_DEFAULT_DRIVER = "3.3";

	public ChromeInstantiator(String chromeDriverVersion) {
		CHROME_DEFAULT_DRIVER = chromeDriverVersion;
	}

	public ChromeInstantiator() {

	}

	public WebDriver launchChrome() throws Exception {

		WebDriver driver = null;
		WebDriverManager.chromedriver().driverVersion(CHROME_DEFAULT_DRIVER).setup();
		driver = new ChromeDriver();
		return driver;
	}

}
