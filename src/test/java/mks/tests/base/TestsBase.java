package mks.tests.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import mks.pages.component.mainpages.NdtvHomePage;
import mks.util.constants.AppApiConstants;
import mks.util.constants.ExecConstants;
import mks.util.constants.UiAppConstants;

public class TestsBase {

	protected WebDriver driver = null;
	protected NdtvHomePage ndtvPage = null;
	
	protected RequestSpecification requestSpecification = null;
	
	@BeforeSuite
	public void navigateAUT() throws Exception {

		driver = new ExecConstants().connector.getWebDriver();
		driver.get(new UiAppConstants().appURL);
		ndtvPage = new NdtvHomePage();
		
		RestAssured.baseURI = new AppApiConstants().apiServerEndpoint;
		requestSpecification = RestAssured.given().header("Content-Type", "application/json");
	}

	@AfterSuite
	public void closeTest() throws Exception {

		ExecConstants.connector.endDriver();
	}

}
