package com.syntacticsugar.vooga.authoring.css;

import javafx.scene.Node;

public class CSSWizard {
	public CSSWizard(){
	}
	
	public static void tileOpacityOff(Node node){
		node.getStyleClass().add("tile-select-off");
		node.getStyleClass().remove("tile-select-on");
	}
	
	public static void tileOpacityOn(Node node) {
		node.getStyleClass().add("tile-select-on");
		node.getStyleClass().remove("tile-select-off");
	}
}
