package com.hellofresh.api.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.hellofresh.api.utilities.ApiUtils;
import com.hellofresh.api.utilities.ConfigReader;
import com.hellofresh.api.utilities.LOGGERUtil;
import com.hellofresh.api.utilities.TestUtils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*
 * This is class is Base class for ApiTestSuite class, also provide @BeforeClass, @AfterClass, @AfterMethod 
 */
public class BaseTest {

	public Response res = null; //Response
	public JsonPath jp = null; //JsonPath
	public String configFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

	ConfigReader configReader = new ConfigReader(configFilePath);

	private static final LOGGERUtil LOGGER = new LOGGERUtil(BaseTest.class);

	//Instantiate a Helper Test Methods (testUtils) Object
	TestUtils testUtils = new TestUtils();

	@BeforeClass
	public void setup (){
		//Setup Base URI
		ApiUtils.setBaseURI(configReader.getProperty("BASE_URI"));   
		LOGGER.info("Setting BASEURI as :" + configReader.getProperty("BASE_URI"));
		//Setup Base Path
		ApiUtils.setBasePath(configReader.getProperty("BASE_PATH")); 
		LOGGER.info("Setting BASEPATH as :" + configReader.getProperty("BASE_PATH"));

		//Setup Content Type
		ApiUtils.setContentType(ContentType.JSON);                   
	}
	
	@AfterMethod
	public void afterTestMethod (){
		LOGGER.info("Completed Test \n");
	}

	@AfterClass
	public void tearDown (){
		//Reset Values
		ApiUtils.resetBaseURI();
		ApiUtils.resetBasePath();
	}

}
