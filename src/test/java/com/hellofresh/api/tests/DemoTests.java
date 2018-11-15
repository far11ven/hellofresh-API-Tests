package com.hellofresh.api.tests;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import java.util.HashMap;
import java.util.Map;

import com.hellofresh.api.commonUtils.MyThreadLocal;
import com.hellofresh.api.utilities.FileOperations;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoTests {
/*
	@Test
	public void testingGETMethod() {
		MyThreadLocal.get().saveData("property1", "gsahgdsagjhghjsaghdgsahdgs");
		
		System.out.println("Running Demo Test1 (GET) ====");

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Pune");

		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
		//JSON Schema Matcher
		
		String schema = FileOperations.readFromFile("./src/test/resources/weather-schema.json");
		get("/Pune").then().assertThat().body(matchesJsonSchema(schema));
		//response.then().body("Humidity", equalTo("33 Percent"));

	}

	@Test
	public void testingPOSTMethod() {

		System.out.println("Running Demo Test2  (POST) ====");

		RestAssured.baseURI ="https://stage.beta.masterpassteststore.com/java/legacy-api/masterpass/transaction/postback";
		RequestSpecification httpRequest = RestAssured.given();

		String requestParams = FileOperations.readFromFile("./src/test/resources/requestBody.json");
		System.out.println(requestParams);

		httpRequest.body(requestParams.toString());
		//Response response = httpRequest.request(Method.POST, "/POSTS");
		Response response = httpRequest.post("/POSTS");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		//String successCode = response.jsonPath().get("SuccessCode");
		//Assert.assertEquals(successCode, "OPERATION_SUCCESS", "Correct Success code was returned");

	}

	@Test
	public void testingPOSTMethod() {

		System.out.println("Running Demo Test2  (POST) ====");

		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("userId", 76818); // Cast
		requestParams.put("id", 76818);
		requestParams.put("title", "sdimpleuser2dd2011");
		requestParams.put("body", "password1");

		String requestParams = FileOperations.readFromFile("./src/test/resources/requestBody1.json");

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


	@Test
	public void testingPUTMethod() {

		System.out.println("Running Demo Test2  (POST) ====");

		RestAssured.baseURI ="https://reqres.in/api";
		RequestSpecification httpRequest = RestAssured.given();

		String requestBody = "{\r\n" + 
				"    \"name\": \"alex\",\r\n" + 
				"    \"job\": \"zion resident\"\r\n" + 
				"}";

		httpRequest.body(requestBody);
		
		Map<String, String> headerParams = new HashMap<String, String>();
		headerParams.put("Content-Type", "application/json");
		headerParams.put("user-agent", "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

		httpRequest.headers(headerParams);
		
		Response response = httpRequest.request(Method.PUT, "/users/20000");
		
		Assert.assertEquals("Response code doesn't match", response.getStatusCode(), 200);
		String responseHeaders = response.getHeaders().toString();


	}

	@Test
	public void testingPOSTDEMOQAMethod() {

		System.out.println("Running Demo Test2  (POST - demoqa) ====");

		RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender"); // Cast
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");

		requestParams.put("Email",  "sample2ee26d9@gmail.com");
		request.body(requestParams.toString());
		Response response = request.post("/register");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, "201");
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
	}

	@Test
	public void testingPOSTMethod() {

		System.out.println("Running Demo Test1 (POST) ====");

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://stage.beta.masterpassteststore.com/java/legacy-api/masterpass/transaction/postback";

		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();

		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.POST, "/Hyderabad");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);


	}
*/
}
