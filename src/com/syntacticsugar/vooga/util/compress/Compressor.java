package com.syntacticsugar.vooga.util.compress;

public class Compressor {

	public static String compress(String input) {
		return LZString.compress(input);
	}

	public static String decompress(String input) {
		return LZString.decompress(input);
	}

	public static String compress16(String input) {
		return LZString.compressToUTF16(input);
	}

	public static String decompress16(String input) {
		return LZString.decompressFromUTF16(input);
	}

	public static String compress64(String input) {
		return LZString.compressToBase64(input);
	}

	public static String decompress64(String input) {
		try {
			return LZString.decompressFromBase64(input);
		} catch (Exception e) {
			return "";
		}
	}

}
