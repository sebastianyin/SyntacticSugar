package com.syntacticsugar.vooga.gameplayer.universe.userinput;

import java.util.Collection;

import javafx.scene.input.KeyCode;

public interface IKeyInputStorage {

	/**
	 * Return the list of all the keys currently being pressed.
	 * @return
	 */
	public Collection<KeyCode> getCurrentKeyInput();
	
}
