package com.hellofresh.api.tests;


import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.*;
import com.hellofresh.api.utils.ApiUtils;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ApiTestSuite extends BaseTest{

	@Test
	public void T01_GETAllCountriesTest() {

		res = ApiUtils.getResponsebyPath("/all");
		jp = ApiUtils.getJsonPath(res);

		//verify if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check that US, DE & GB country code are present in the response
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "US"), "US Wasn't present in the list");
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "DE"), "DE Wasn't present in the list");
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "GB"), "GB Wasn't present in the list");
	}

	@Test
	public void T02a_GETIndividualCountryTestforUS() {

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/US");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "US"), "Country with code US is not found");
		
		//Check if the HTTP response has Country code in Result OBJECT
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "US"), "US Wasn't present in the list");

	}

	@Test
	public void T02b_GETIndividualCountryTestforDE() {

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/DE");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "DE"), "Country with code DE is not found");
		
		//Check if the HTTP response has Country code in Result OBJECT
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "DE"), "DE Wasn't present in the list");

	}

	@Test
	public void T02c_GETIndividualCountryTestforGB() {

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/GB");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "GB"), "Country with code GB is not found");
		
		//Check if the HTTP response has Country code in Result OBJECT
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "GB"), "GB Wasn't present in the list");
		
	}


	@Test
	public void post_test() {
		
		RestAssured.baseURI = "http://webhooks.mongodb-stitch.com/api/client/v2.0/app/bouldersandroidapp-xitme/service/bdb/incoming_webhook/POST_BDB?secret=SECRET";
		Response response = given().
				 contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\"email\": \"world\", \"password\": \"world\" }")
				.when()
				.post("");
		System.out.println("POST Response\n" + response.asString());
		// tests
		response.then().body("id", Matchers.any(Integer.class));
		response.then().body("name", Matchers.is("Lisa"));
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
