package com.hellofresh.api.tests;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import com.hellofresh.api.utils.ApiUtils;
import com.hellofresh.api.utils.LogUtils;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ApiTestSuite extends BaseTest{
	
	private static final LogUtils LOGGER = new LogUtils(ApiTestSuite.class);

	@Test
	public void T01_GETAllCountriesTest() {
		
		LOGGER.info("Starting Test : T01_GETAllCountriesTest");
		
		res = ApiUtils.getResponsebyPath("/all");
		jp = ApiUtils.getJsonPath(res);

		//verify if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check that US, DE & GB countries are present in the response
		Assert.assertTrue(testUtils.isCountryPresent(jp, "US"), "US Wasn't present in the list");
		Assert.assertTrue(testUtils.isCountryPresent(jp, "DE"), "DE Wasn't present in the list");
		Assert.assertTrue(testUtils.isCountryPresent(jp, "GB"), "GB Wasn't present in the list");
	}

	@Test
	public void T02a_GETIndividualCountryTestforUS() {
		LOGGER.info("Starting Test : T02a_GETIndividualCountryTestforUS");

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/US");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "US"), "Country with code US is not found");
		
		//Check that US country code is present in the response
				Assert.assertTrue(testUtils.isCountryCodePresent(jp, "US"), "US Wasn't present in the list");
				

	}

	@Test
	public void T02b_GETIndividualCountryTestforDE() {
		LOGGER.info("Starting Test : T02b_GETIndividualCountryTestforDE");

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/DE");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "DE"), "Country with code DE is not found");

		//Check that DE country code is present in the response
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "DE"), "DE Wasn't present in the list");	

	}

	@Test
	public void T02c_GETIndividualCountryTestforGB() {
		LOGGER.info("Starting Test : T02c_GETIndividualCountryTestforGB");

		ApiUtils.setBasePath("/get/iso2code");

		res = ApiUtils.getResponsebyPath("/GB");
		jp = ApiUtils.getJsonPath(res);

		//Check if the HTTP Status received in response was 200
		testUtils.checkStatusIs(res, 200);

		//Check if the HTTP response has message "Country found matching code"
		Assert.assertTrue(testUtils.checkCountryFoundMessage(res, "GB"), "Country with code GB is not found");
		
		//Check that GB country code is present in the response
		Assert.assertTrue(testUtils.isCountryCodePresent(jp, "GB"), "GB Wasn't present in the list");

	}


	@Test
	public void post_test() {
		/*LOGGER.info("Starting Test : post_test");
		
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
		response.then().body("name", Matchers.is("Lisa"));*/
		
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("userId", 76818); // Cast
		requestParams.put("id", 76818);
		requestParams.put("title", "sdimpleuser2dd2011");
		requestParams.put("body", "password1");


		System.out.println("Request Params :" + requestParams.toString());

		Map<String, String> headerPrams = new HashMap<String, String>();
		headerPrams.put("Content-Type", "application/json");
		headerPrams.put("user-agent", "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

		httpRequest.headers(headerPrams);
		httpRequest.body(requestParams.toString());
		Response response = httpRequest.request(Method.POST, "/POSTS");
		//Response response = httpRequest.post("/POSTS");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);

		System.out.println("Value 1 : \n" +  response.contentType());
		System.out.println("Value 2 : \n" +  response.getContentType());

		System.out.println("Value 3 : \n" +  response.getTime());
		System.out.println("Value 4 : \n" +  response.getHeader("content-type"));
		System.out.println("Value 5 : \n" +  response.getHeaders());
		//String successCode = response.jsonPath().get("SuccessCode");
		//Assert.assertEquals(successCode, "OPERATION_SUCCESS", "Correct Success code was returned");
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
