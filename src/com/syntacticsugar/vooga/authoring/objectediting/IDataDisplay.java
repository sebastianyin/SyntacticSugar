package com.syntacticsugar.vooga.authoring.objectediting;

public interface IDataDisplay<T> {

	/* Interface utilized by both AttributeViewer and CollisionViewer, for displaying data of any type within the display pane*/
	
	public void displayData(T data);
	
	public void clearDisplay();
	
	public T getData();
	
}
