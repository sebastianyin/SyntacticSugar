package com.syntacticsugar.vooga.authoring.level;

public interface ITabbedManager<T> extends IManager {
	
	/**
	 * Append a new tab to the end of this tab.
	 */
	public void append();
	
	/**
	 * Remove the currently selected tab.
	 */
	public void remove();
	
	/**
	 * Returns the currently selected data view in the tab pane.
	 * @return
	 */
	public IDataSelector<T> getCurrentView();
	
}
