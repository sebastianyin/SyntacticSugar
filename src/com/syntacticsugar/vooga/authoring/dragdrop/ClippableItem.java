package com.syntacticsugar.vooga.authoring.dragdrop;

import java.io.Serializable;

public abstract class ClippableItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
