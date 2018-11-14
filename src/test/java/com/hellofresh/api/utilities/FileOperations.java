package com.hellofresh.api.utilities;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;


public class FileOperations {

	public static void writeToFile(String filePath, String content) {
		try {

			FileWriter fr = new FileWriter(new File(filePath));

			BufferedWriter br = new BufferedWriter(fr);

			br.append(content);
			br.flush();
			br.close();
			fr.close();

		} catch (IOException e) {

			System.out.println("Problem in Writing");

		}


	}

	public static String readFromFile(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	/*finally {
			br.close();
		}*/	
		
		return null;
	}

	/*public static String readFromFile(String filePath) {
		BufferedReader br = null;
		FileReader fr = null;

		String finalContent = null;
		String content = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			while ((content = br.readLine()) != null) {

				finalContent = finalContent + content;

			}

			return content;

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return content;

	}
	 */

}
