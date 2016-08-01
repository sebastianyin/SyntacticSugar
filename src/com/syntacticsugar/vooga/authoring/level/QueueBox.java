package com.syntacticsugar.vooga.authoring.level;

import com.syntacticsugar.vooga.authoring.objectediting.IVisualElement;
import com.syntacticsugar.vooga.authoring.tooltips.ObjectTooltip;
import com.syntacticsugar.vooga.xml.data.ObjectData;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class QueueBox implements IVisualElement {

	private VBox myView;
	private ImageView myImage;
	private ObjectData myData;

	public QueueBox(ObjectData data) {
		myData = data;
		myView = new VBox();
		addImage(data.getImagePath());
		Tooltip.install(myView, new ObjectTooltip(myData));
	}

	private void addImage(String path) {
		myImage = new ImageView(path);
		myImage.setFitHeight(50);

		myImage.setPreserveRatio(true);
		myView.getChildren().add(myImage);
	}

	@Override
	public Node getView() {
		return myView;
	}

}
