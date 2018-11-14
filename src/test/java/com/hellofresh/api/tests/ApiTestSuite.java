package com.hellofresh.api.tests;


import org.junit.Assert;
import org.testng.annotations.*;
import com.hellofresh.api.utils.ApiUtils;


public class ApiTestSuite extends BaseTest{

	@Test
	public void T01_GETCountriesTest() {

		res = ApiUtils.getResponsebyPath("/all");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//verify that US, DE & GB countries are present in the response
		Assert.assertTrue("US Wasn't present in the list", testUtils.isCountryPresent(jp, "US"));
		Assert.assertTrue("DE Wasn't present in the list", testUtils.isCountryPresent(jp, "DE"));
		Assert.assertTrue("GB Wasn't present in the list", testUtils.isCountryPresent(jp, "GB"));
	}

	@Test
	public void T02_GETIndividualCountryTestforUS() {

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/US");
		jp = ApiUtils.getJsonPath(res);
		
		System.out.println(" ==> " + res.asString());

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);
	}
	
	

	/*    @Test
    public void TC03_PostMethod() throws JSONException,InterruptedException {

    	 //Rest API's URL
    	 String restAPIUrl = "http://{URL of API}";

    	 //API Body
    	 String apiBody = "{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}";

    	 // Building request by using requestSpecBuilder
    	 RequestSpecBuilder builder = new RequestSpecBuilder();

    	 //Set API's Body
    	 builder.setBody(apiBody);

    	 //Setting content type as application/json
    	 builder.setContentType("application/json; charset=UTF-8");

    	 RequestSpecification requestSpec = builder.build();

    	 //Making post request with authentication or leave blank if you don't have credentials like: basic("","")
    	 Response response = given().authentication().preemptive().basic({username}, {password})
    	 .spec(requestSpec).when().post(restAPIUrl);

    	 JSONObject JSONResponseBody = new JSONObject(response.body().asString());

    	 //Get the desired value of a parameter
    	 String result = JSONResponseBody.getString({key});

    	 //Check the Result
    	 Assert.assertEquals(result, "{expectedValue}");


    }*/


}
