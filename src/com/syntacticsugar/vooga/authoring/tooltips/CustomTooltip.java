package com.syntacticsugar.vooga.authoring.tooltips;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;

public abstract class CustomTooltip extends Tooltip {

	public CustomTooltip() {
		setGraphic(buildContentNode());
		setOnShowing(e -> updateContent());
	}
	
	protected abstract Node buildContentNode();
	
	protected abstract void updateContent();
	
}
