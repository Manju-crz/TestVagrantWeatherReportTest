package mks.fileutil.texts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileLoader {

	private Properties prop = null;
			
	public PropertiesFileLoader(String propertiesFile) throws IOException {
		prop = new Properties();
		FileInputStream inputStream = new FileInputStream(propertiesFile);
		prop.load(inputStream);
	}
	
	public String getProperty(String propertyKey) {
		return prop.getProperty(propertyKey);
	}
	
	
}
