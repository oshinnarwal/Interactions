package com.Weather.RestAPIResources;
/**
 * This class will hold the constants values required for each tests.
 * @author onarwal
 *
 */
public class AutomationConstants {

	public static final String CONTENT_TYPE = "Content-Type";
	public static final String AUTHORIZATION = "Authorization";
	public static final String REQUEST = "Request";
	public static final String RESOURCE = "Resource";
	public static final String lANGUAGE = "LANGUAGE";
	public static final String ENV = "ENV";
	public static final int STATUS_CODE = 200;
	public static final String SYSTEM_PATH = System.getProperty("user.dir");
	public static final String RESOURCE_PATH = "/src/test/java/";
	public static final String GLOBAL_PROP_PATH = "src\\main\\java\\com\\Weather\\RestAPIResources\\Global.properties";
	public static final String baseURL = "baseUrl";
	public static final long GLOBAL_IMPLICIT_WAIT_TIME=10;
	public static final String CITY = "London";
	public static final String TEMPRESPONSE = "list.main.temp";
	public static final String TEMP_MINRESPONSE = "list.main.temp_min";
	public static final String TEMP_MAXRESPONSE = "list.main.temp_max";
	public static final String PRESSURE_RESPONSE = "list.main.pressure";
	public static final String SEALEVEL_RESPONSE = "list.main.sea_level";
	
	public static final String HUMPRESPONSE = "main.humidity";
	public static final String DRIVERPATH ="//Drivers//chromedriver";


}
