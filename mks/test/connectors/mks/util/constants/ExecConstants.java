package mks.util.constants;


import mks.util.connectors.DriverConnector;

public class ExecConstants extends TestConstants{

	public static DriverConnector connector = null;

	public ExecConstants() throws Exception {
		connector = new DriverConnector(browser, webdriverVersion);
	}

}
