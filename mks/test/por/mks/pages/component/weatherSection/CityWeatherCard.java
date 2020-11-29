package mks.pages.component.weatherSection;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.util.constants.ExecConstants;
import mks.util.finder.Find;
import mks.utils.sleepers.Sleep;

class CityWeatherCard {

	WebDriver driver = null;
	Find find = null;
	
	private String cityName = null;
	private WebElement cityOuterContainer = null;
	CityWeatherCard(String cityName) throws Exception {
		this.cityName = cityName;
		driver = ExecConstants.connector.getWebDriver();
		find = new Find(driver, 10);
		if(!isCityCardVisible()) {
			throw new Exception("There is no container found with the city name : " + cityName);
		}
	}
	
	
	By citiesWeatherOuterContainers = By.xpath("//div[@class='outerContainer']");
	
	
	public boolean isCityCardVisible() {
		List<WebElement> containers = find.elements(citiesWeatherOuterContainers);
		for (WebElement card : containers) {
			if (card.getAttribute("title").trim().equalsIgnoreCase(cityName)) {
				cityOuterContainer = card;
				return true;
			}
		}
		return false;
	}
	
	By temperatureTextInDC = By.xpath(".//span[@class='tempRedText']");
	By temperatureTextInF = By.xpath(".//span[@class='tempWhiteText']");
	
	public String getDegreeCelsiusValue() {
		String str = find.child(cityOuterContainer, temperatureTextInDC).getText().trim();
		return str.substring(0,str.length()-1);
	}
	
	public String getFahrenheitValue() {
		String str = find.child(cityOuterContainer, temperatureTextInF).getText().trim();
		return str.substring(0,str.length()-1);
	}

	public String getLeafLetContent() throws Exception {
		LeafLetPopupContent leaf = new LeafLetPopupContent();
		String info = leaf.getLeafLetCityTemperatureInformation();
		leaf.closeLeafLetPopup();
		return info;
	}
	
	
	class LeafLetPopupContent{
		
		By leafLetPopup = By.xpath("//div[@class='leaflet-popup-content']");
		By leafLetPopupCityText = By.xpath("(//div[@class='leaflet-popup-content']//span)[2]");
		By leafLetPopupTempInfo = By.xpath("//div[@class='leaflet-popup-content']//span");
		By leafPopupCloseButton = By.xpath("//a[@class='leaflet-popup-close-button']");

		private String leafLetCity = null;

		LeafLetPopupContent() {
			if ((find.elements(leafLetPopup).size()) == 1 && (!find.element(leafLetPopupCityText).getText().equalsIgnoreCase(cityName))) {
				find.element(leafPopupCloseButton).click();
			} else {
				cityOuterContainer.click();
				Sleep.forASecond();
			}
			if (!isCityNameLeafPopupMatching()) {
				System.out.println(
						"City card Leaf let content not matching to the given city name, and the found leaf let popup city name is : "
								+ leafLetCity);
			}
		}

		private boolean isCityNameLeafPopupMatching() {
			leafLetCity = find.element(leafLetPopupCityText).getText();
			if (leafLetCity.toLowerCase().startsWith(cityName.toLowerCase() + ",")) {
				return true;
			}
			return false;
		}

		public String getLeafLetCityText() {
			return leafLetCity;
		}
		
		public String getLeafLetCityTemperatureInformation() {
			String info = "";
			List<WebElement> infoElements = find.elements(leafLetPopupTempInfo);
			for (int i = 2; i < infoElements.size(); i++) {
				info = info + infoElements.get(i).getText().trim() + "\n";
			}
			return info.substring(0, info.length() - 1);
		}

		public void closeLeafLetPopup() throws Exception {
			cityOuterContainer.click();
			Sleep.forASecond();
			if (find.elements(leafLetPopup).size() != 0) {
				throw new Exception("LeafLet popup is still visible even after closing it!");
			}
		}
	}
	
	
	
	
	
}
