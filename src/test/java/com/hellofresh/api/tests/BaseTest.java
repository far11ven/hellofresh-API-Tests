package com.hellofresh.api.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.hellofresh.api.utilities.ConfigReader;
import com.hellofresh.api.utilities.TestUtils;
import com.hellofresh.api.utils.ApiUtils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class BaseTest {

    public Response res = null; //Response
    public JsonPath jp = null; //JsonPath
    public String configFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
    
    ConfigReader configReader = new ConfigReader(configFilePath);

    //Instantiate a Helper Test Methods (testUtils) Object
    TestUtils testUtils = new TestUtils();

    @BeforeClass
    public void setup (){
        //Test Setup
        ApiUtils.setBaseURI(configReader.getProperty("BASE_URI"));   //Setup Base URI
        ApiUtils.setBasePath(configReader.getProperty("BASE_PATH")); //Setup Base Path
        ApiUtils.setContentType(ContentType.JSON);                   //Setup Content Type
    }

    @AfterClass
    public void afterTest (){
        //Reset Values
        ApiUtils.resetBaseURI();
        ApiUtils.resetBasePath();
    }

}
