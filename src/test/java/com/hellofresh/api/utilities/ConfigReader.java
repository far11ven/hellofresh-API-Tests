package com.hellofresh.api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	String filePath = null;

	public ConfigReader(String filePath) {
		this.filePath = filePath;
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		propertyValue = pro.getProperty(propertyName);

		return propertyValue;


	}



}
