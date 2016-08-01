package com.syntacticsugar.vooga.authoring.objectediting;

import com.syntacticsugar.vooga.xml.data.IData;

public interface IDataClipboard {
	
	/*This interface is implemented by ObjectEditor class, so that the current instance of IData can be returned and sent to backend*/
	
	public IData obtainSelectedIData();
}
