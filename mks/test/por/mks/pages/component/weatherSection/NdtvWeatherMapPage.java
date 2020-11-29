package mks.pages.component.weatherSection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.util.constants.ExecConstants;
import mks.util.finder.Find;
import mks.utils.sleepers.Sleep;

public class NdtvWeatherMapPage {
	
	WebDriver driver = null;
	Find find = null;
	
	private String currentWorkingCity = null;
	private CityWeatherCard cityWeatherCard = null;
	public NdtvWeatherMapPage() throws Exception {
		driver = ExecConstants.connector.getWebDriver();
		find = new Find(driver, 10);
	}
	
	By searchCitynameInputBox = By.id("searchBox");
	By cityCheckbox = null;
	/**
	 * Just helps to fetch the particular city data from UI
	 * @param city
	 * @return
	 */
	public NdtvWeatherMapPage setCityName(String city) {
		find.element(searchCitynameInputBox).sendKeys(city);
		currentWorkingCity = city;
		cityCheckbox = By.id(currentWorkingCity);
		selectCityCheckboxIfNotAlready();
		return this;
	}
	
	private void selectCityCheckboxIfNotAlready() {
		WebElement cityCheckboxElement = find.element(cityCheckbox);
		if (cityCheckboxElement.getAttribute("class") != null
				&& cityCheckboxElement.getAttribute("class").equalsIgnoreCase("defaultChecked")) {
			System.out.println("Already checked city");
		} else {
			cityCheckboxElement.click();
			Sleep.forASecond();
		}
	}

	private CityWeatherCard getCityWeatherCard() throws Exception {
		if (cityWeatherCard == null) {
			cityWeatherCard = new CityWeatherCard(currentWorkingCity);
		}
		return cityWeatherCard;
	}

	public String getLeafLetTemperatureContent() throws Exception {
		return getCityWeatherCard().getLeafLetContent();
	}

	public String getTemperatureInDC() throws Exception {
		return getCityWeatherCard().getDegreeCelsiusValue();
	}

	public String getTemperatureInFahrenheit() throws Exception {
		return getCityWeatherCard().getFahrenheitValue();
	}
	
}
