package Assignment.TestVagrantWeatherReportTest;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import mks.fileutil.texts.PropertiesFileLoader;
import mks.pages.component.weatherSection.NdtvWeatherMapPage;
import mks.tests.base.TestsBase;
import mks.util.constants.AppApiConstants;
import mks.utils.sleepers.Sleep;

public class WeatherTest extends TestsBase {

	private String testdataPropFile = System.getProperty("user.dir") + File.separator + "TestData.properties";
	private PropertiesFileLoader testdataProperties = null;

	private String cityTemperatureInDC = null;
	private String cityTemperatureInF = null;
	private String apiTempInDC = null;
	
	private String testdataCityName = null;
	
	@BeforeClass
	public void loadData() throws IOException {
		testdataProperties = new PropertiesFileLoader(testdataPropFile);
		testdataCityName = testdataProperties.getProperty("TestdataCityName");
	}
	
	@Test
	public void phase1Test() throws Exception {
		ndtvPage.discardBreakingAlertsIfFinds();
		NdtvWeatherMapPage weatherMapPage = ndtvPage.navigateToWeatherMapPage();
		Sleep.for2Seconds();
		weatherMapPage.setCityName(testdataCityName);
		cityTemperatureInDC = weatherMapPage.getTemperatureInDC();
		cityTemperatureInF = weatherMapPage.getTemperatureInFahrenheit();

		Assert.assertTrue(cityTemperatureInDC.matches("\\d+"));
		Assert.assertTrue(cityTemperatureInF.matches("\\d+"));

		String info = weatherMapPage.getLeafLetTemperatureContent();
		String[] infoArr = info.split("\n");
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(infoArr[0].startsWith("Condition : "));
		sa.assertTrue(infoArr[1].startsWith("Wind: "));
		sa.assertTrue(infoArr[2].startsWith("Humidity: "));
		sa.assertTrue(infoArr[3].startsWith("Temp in Degrees: "));
		sa.assertTrue(infoArr[4].startsWith("Temp in Fahrenheit: "));
		sa.assertAll();
	}

	@Test
	public void phase2Test() {
		
		String extendedApiPath = "/data/2.5/weather";
		
		Response response = null;
		requestSpecification.param("q", testdataCityName);
		requestSpecification.param("appid", AppApiConstants.apiKey);

		response = requestSpecification.when().get(extendedApiPath);
		apiTempInDC = response.path("main.temp").toString();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(apiTempInDC.matches("[0-9]{1,13}(\\.[0-9]*)?"));
	}

	@Test(dependsOnMethods = { "phase1Test", "phase2Test" })
	public void phase3Test() {

		Assert.assertEquals(apiTempInDC, cityTemperatureInDC,
				"The Api responsed temperature value and the NDTV UI's temperature value in degree celsius is not same!");
	}

}
