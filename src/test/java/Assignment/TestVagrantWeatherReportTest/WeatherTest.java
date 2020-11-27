package Assignment.TestVagrantWeatherReportTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WeatherTest {
	
	@Test
	public void phase1Test() {
		WebDriver driver = null;
		WebDriverManager.chromedriver().driverVersion("3.3").setup();
		driver = new ChromeDriver();
		driver.get("https://www.ndtv.com/");
		driver.close();
	}
	
	
	
}
