package com.syntacticsugar.vooga.gameplayer.view.gameview;

import javafx.scene.layout.Pane;

public interface IScalingFactorContainer {
	
	/**
	 * Returns the GameView's scaling factor.
	 * 
	 * @return the scaling factor
	 */
	public double getScalingFactor();
	
	/**
	 * Adds an object to the GameView.
	 * 
	 * @param myObject the object
	 */
	public void addObjectView(Pane myObject);

}
