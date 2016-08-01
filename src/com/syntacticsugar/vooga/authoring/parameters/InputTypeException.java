package com.syntacticsugar.vooga.authoring.parameters;

public class InputTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InputTypeException(String expectedType, String input) {
		super(format(expectedType, input));
	}
	
	/**
	 * Written by Prof. Duvall, provided in ReflectionException.java
	 * @param message
	 * @param args
	 * @return
	 */
    private static String format(String expectedType, String input) {
        return String.format("Expected type: %s\nReceived Input: %s", expectedType, input);
    }
	
}
