package com.syntacticsugar.vooga.gameplayer.view.gameview;

import javafx.scene.layout.Pane;

public interface ISimpleGameView {
	
	/**
	 * Adds an object view.
	 * 
	 * @param obj the view
	 */
	public void addObjectView(Pane obj);
	
	/**
	 * Removes an object view.
	 * 
	 * @param obj the view
	 */
	public void removeObjectView(Pane obj);
	
	/**
	 * Returns the GameView's scaling factor.
	 * 
	 * @return the scaling factor
	 */
	public double getScalingFactor();

}
