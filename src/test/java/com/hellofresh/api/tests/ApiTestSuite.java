package com.hellofresh.api.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.hellofresh.api.utilities.ApiUtils;
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
	 */

	@Test
	public void T02a_GET_IndividualCountryTestforUS() {
		LOGGER.info("Starting Test : T02a_GET_IndividualCountryTestforUS");

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/US");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify if GET US country response matches GET Individual countries schema
		testUtils.validateResponseSchema(res, "matched-individual-country-schema.json");

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "US"), "Country with code US is not found");

		//Check that US country code is present in the response
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "US"), "US Wasn't present in the list");


	}

	/*
	 * This is 2nd test method which is related to API Task#2 i.e. "Get each country (US, DE and GB) individually and validate the response"
	 */

	@Test
	public void T02b_GET_IndividualCountryTestforDE() {
		LOGGER.info("Starting Test : T02b_GET_IndividualCountryTestforDE");

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/DE");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify if GET DE country response matches GET Individual countries schema
		testUtils.validateResponseSchema(res, "matched-individual-country-schema.json");

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "DE"), "Country with code DE is not found");

		//Check that DE country code is present in the response
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "DE"), "DE Wasn't present in the list");	

	}

	/*
	 * This is 3rd test method which is related to API Task#2 i.e. "Get each country (US, DE and GB) individually and validate the response"
	 */

	@Test
	public void T02c_GET_IndividualCountryTestforGB() {
		LOGGER.info("Starting Test : T02c_GET_IndividualCountryTestforGB");

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/GB");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify if GET GB country response matches GET Individual countries schema
		testUtils.validateResponseSchema(res, "matched-individual-country-schema.json");

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "GB"), "Country with code GB is not found");

		//Check that GB country code is present in the response
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "GB"), "GB Wasn't present in the list");


	}

	/*
	 * This is test method which is related to API Task#3 i.e. "Try to get information for inexistent countries and validate the response"
	 */
	@Test
	public void T03_GET_InexistentCountryTest() {
		LOGGER.info("Starting Test : T03_GET_InexistentCountryTest");

		//Set BASEPATH to /get/iso2code
		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/DX");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify if GET DX (inexistent) country response matches GET Inexistent country schema
		testUtils.validateResponseSchema(res, "inexistent-country-schema.json");


		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkInexistentCountryFoundMessage(res, "DX"), "Inexistent Country with code DX found");

		//Check that GB country code is present in the response
		Assert.assertNull(jp.get("RestResponse.result"), "DX Wasn't present in the list");

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
