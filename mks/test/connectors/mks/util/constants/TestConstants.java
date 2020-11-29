package mks.util.constants;

import java.io.File;

import mks.fileutil.texts.PropertiesFileLoader;
import mks.util.connectors.DriverConnector;

public abstract class TestConstants {

	private static String fileSep = File.separator;
	private static String configPropFileName = "Config.properties";
	private static String configPropertiesFile = System.getProperty("user.dir") + fileSep + configPropFileName;
	
	protected static String browser = null;
	protected static String webdriverVersion = null;
	protected static PropertiesFileLoader configPropFile = null;

	protected TestConstants() throws Exception {
		configPropFile = new PropertiesFileLoader(configPropertiesFile);
		browser = configPropFile.getProperty("Browser");
		webdriverVersion = configPropFile.getProperty("WebDriverVersion");
	}

}
