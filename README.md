## API Automation Tests - Task#2


This framework uses Rest-Assured Library With TestNG for API Testing 

- For logging Log4J is used
- All tests are stored in ```"ApiTestSuite" class```, under package ```"com.hellofresh.api.tests"``` under Under ```src/test/java```
- ```"com.hellofresh.api.globalutils"``` package which contains test helper methods
- All utility classes are stored in ```"com.hellofresh.api.utilities"``` under Under ```src/main/java```
- ```"config.properties"``` contains all the configuration settings
- Schemas against which response validation are done under ```"schemas"``` folder in ```src/test/resources``` package


**Task Mapping:**

1) Task#1 : ApiTestSuite test method *T01_GET_AllCountriesTest*
2) Task#2 : ApiTestSuite test method *T02_GET_IndividualCountryTestforCountryCodes*
3) Task#3 : ApiTestSuite test method *T03_GET_InexistentCountryTest*
3) Task#4 : ApiTestSuite test method *T04_POST_AddNewCountryTest*

## How To Run:

 **Method#1**
 - Goto ```src/test/java ``` > ```com.hellofresh.api.tests"```
 - Right click on ```"ApiTestSuite.java"``` > ```Run As``` > ```TestNG Test```
 
 
 **Method#2**
 - Open "Command Prompt"
 - Goto Project directory
 - type following command : 
 
 			> mvn install
 			> mvn test  or mvn -DApiTestSuite test
 			

## Logs: 

Logs are stored under respective date folder under "logs" directory (Created after initial run)

**Successful Run via UI:**

![alt text](https://raw.githubusercontent.com/far11ven/hellofresh-images/master/images/API%20Test%20Run_Via%20UI.JPG)


**Via Command line**

![alt text](https://raw.githubusercontent.com/far11ven/hellofresh-images/master/images/API%20Test%20Run_Command%20Line.png)
