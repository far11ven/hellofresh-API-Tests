package com.hellofresh.api.utilities;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLHandler {

	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;
	static String fileName = "./src/test/resources/config/XMLData.properties";

	public XMLHandler(String xmlData){

		writeToXMLFile(xmlData);

		try {
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(fileName);
			doc.normalize();

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}

	}

	private void writeToXMLFile(String xmlData) {

		//following code clears existing data from the file

		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName);
			writer.print("");
			writer.close();

		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		//following code adds new data to the file

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = xmlData;

			fw = new FileWriter(fileName);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

	public NodeList getNodeList(String nodeName) {

		NodeList nList = doc.getElementsByTagName(nodeName);
		return nList;
	}

	public int getNodeListLength(NodeList nodeName) {

		int nLength = nodeName.getLength();
		return nLength;
	}

	public Node getNode(NodeList nodeName, int itemIndex) {

		Node nNode = nodeName.item(itemIndex);
		return nNode;
	}

	public String getAttributeValue(Node nodeItem, String attributeName) {

		String attributeValue = null;
		Node nNode = nodeItem;
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			attributeValue = eElement.getElementsByTagName(attributeName).item(0).getTextContent();

		}
		return attributeValue;
	}

}