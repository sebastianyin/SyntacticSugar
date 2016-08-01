package com.syntacticsugar.vooga.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XMLHandler<T> implements IXML<T> {

	private XStream myXStream;

	public XMLHandler() {
		this.myXStream = new XStream(new StaxDriver());
	}

	private String generateXML(T obj) {
		try {
			String xml = myXStream.toXML(obj);
			System.out.println("XML size " + xml.length());
			return xml;
		} catch (Exception e) {
			System.out.println("XML Write Error");
			return "";
		}
	}

	public static String fileToString(File f) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}
			br.close();
			return sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return "";
		} catch (IOException e) {
			System.out.println("Error reading file");
			return "";
		}
	}

	public static void writeXMLToFile(String xml, String path) {
		try {
			PrintWriter out = new PrintWriter(path);
			out.println(xml);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Failed to write XML to file");
		}
	}

	@Override
	public void write(T object, File f) {
		writeXMLToFile(generateXML(object), f.getPath());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T read(File f) {
		String xml = fileToString(f);
		try {
			T object = (T) myXStream.fromXML(xml);
			System.out.println(object);
			return object;
		} catch (Exception e) {
			System.out.println("Error reading XML");
			return null;
		}
	}

}
