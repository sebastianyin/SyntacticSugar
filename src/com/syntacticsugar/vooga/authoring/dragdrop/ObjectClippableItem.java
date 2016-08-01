package com.syntacticsugar.vooga.authoring.dragdrop;

import com.syntacticsugar.vooga.xml.data.ObjectData;

public class ObjectClippableItem extends ClippableItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObjectData objectData;
	// private something else

	public ObjectData getObjectData() {
		return objectData;
	}

	protected void setObjectData(ObjectData objectData) {
		this.objectData = objectData;
	}
}
