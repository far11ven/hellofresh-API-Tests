package com.hellofresh.api.utilities;

import org.json.JSONObject; 
import org.json.JSONArray;
import org.json.JSONException; 
/**
 * This class includes method for dealing with JSON
 * @author Kushal Bhalaik
 *
 */

public class JSONOperations {

	/**
	 * This method returns a JSON element as a string, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static String getJSONElement(String jsonInput, String element) {

		JSONObject base = new JSONObject(jsonInput); 

		String jArray = base.getString(element); 

		return jArray; 

	}

	/**
	 * This method returns a JSON element as a string, when JSON object is passed
	 * @author Kushal Bhalaik
	 *
	 */


	public static String getJSONElement(JSONObject jsonInput, String element) {

		String jArray = jsonInput.getString(element); 

		return jArray; 

	}

	/**
	 * This method returns a JSON element as an Integer, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static String getJSONElementInt(String jsonInput, String element) {
		JSONObject base = new JSONObject(jsonInput); 

		String jArray = base.get(element).toString(); 

		return jArray; 
	}

	/**
	 * This method returns a JSON element as an Integer, when JSON object is passed
	 * @author Kushal Bhalaik
	 *
	 */


	public static String getJSONElementInt(JSONObject jsonInput, String element) {
		String jArray = jsonInput.get(element).toString(); 

		return jArray; 
	}

	/**
	 * This method returns a JSON element as a boolean, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean getJSONElementBool(String jsonInput, String element) {

		JSONObject base = new JSONObject(jsonInput); 

		boolean jArray = base.getBoolean(element); 

		return jArray; 

	}

	/**
	 * This method returns a JSON element as a boolean, when JSON object is passed
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean getJSONElementBool(JSONObject jsonInput, String element) {
		boolean jArray = jsonInput.getBoolean(element); 

		return jArray; 

	}

	/**
	 * This method returns a JSON Object, from parent JSON object which is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static JSONObject getJSONObject(String jsonInput, String childObject) {

		JSONObject base = new JSONObject(jsonInput); 

		JSONObject child = base.getJSONObject(childObject); 
		return child; 


	}

	/**
	 * This method returns a JSON Object, from parent JSON object which is passed
	 * @author Kushal Bhalaik
	 *
	 */

	public static JSONObject getJSONObject(JSONObject jsonInput, String childObject) {


		JSONObject child = jsonInput.getJSONObject(childObject); 
		return child; 

	}

	/**
	 * This method returns a JSON Array, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static JSONArray getJSONArray(String jsonInput, String element) {
		JSONObject base = new JSONObject(jsonInput); 

		JSONArray jArray = base.getJSONArray(element); 

		return jArray; 
	}


	/**
	 * This method returns a JSON Array as a string, when JSON object is passed
	 * @author Kushal Bhalaik
	 *
	 */

	public static JSONArray getJSONArray(JSONObject jsonInput, String element) {
		JSONArray jArray = jsonInput.getJSONArray(element); 

		return jArray; 
	}

	/**
	 * This method validates if an JSON object is present, when JSON object is passed
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean isJSONObjectPresent(JSONObject jsonInput, String object) {

		try {
			jsonInput.getJSONObject(object); 
			return true;
			
		} catch (JSONException e) {
			return false;
		}
	}
	
	
	/**
	 * This method validates if an JSON object is present, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean isJSONObjectPresent(String jsonInput, String object) {
		try {
			JSONObject base = new JSONObject(jsonInput); 
			base.getJSONObject(object); 
			return true;
			
		} catch (JSONException e) {
			return false;
		}
	}
	
	/**
	 * This method validates if an JSON object is present, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean isJSONElementPresent(String jsonInput, String elementName) {
		try {
			JSONObject base = new JSONObject(jsonInput);
			getJSONElement(base, elementName);
			return true;
			
		} catch (JSONException e) {
			return false;
		}
	}
	
	/**
	 * This method validates if an JSON object is present, when JSON object is passed as  JSON Object
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean isJSONElementPresent(JSONObject jsonInput, String elementName) {
		try {
			JSONObject base = jsonInput; 
			getJSONElement(base, elementName);
			return true;
			
		} catch (JSONException e) {
			return false;
		}
	}
	
	/**
	 * This method validates if an JSON object is present, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean isJSONElementIntPresent(String jsonInput, String elementName) {
		try {
			JSONObject base = new JSONObject(jsonInput); 
			getJSONElementInt(base, elementName);
			return true;
			
		} catch (JSONException e) {
			return false;
		}
	}
	
	/**
	 * This method validates if an JSON object is present, when JSON object is passed
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean isJSONElementIntPresent(JSONObject jsonInput, String elementName) {
		try {
			JSONObject base = jsonInput;
			getJSONElementInt(base, elementName);
			return true;
			
		} catch (JSONException e) {
			return false;
		}
	}
	
	/**
	 * This method validates if an JSON object is present, when JSON object is passed as a String
	 * @author Kushal Bhalaik
	 *
	 */

	public static boolean isJSONElementBoolPresent(String jsonInput, String elementName) {
		try {
			JSONObject base = new JSONObject(jsonInput); 
			getJSONElementBool(base, elementName);
			return true;
			
		} catch (JSONException e) {
			return false;
		}
	}

	/**
	 * This method returns a JSON object in pretty format, when JSON Object is passed as a plain String
	 * @author Kushal Bhalaik
	 *
	 */

	public static String formatJSON(String jsonInput){

		String[] str = jsonInput.split(","); 

		for (int i = 1; i < str.length; i++){

			if (i == 1){

				str[0] = str[0] + ", \n"; 
			}


			str[0] = str[0] + (str[i] + ", \n"); 

		}
		String context = str[0]; 
		return context; 
	}


	/**
	 * This method returns a JSON object in html format, when JSON Object is passed as a plain String
	 * @author Kushal Bhalaik
	 *
	 */

	public static String formattoHtml(String jsonInput){

		String[] str = jsonInput.split(","); 

		for (int i = 1; i < str.length; i++){

			if (i == 1){

				str[0] = "<pre>sdsadsadsad" + str[0] + ", <pre>"; 
			}
			str[0] = str[0] +(str[i] + ", <pre>"); 

		}
		String context = str[0]; 

		return context; 
	}
}