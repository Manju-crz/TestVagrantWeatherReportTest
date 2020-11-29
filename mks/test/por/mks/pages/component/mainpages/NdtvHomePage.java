package mks.pages.component.mainpages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.pages.component.weatherSection.NdtvWeatherMapPage;
import mks.util.constants.ExecConstants;
import mks.util.finder.Find;
import mks.utils.sleepers.Sleep;

public class NdtvHomePage {

	WebDriver driver = null;
	Find find = null;
	
	By breakingAlertsNoThanksLink = By.xpath("//div[@class='noti_wrap']//a[@class='notnow']");

	By topNavExtra3DotsButton = By.id("h_sub_menu");
	By topMenuFirstSetSectionLinks = By
			.xpath("//div[@class='neweleccont ntopnav_wrap_opt']//div[@class='topnav_cont']/a");
	By topMenuSecondSetSectionLinks = By
			.xpath("//div[@class='neweleccont ntopnav_wrap2']//div[@class='topnav_cont']/a");

	private final String weatherSectionTopNavText = "WEATHER";

	public NdtvHomePage() throws Exception {
		driver = ExecConstants.connector.getWebDriver();
		find = new Find(driver, 10);
	}

	public NdtvHomePage discardBreakingAlertsIfFinds() {
		try {
			new Find(driver, 5).element(breakingAlertsNoThanksLink).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return this;
	}

	private List<String> getTopMenuFirstSetList() {
		List<String> menuList = new ArrayList<>();
		List<WebElement> menuListElements = find.elements(topMenuFirstSetSectionLinks);
		for (WebElement element : menuListElements) {
			menuList.add(element.getText().trim());
		}
		return menuList;
	}
	
	private void clickOnTopNavExtra3DotsButton() {
		find.element(topNavExtra3DotsButton).click();
		Sleep.forASecond();
	}

	public NdtvWeatherMapPage navigateToWeatherMapPage() throws Exception {
		clickOnTopNavExtra3DotsButton();
		List<WebElement> menuListElements = find.elements(topMenuSecondSetSectionLinks);
		for (WebElement element : menuListElements) {
			if (element.getText().equalsIgnoreCase(weatherSectionTopNavText)) {
				element.click();
				break;
			}
		}
		return new NdtvWeatherMapPage();
	}

}
