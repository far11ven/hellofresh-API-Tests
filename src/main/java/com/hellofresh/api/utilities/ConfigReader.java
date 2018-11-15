package com.hellofresh.api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * This class provides instance of ConfigReader for reading values from "config.properties"
*/
public class ConfigReader {

	String filePath = null;
	
	/*
	 * This method is constructor which initializes the filePath variable
	*/
	public ConfigReader(String filePath) {
		this.filePath = filePath;
	}
	
	/*
	 * This method provides reading values from "config.properties", takes one parameter "propName" whose value needs to be returned
	*/
	public String getProperty(String propertyName) {
		String propertyValue = null;

		Properties pro = new Properties();

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(this.filePath);
		} catch (FileNotFoundException e) {
			System.out.println("Property file not found!!");
			e.printStackTrace();
		}

		try {
			pro.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			
		}

		propertyValue = pro.getProperty(propertyName);

		return propertyValue;


	}



}
