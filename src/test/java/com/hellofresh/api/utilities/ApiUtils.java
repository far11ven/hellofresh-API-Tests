package com.hellofresh.api.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

import com.hellofresh.api.utilities.LOGGERUtil;

import static io.restassured.RestAssured.get;


/*
 * This is class contains all the restAssured related getter/setter methods
 */
public class ApiUtils {

	//Global Setup Variables
	public static String path;
	public static String jsonPathTerm;
	private static final LOGGERUtil LOGGER = new LOGGERUtil(ApiUtils.class);

	//Sets Base URI
	public static void setBaseURI (String baseURI){
		RestAssured.baseURI = baseURI ;
	}

	//gets base uri
	public static String getBaseURI(){
		return RestAssured.baseURI;
	}

	//Sets base path
	public static void setBasePath(String basePathTerm){
		RestAssured.basePath = basePathTerm;
		LOGGER.info("Setting base path as :" + basePathTerm);
	}

	//get base path
	public static String getBasePath(){
		return RestAssured.basePath;
	}

	//Reset Base URI (after test)
	public static void resetBaseURI (){
		RestAssured.baseURI = null;
	}

	//Reset base path
	public static void resetBasePath(){
		RestAssured.basePath = null;
	}

	//Sets ContentType
	public static void setContentType (ContentType Type){
		given().contentType(Type);
	}


	//Sets RequestBody
	public static void setRequestBody (String body){
		given()
		.body(body);
	}

	//Sets RequestHeader
	public static void setRequestHeader(Map<String, String> headers){
		given()
		.headers(headers);
	}

	//Sets PostRequestResponse
	public static Response getPOSTResponse (){
		return given().post("");
	}

	//Returns response by given path
	public static Response getResponsebyPath(String path) {
		return get(path);
	}

	//Returns response
	public static Response getResponse() {
		return get();
	}

	//Returns JsonPath object
	public static JsonPath getJsonPath (Response res) {
		String json = res.asString();
		return new JsonPath(json);
	}
}