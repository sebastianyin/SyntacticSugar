package com.syntacticsugar.vooga.gameplayer.view;

import com.syntacticsugar.vooga.gameplayer.objects.IViewableObject;

public interface IViewRemover extends IViewController {
	
	/**
	 * Removes an object from the view.
	 * 
	 * @param obj the object
	 */
	public void removeViewObject(IViewableObject obj);

}
