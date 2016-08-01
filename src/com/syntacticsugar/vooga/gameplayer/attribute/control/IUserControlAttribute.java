package com.syntacticsugar.vooga.gameplayer.attribute.control;

import com.syntacticsugar.vooga.gameplayer.universe.userinput.IKeyInputStorage;

public interface IUserControlAttribute {
	
	/**
	 * Passes a key input into the User Control Attribute
	 * @param universeKeyInput the key input
	 */
	public void updateKeyInput(IKeyInputStorage universeKeyInput);
	
}

