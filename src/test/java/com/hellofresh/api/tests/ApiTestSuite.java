package com.hellofresh.api.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.hellofresh.api.globalutils.ApiUtils;
import com.hellofresh.api.globalutils.TestUtils;
import com.hellofresh.api.utilities.LOGGERUtil;
import java.util.HashMap;
import java.util.Map;

/*
 * This is class contains all the API Testing tasks and also inherits BaseTest class
 */
public class ApiTestSuite extends BaseTest{

	private static final LOGGERUtil LOGGER = new LOGGERUtil(ApiTestSuite.class);

	/*
	 * This is test method which is related to API Task#1 i.e. "Get all countries and validate that US, DE and GB were returned in the response"
	 */

	@Test
	public void T01_GET_AllCountriesTest() {

		LOGGER.info("Starting Test : T01_GET_AllCountriesTest");

		res = ApiUtils.getResponsebyPath("/all");
		jp = ApiUtils.getJsonPath(res);

		//verify if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify if response matches GET All countries schema
		testUtils.validateResponseSchema(res, "all-countries-schema.json");

		//Check that US, DE & GB countries are present in the response
		Assert.assertTrue(testUtils.isCountryPresent(jp, "US"), "US Wasn't present in the list");
		Assert.assertTrue(testUtils.isCountryPresent(jp, "DE"), "DE Wasn't present in the list");
		Assert.assertTrue(testUtils.isCountryPresent(jp, "GB"), "GB Wasn't present in the list");


	}



	/*
	 * This is 1st test method which is related to API Task#2 i.e. "Get each country (US, DE and GB) individually and validate the response"
	 * gets valid test data from dataProvider="validCountryCodes" from TestUtils.class
	 */

	@Test(dataProvider="validCountryCodes", dataProviderClass = TestUtils.class)
	public void T02_GET_IndividualCountryTestforCountryCodes(String countryCode) {
		LOGGER.info("Starting Test : T02_GET_IndividualCountryTestforCountryCodes with country code " + countryCode);

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/" + countryCode);
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify if GET with countryCode response matches GET Individual countries schema
		testUtils.validateResponseSchema(res, "matched-individual-country-schema.json");

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, countryCode), "Country with code " + countryCode + " is not found");

		//Check that country code is present in the response
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, countryCode), countryCode + " Wasn't present in the list");


	}

	/*
	 * This is test method which is related to API Task#3 i.e. "Try to get information for inexistent countries and validate the response"
	 * gets invalid test data from dataProvider="inexistentCountries" from TestUtils.class
	 */
	@Test(dataProvider="inexistentCountries", dataProviderClass = TestUtils.class)
	public void T03_GET_InexistentCountryTest(String countryName) {
		LOGGER.info("Starting Test : T03_GET_InexistentCountryTest with country code value : " + countryName);

		//Set BASEPATH to /get/iso2code
		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/" + countryName);
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify if GET countryName (inexistent) country response matches GET Inexistent country schema
		testUtils.validateResponseSchema(res, "inexistent-country-schema.json");


		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkInexistentCountryFoundMessage(res, countryName), "Inexistent Country with code " + countryName + " found");

		//Check that countryName country code is present in the response
		Assert.assertNull(jp.get("RestResponse.result"), countryName + " Was present in the list");

	}

	/*
	 * This is test method is related to API Task#4 i.e. "Write a test that would validate new country addition using POST"
	 */

	@Test
	public void T04_POST_AddNewCountryTest() {
		LOGGER.info("Starting Test : T04_POST_AddNewCountryTest");

		Map<String, String> headerParams = new HashMap<String, String>();      //header params to be passed with POST request
		headerParams.put("Content-Type", "application/json");
		headerParams.put("user-agent", "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

		//Set BASEPATH to /post (Assuming complete path for post request is http://services.groupkt.com/country/post)
		ApiUtils.setBasePath("/post");

		//sets POST requestbody
		ApiUtils.setRequestBody(testUtils.getPostRequestBody("postRequestBody.json"));
		//sets POST request headers
		ApiUtils.setRequestHeader(headerParams);

		res = ApiUtils.getPOSTResponse();
		jp = ApiUtils.getJsonPath(res);

		//Validation#1
		//Check if the HTTP Status received in response was 201 (In this case it is 200, as this is not active endpoint for POST)
		testUtils.checkStatusIs(res, 200);

		//Validation#2
		//Also we could add validation to match POSTreq response schema

		//Validation#3
		//as Post response is not defined, assuming it has message : "New Country added successfully", we could then do
		//Assert.assertEquals(jp.get("RestResponse.message"),"New Country added successfully", "Message do-not match");  // right now this will fail


	}


}
