package com.syntacticsugar.vooga.authoring.fluidmotion;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;

public class SequentialTransitionWizard {
	public static Animation sequence(Animation... animations){
		SequentialTransition seq = new SequentialTransition();
		seq.getChildren().addAll(animations);		
		return seq;
	}
}
