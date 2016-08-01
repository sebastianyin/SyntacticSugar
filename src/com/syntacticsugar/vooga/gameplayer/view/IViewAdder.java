package com.syntacticsugar.vooga.gameplayer.view;

import com.syntacticsugar.vooga.gameplayer.objects.IViewableObject;

public interface IViewAdder extends IViewController{

	/**
	 * Adds an object to the view.
	 * 
	 * @param obj the object
	 */
	public void addViewObject(IViewableObject obj);
	
}
