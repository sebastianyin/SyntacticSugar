package com.syntacticsugar.vooga.authoring.level;

import java.util.Observable;

import javafx.scene.Node;

public interface IManager {

	/**
	 * Return the controller of this MVC element. Used to
	 * establish observer/observable relationships across elements.
	 * @return
	 */
	public Observable getObservableController();
	
	public Node getControlNode();
	
	public Node getViewNode();
	
}
