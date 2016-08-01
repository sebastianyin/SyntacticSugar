package com.syntacticsugar.vooga.authoring.level;

import java.util.Observable;

import javafx.scene.image.ImageView;

public class PreviewImage extends Observable{
	private ImageView preview;

	public ImageView getPreview() {
		return preview;
	}

	public void setPreview(ImageView preview) {
		this.preview = preview;
	}


}
