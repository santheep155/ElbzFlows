package utility;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class Config {

	private static final String configPath = "src/main/resources/config.properties";
	private static Properties properties;
	
	public static void initConfigRead() throws Exception{
		properties = new Properties();
		Reader fileReader = new FileReader(configPath);
		properties.load(fileReader);
	}
	
	public static String getURL() {
		return properties.get("url").toString();
	}

	public static long getImplicitWaitTimeout() {
		return Long.parseLong(properties.get("implicitWait").toString());
	}
	
	public static long getPageLoadTimeout() {
		return Long.parseLong(properties.get("pageloadtimeout").toString());
	}
	
	public static String getChromeDriverLoc() {
		return properties.getProperty("chromeDriverLoc").toString();
	}
	
	public static String getFirefoxDriverLoc() {
		return properties.getProperty("firefoxDriverLoc").toString();
	}	
	
	public static String getBrowserName() {
		return properties.get("browser").toString();
	}
	
	public static String getTestDataFilePath() {
		return properties.getProperty("testDataLoc").toString();
	}

	public static String getScreenshotPath() {
		return properties.getProperty("screenshotLoc").toString();
	}
	
	public static String getUsername() {
		return properties.getProperty("username").toString();
	}

	public static String getPassword() {
		return properties.getProperty("password").toString();
	}
	
	public static long getOrderCount() {
		return Long.parseLong(properties.getProperty("ordercount").toString());
	}
	
	public static String getStoreName() {
		return properties.getProperty("store").toString();
	}
}