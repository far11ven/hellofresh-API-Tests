package com.hellofresh.api.utilities;



import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.testng.Assert;

import com.hellofresh.api.utils.ApiUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TestUtils {

	//This method verifies the http response status returned
	public void checkStatusIs(Response res, int statusCode) {
		Assert.assertEquals(res.getStatusCode(), statusCode, "HTTP Response Status Check Failed!");
	}

	//This method checks if a Country is present in response
	public boolean isCountryPresent (JsonPath jp, String countryCode) {

		ArrayList clientList = jp.get("RestResponse.result");

		for(int i=0; i < clientList.size(); i++) {

			HashMap fetchedCountry = jp.get("RestResponse.result[" + i + "]");

			if(fetchedCountry.get("alpha2_code").equals(countryCode)) {
				return true;
			}

			if(i == clientList.size()) {

				return false;
			}
		}

		return false;
	}

	//This method verifies the http response message for "Country Found"
	public boolean checkCountryFound(Response res, String countryCode) {
		
		String successMessage = "Country found matching code [" + countryCode + "].";
		
		JsonPath jp = ApiUtils.getJsonPath(res);
		ArrayList fetchedCountry = jp.get("RestResponse.messages");
		
		if(fetchedCountry.get(0).equals(successMessage)) {
			
			return true;
		} else {
			
			return false;
		}
				
	}
}
