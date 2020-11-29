package mks.util.constants;


public class UiAppConstants extends TestConstants{
	
	public static String appURL = null;
	
	public UiAppConstants() throws Exception {
		super();
		appURL = configPropFile.getProperty("AUT_URL");
	}

	
}
