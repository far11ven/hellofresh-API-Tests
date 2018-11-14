package com.hellofresh.api.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;

public class ApiUtils {
	
	//Global Setup Variables
	public static String path;
	public static String jsonPathTerm;

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
		//System.out.print("returned json: " + json +"\n");
		return new JsonPath(json);
	}
}