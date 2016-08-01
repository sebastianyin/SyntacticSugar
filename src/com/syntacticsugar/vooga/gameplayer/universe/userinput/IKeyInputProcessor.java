package com.syntacticsugar.vooga.gameplayer.universe.userinput;

import java.util.Collection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public interface IKeyInputProcessor extends IKeyInputStorage {

	/**
	 * Function to be called whenever the Game Manager registers a 
	 * key press event. Adds the key code to the list of current input.
	 * @param code
	 */
	public void receiveKeyPress(KeyCode code);
	
	/**
	 * Function to be called whenever the Game Manager registers a 
	 * key release event. Removes the key code from the list of current input.
	 * @param code
	 */
	public void receiveKeyRelease(KeyCode code);
	
	/**
	 * Function to be called whenever the Game Manager registers a 
	 * mouse event.
	 * @param mouseEvent
	 */
	public void receiveMouseEvent(MouseEvent mouseEvent);
	
	
	// IKeyInputStorage methods
	
	@Override
	public Collection<KeyCode> getCurrentKeyInput();
	
}
