package com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion;

import com.syntacticsugar.vooga.authoring.fluidmotion.FadeTransitionWizard;
import com.syntacticsugar.vooga.authoring.fluidmotion.FluidGlassBall;
import com.syntacticsugar.vooga.authoring.fluidmotion.ParallelTransitionWizard;
import com.syntacticsugar.vooga.authoring.fluidmotion.TranslateTransitionWizard;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class DirectionalFadeWizard {

	public static Animation applyEffect(Node node, Duration duration, Interpolator interp, double fromX, double byX,
			int cycleCount, boolean fadeIn) {
		TranslateTransition tt = (TranslateTransition) TranslateTransitionWizard.applyEffect(
												node,
												duration);
		tt.setInterpolator(interp);
		tt.setFromX(fromX);
		tt.setByX(byX);
		tt.setCycleCount(cycleCount);

		FadeTransition ft = (FadeTransition) FadeTransitionWizard.applyEffect(
												node, 
												duration, 
												cycleCount, 
												fadeIn);
		return ParallelTransitionWizard.parallelize(tt, ft);
	}

	public static Animation applyEffect(Node node) {
		return applyEffect(node, 
							FluidGlassBall.getDirectionalFadeDuration(),
							FluidGlassBall.getDirectionalFadeInterpolator(), 
							FluidGlassBall.getDirectionalFadeOffsetWidth(),
							-1 * FluidGlassBall.getDirectionalFadeOffsetWidth(), 
							FluidGlassBall.getFadeCycleCount(), 
							true);
	}
}
