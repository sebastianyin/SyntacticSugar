package com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion;

import com.syntacticsugar.vooga.authoring.fluidmotion.FadeTransitionWizard;
import com.syntacticsugar.vooga.authoring.fluidmotion.FluidGlassBall;
import com.syntacticsugar.vooga.authoring.fluidmotion.SequentialTransitionWizard;

import javafx.animation.Animation;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;

public class PulsingFadeWizard {
	public static Animation applyEffect(Node node){
		SequentialTransition seq = (SequentialTransition) SequentialTransitionWizard.sequence(
				FadeTransitionWizard.fadeIn(
						node, 
						FluidGlassBall.getPreviewTilePulseDuration(), 
						FluidGlassBall.getPulsingFadeOpacityMin(), 
						FluidGlassBall.getPulsingFadeOpacityMax(),
						FluidGlassBall.getDirectionalFadeCycleCount()),
				FadeTransitionWizard.fadeOut(
						node, 
						FluidGlassBall.getPreviewTilePulseDuration(), 
						FluidGlassBall.getPulsingFadeOpacityMax(), 
						FluidGlassBall.getPulsingFadeOpacityMin(), 
						FluidGlassBall.getDirectionalFadeCycleCount()));
		seq.setCycleCount(Integer.MAX_VALUE);
		return seq;
	}
	
	public static void attachPulsingHandlers(Node... nodes) {
		for (Node n : nodes) {
			Animation anim = PulsingFadeWizard.applyEffect(n);
			applyPulsingMouseHandlers(n, anim);
		}
	}

	public static void applyPulsingMouseHandlers(Node node, Animation anim) {
		node.setOnMouseEntered(e -> anim.play());
		node.setOnMouseExited(e -> {
			node.setOpacity(1);
			anim.stop();
		});
	}
}
