package com.WeatherTest.com.WeatherComparison;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.Weather.RestAPIResources.AutomationConstants;
import com.Weather.RestAPIResources.RestRequestResponseExtract;
import com.Weather.Utils.UiUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
/**
 * 
 * Validating AIP response with expected data from excel and then write the data to excel
 * @author oshinnarwal
 *
 */
public class TC01_ValidationOfWeather {
	JsonPath jsonPathevaluator;
	double temperatureAPI;
	double temp_min;
	double temp_max;
	double pressure;
	double sea_level;
	List<Double> tempList = new ArrayList<>();
	List<Double> tempMinList = new ArrayList<>();
	List<Double> tempMaxList = new ArrayList<>();
	List<Double> pressureList = new ArrayList<>();
	List<Double> seaLevelList = new ArrayList<>();

	@Test
	public void readDataFromExcel() throws IOException {

		UiUtils util = new UiUtils();
		/**
		 * We are reading the data from excel for 5 fields
		 */
		util.readFromExcel(tempList, tempMinList, tempMaxList, pressureList, seaLevelList);

	}

	@Test(dependsOnMethods = { "readDataFromExcel" })
	public void getApiResponse() throws IOException {
		RestRequestResponseExtract req = new RestRequestResponseExtract();

		/**
		 * Retrieving the response from the API
		 */
		Response res = req.buildRequest("GET", "WEATHERAPI", "");
		Response resObj = req.extractResponse(res);
		System.out.println(resObj.asString());
		System.out.println(jsonPathevaluator = resObj.jsonPath());
		List<Double> liTempApi = new ArrayList<Double>();
		/**
		 * Storing the data from the API into list
		 */
		liTempApi = jsonPathevaluator.getList(AutomationConstants.TEMPRESPONSE);
		List<Double> liTemp_minApi = jsonPathevaluator.getList(AutomationConstants.TEMP_MINRESPONSE);
		List<Double> liTemp_maxApi = jsonPathevaluator.getList(AutomationConstants.TEMP_MAXRESPONSE);
		List<Double> li_PressureApi = jsonPathevaluator.getList(AutomationConstants.PRESSURE_RESPONSE);
		List<Double> li_SeaLevelApi = jsonPathevaluator.getList(AutomationConstants.SEALEVEL_RESPONSE);
		System.out.println(liTempApi);
		System.out.println(liTemp_minApi);
		System.out.println(liTemp_maxApi);
		System.out.println(li_PressureApi);
		System.out.println(li_SeaLevelApi);

		UiUtils util = new UiUtils();
		/**
		 * writing the dta from the lists into excel
		 */
		util.writeToExcel(liTempApi, liTemp_minApi, liTemp_maxApi, li_PressureApi, li_SeaLevelApi, tempList, tempMinList, tempMaxList,
				pressureList, seaLevelList);

	}

}
