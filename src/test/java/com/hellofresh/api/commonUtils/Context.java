package com.hellofresh.api.commonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Context {

	private Map<String, String> tempData = new HashMap();
	private Map<String, Map<String, String>> storyData = new HashMap();
	private Map<String, ArrayList<String>> valueFromStoryTemp = new HashMap();


	public void saveData(String key, String value) {
		this.tempData.put(key,  value);

	}

	public void saveData(String key, Map<String, String> storyDataMap) {
		this.storyData.put(key,  storyDataMap);

	}

	public String readData(String key) {
		return (String) this.tempData.get(key);

	}

	public String readData(String storyName, String key) {

		return this.storyData.containsKey(storyName) ? (String) ((Map) this.storyData.get(storyName)).get(key) : null;

	}


	public void saveStoryExampleValues(String storyName, String value) {

		ArrayList values = new ArrayList();

		if(this.valueFromStoryTemp.containsKey(storyName)) {
			values = (ArrayList) this.valueFromStoryTemp.get(storyName);
			values.add(value);
			this.valueFromStoryTemp.put(storyName, values);
		} else {

			values.add(value);
			this.valueFromStoryTemp.put(storyName, values);
		}
	}

	public ArrayList<String>  getValuesAsArray(String storyName, String value) {

		ArrayList values = new ArrayList();

		if(this.valueFromStoryTemp.containsKey(storyName)) {
			values = (ArrayList) this.valueFromStoryTemp.get(storyName);
			return values;
		} else {
			return null;
		}
	}

}