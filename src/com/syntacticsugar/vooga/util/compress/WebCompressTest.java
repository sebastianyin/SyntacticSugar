package com.syntacticsugar.vooga.util.compress;

import java.io.File;

import org.json.JSONObject;

import com.syntacticsugar.vooga.util.webconnect.FileHelper;
import com.syntacticsugar.vooga.util.webconnect.JSONHelper;
import com.syntacticsugar.vooga.util.webconnect.WebConnector;


public class WebCompressTest {
	public static void main(String args[]) {
		File selectedFile = new File("src/com/syntacticsugar/vooga/util/compress/Untitled.xml");
		System.out.println("Regular Test:");
		String inputString = FileHelper.readFile(selectedFile);
		System.out.printf("%30s: %8d\n", "Input string size", inputString.length());
		String compressed = Compressor.compress16(inputString);
		System.out.printf("%30s: %8d\n", "Compressed string size", compressed.length());
		
		//send compressed item into database
		//JSONObject gameRequest = JSONHelper.createXMLJSON("Jiawei Zhang", "Test Game 1", "Testing whether compression is working", compressed);
		//String response = WebConnector.postXML(gameRequest);
		//System.out.println("WebConnector Response: "+response);
		
		//System.out.println(WebConnector.getXMLs());
		
		JSONObject game = WebConnector.getXML(38);
		String compressedXML = JSONHelper.extractXML(game);
		System.out.printf("%30s: %8b\n", "Compressed Equal", compressed.equals(compressedXML));
		
		String uncompressed = Compressor.decompress16(compressedXML);
		//System.out.println(uncompressed);
		System.out.printf("%30s: %8d\n", "Uncompressed string size", uncompressed.length());
		System.out.printf("%30s: %8b\n", "Equal", inputString.equals(uncompressed));
		//System.out.println();
	}
}
