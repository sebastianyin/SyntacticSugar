package com.syntacticsugar.vooga.util.compress;

import java.io.File;
import java.nio.file.Files;

public class CompressorTest {
	public static void main(String args[]) {
		//regularTest();
		utf16Test();
		//base64Test();
	}

/*	private static void regularTest() {
		//File selectedFile = new File("src/com/syntacticsugar/vooga/util/compress/Untitled.xml");
		File selectedFile = new File("C:/Users/jiawe/Desktop/testmap.xml");
		System.out.println("Regular Test:");
		String inputString = readFile(selectedFile);
		System.out.printf("%30s: %8d\n", "Input string size", inputString.length());
		String compressed = Compressor.compress(inputString);
		System.out.printf("%30s: %8d\n", "Compressed string size", compressed.length());
		String uncompressed = Compressor.decompress(compressed);
		System.out.printf("%30s: %8d\n", "Uncompressed string size", uncompressed.length());
		System.out.printf("%30s: %8b\n", "Equal", inputString.equals(uncompressed));
		System.out.println();
	}*/

	private static void utf16Test() {
		//File selectedFile = new File("src/com/syntacticsugar/vooga/util/compress/Untitled.xml");
		File selectedFile = new File("C:/Users/jiawe/Desktop/testmap.xml");
		System.out.println("UTF16 Test:");
		String inputString = readFile(selectedFile);
		System.out.printf("%30s: %8d\n", "Input string size", inputString.length());
		String compressed = Compressor.compress16(inputString);
		System.out.printf("%30s: %8d\n", "Compressed string size", compressed.length());
		String uncompressed = Compressor.decompress16(compressed);
		System.out.printf("%30s: %8d\n", "Uncompressed string size", uncompressed.length());
		System.out.printf("%30s: %8b\n", "Equal", inputString.equals(uncompressed));
		System.out.println();
	}

/*	private static void base64Test() {
		File selectedFile = new File("src/com/syntacticsugar/vooga/util/compress/Untitled.xml");
		System.out.println("Base64 Test:");
		String inputString = readFile(selectedFile);
		System.out.printf("%30s: %8d\n", "Input string size", inputString.length());
		String compressed = Compressor.compress64(inputString);
		System.out.printf("%30s: %8d\n", "Compressed string size", compressed.length());
		String uncompressed = Compressor.decompress64(compressed);
		System.out.printf("%30s: %8d\n", "Uncompressed string size", uncompressed.length());
		System.out.printf("%30s: %8b\n", "Equal", inputString.equals(uncompressed));
		System.out.println();
	}*/

	private static String readFile(File f) {
		try {
			byte[] inputBytes = Files.readAllBytes(f.toPath());
			return new String(inputBytes);
		} catch (Exception e) {

		}
		return "";
	}
}
