package com.syntacticsugar.vooga.authoring.level;

import java.util.Collection;

public interface IDataSelector<T> {

	/**
	 * Return a Collection containing all current data.
	 * @return
	 */
	public Collection<T> getData();
	
	/**
	 * Returns the currently selected data.
	 * @return
	 */
	public T getSelectedData();
	
	/**
	 * Remove the currently selected data.
	 */
	public void removeSelectedData();
	
	/**
	 * Add a new data element.
	 * @param toAdd
	 */
	public void addData(T toAdd);
	
	/**
	 * Clear all existing data.
	 */
	public void clearData();
	
	
}
