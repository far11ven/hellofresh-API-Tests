package com.hellofresh.api.globalutils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.hellofresh.api.globalutils.ApiUtils;
import com.hellofresh.api.utilities.FileOperations;
import com.hellofresh.api.utilities.LOGGERUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



/*
 * This is class contains all the helper methods required by ApiTestSuite class
 */

public class TestUtils {

	private static final LOGGERUtil LOGGER = new LOGGERUtil(TestUtils.class);

	//This method verifies the http response status returned
	public void checkStatusIs(Response res, int statusCode) {

		try {
			Assert.assertEquals(res.getStatusCode(), statusCode, "HTTP Response Status Check Failed!");
			LOGGER.info("Http Response Status code is as expected : " + statusCode);
		} catch (AssertionError e) {

			LOGGER.fail("API Response Http Status expected was [" + res.getStatusCode() + "] and actual is [" + statusCode +"]");
		}
	}

	//This method validates a response against selected schema
	public void validateResponseSchema(Response res, String schemaName) {

		try {

			String schema = FileOperations.readFromFile("./src/test/resources/schemas/" + schemaName);
			res.then().assertThat().body(matchesJsonSchema(schema));
			LOGGER.info("Response Schema Validation is PASS");

		} catch (FileNotFoundException e) {
			LOGGER.fail("Couldn't find [" +  schemaName + "] schema in src/test/resources/schemas folder");

		}catch (AssertionError ex) {
			ex.printStackTrace();
			LOGGER.fail("Response schema validation failed!!" + Joiner.on("\n").join(Iterables.limit(Arrays.asList(ex.getStackTrace()), 10)));  //stores only 10 lines from error stacktrace in log file
		}
	}

	//This method checks if a Country Code is present in GET All Countries response, return true if found
	public boolean isCountryPresent (JsonPath jp, String countryCode) {

		ArrayList<Object> clientList = jp.get("RestResponse.result");

		for(int i=0; i < clientList.size(); i++) {

			HashMap<Object,Object> fetchedCountry = jp.get("RestResponse.result[" + i + "]");

			if(fetchedCountry.get("alpha2_code").equals(countryCode)) {
				return true;
			}

			if(i == clientList.size()) {

				return false;
			}
		}

		return false;
	}

	//This method verifies the http response message for "Country Found", return true if found
	public boolean checkCountryFoundMessage(Response res, String countryCode) {

		String successMessage = "Country found matching code [" + countryCode + "].";

		JsonPath jp = ApiUtils.getJsonPath(res);
		ArrayList<Object> fetchedCountryObject = jp.get("RestResponse.messages");

		if(fetchedCountryObject.get(0).equals(successMessage)) {

			return true;
		} else {

			return false;
		}

	}


	//This method verifies the http response message for "No Country Found", return true if found
	public boolean checkInexistentCountryFoundMessage(Response res, String countryCode) {

		String successMessage = "No matching country found for requested code [" + countryCode + "].";

		JsonPath jp = ApiUtils.getJsonPath(res);
		ArrayList<Object> fetchedCountryObject = jp.get("RestResponse.messages");
		if(fetchedCountryObject.get(0).equals(successMessage)) {

			return true;
		} else {

			return false;
		}

	}


	//This method checks if a Country Code is present in GET individual country response, return true if found
	public boolean isCountryCodePresent (JsonPath jp, String countryCode) {

		HashMap<Object,Object> fetchedCountry = jp.get("RestResponse.result");

		if(fetchedCountry.get("alpha2_code").equals(countryCode)) {
			return true;
		}

		return false;
	}

	//Fetches POST request body from postRequestBody.json
	public String getPostRequestBody(String fileName) {
		try {

			String postBody = FileOperations.readFromFile("./src/test/resources/" + fileName);
			LOGGER.info("Request Body extracted from file" + fileName);
			return postBody;

		} catch (FileNotFoundException e) {
			LOGGER.fail("Couldn't find [" +  fileName + "] json file in src/test/resources folder");

		}
		return null;
	}

	//provides test data for test T02_GET_IndividualCountryTestforCountryCodes
	@DataProvider(name="validCountryCodes")
	public Object[] createValidCountries() {
		return new Object[] {
				"US",
				"DE",
				"GB"
		};
	}

	//provides test data for test T03_GET_InexistentCountryTest
	@DataProvider(name="inexistentCountries")
	public Object[] createInexistentCountries() {
		return new Object[] {
				"DX",
				"xdcs",
				"1966"
		};
	}
}
