package com.hellofresh.api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	static FileInputStream fis = null;
	static Properties pro = new Properties();

	public static String getProperty(String filePath, String propName) {

		try {
			System.out.println("FileReader searching for file at = " + filePath);
			fis = new FileInputStream(new File(filePath));
		}
		catch(FileNotFoundException e){

			System.out.println("FileReader could not find the requested file...!!");

		}


		try {
			pro.load(fis);
		}
		catch(IOException e){

			System.out.println("FileReader could not load the requested file...!!");

		}

		System.out.println("Retrieving value for property [" + propName +"] as :" + pro.getProperty(propName));
		return pro.getProperty(propName);
	}

}
