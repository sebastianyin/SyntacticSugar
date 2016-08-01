package com.syntacticsugar.vooga.authoring.fluidmotion;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TranslateTransitionWizard {
	public static Animation applyEffect(Node node, Duration duration){
		TranslateTransition tt = new TranslateTransition(duration,node);
		return tt;
	}
}
