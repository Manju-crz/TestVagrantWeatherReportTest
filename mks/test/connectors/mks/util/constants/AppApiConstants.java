package mks.util.constants;

public class AppApiConstants extends TestConstants {

	public static String apiServerEndpoint = null;
	public static String apiKey = null;

	public AppApiConstants() throws Exception {
		super();
		apiServerEndpoint = configPropFile.getProperty("ServerEndpoint");
		apiKey = configPropFile.getProperty("ApiKey");
	}

}
