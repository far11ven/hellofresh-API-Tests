package com.hellofresh.api.utilities;



import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TestUtils {

    //Verify the http response status returned. Check Status Code is 200?
    public void checkStatusIs(Response res, int statusCode) {
        Assert.assertEquals(res.getStatusCode(), statusCode, "HTTP Response Status Check Failed!");
    }

  //Get Clients
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
}
