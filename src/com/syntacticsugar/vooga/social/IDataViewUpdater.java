package com.syntacticsugar.vooga.social;

import org.json.JSONException;

public interface IDataViewUpdater {

	public void update(int selected) throws JSONException;
	
	public void updateID(int selected);
	
}
