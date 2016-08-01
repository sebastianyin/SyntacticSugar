package com.syntacticsugar.vooga.authoring.objectediting;

import javafx.scene.Node;

public interface IVisualElement {

	/*Interface implemented by both AttributeViewer and CollisionViewer to return their GUI elements as a single node.
	This single method is utilized in the objectEditor class for adding the attributeViewer and collisionViewer to the
	ObjectEditor' main pane*/
	
	/**
	 * Return the JavaFX Node used to display this UI element.
	 * @return
	 */
	public Node getView();
	
}
