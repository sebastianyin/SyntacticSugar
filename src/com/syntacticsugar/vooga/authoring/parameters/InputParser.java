package com.syntacticsugar.vooga.authoring.parameters;

import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class InputParser {

	/**
	 * Tries to parse a String input as a KeyCode value. Shows an AlertBox if
	 * the parse was unsuccessful and throws an InputTypeException.
	 * @param arg
	 * @return
	 * @throws InputTypeException
	 */
	public static KeyCode parseAsKeyCode(String arg) throws InputTypeException {
		String input = arg.trim().replace(" ", "").toUpperCase();
		KeyCode result = KeyCode.valueOf(input);
		if (result != null) 
			return result;
		InputTypeException exception = makeException("KeyCode", arg);
		showInputAlert(exception);
		throw exception;
	}

	/**
	 * Tries to parse a String input as a Double value. Shows an AlertBox if
	 * the parse was unsuccessful and throws an InputTypeException.
	 * @param arg
	 * @return
	 * @throws InputTypeException
	 */
	public static double parseAsDouble(String arg) throws InputTypeException {
		try {
			String input = arg.trim();
			double result = Double.parseDouble(input);
			return result;
		} catch (NumberFormatException e) {
			InputTypeException exception = makeException("Double", arg);
			showInputAlert(exception);
			throw exception;
		}
	}

	/**
	 * Tries to parse a String input as an Integer value. Shows an AlertBox if
	 * the parse was unsuccessful and throws an InputTypeException.
	 * @param arg
	 * @return
	 * @throws InputTypeException
	 */
	public static int parseAsInt(String arg) throws InputTypeException {
		try {
			String input = arg.trim();
			int result = Integer.parseInt(input);
			return result;
		} catch (NumberFormatException e) {
			InputTypeException exception = makeException("Integer", arg);
			showInputAlert(exception);
			throw exception;
		}
	}
	
	/**
	 * Tries to parse a String input as a Direction enum. Shows an AlertBox if
	 * the parse was unsuccessful and throws an InputTypeException.
	 * @param arg
	 * @return
	 */
	public static Direction parseAsDirection(String arg) throws InputTypeException {
		try {
			String input = arg.trim().replace(" ", "").toUpperCase();
			Direction result = Direction.valueOf(input);
			return result;
		} catch (NumberFormatException e) {
			InputTypeException exception = makeException("Direction", arg);
			showInputAlert(exception);
			throw exception;
		}
	}

	/**
	 * Tries to parse a String as a viable image path in the project directory.
	 * Throws an InputTypeException if the specified path is not a valid image.
	 * @param arg
	 * @return
	 */
	public static String parseAsImagePath(ClassLoader loader, String arg) {
		String input = arg;
		try {
			// Is this in the directory?
			@SuppressWarnings("unused")
			Image test = new Image(loader.getResourceAsStream(input));
			return arg;
		} catch (Exception e) {
			InputTypeException exception = makeException("Image Path", arg);
			showInputAlert(exception);
			throw exception;
		}
	}
	
	private static InputTypeException makeException(String type, String input) {
		return new InputTypeException(type, input);
	}

	private static void showInputAlert(InputTypeException e) {
		AlertBoxFactory.createObject(e.getMessage());
	}

}
