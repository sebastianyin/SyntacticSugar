package com.syntacticsugar.vooga.authoring.fluidmotion;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class FadeTransitionWizard {
	
	public static Animation applyEffect(Node node, Duration timeDuration, int cycleNo, boolean fadeIn){
		return (fadeIn) ? fadeIn(node, timeDuration,cycleNo): fadeOut(node, timeDuration,cycleNo);
	}
	
	public static Animation fadeOut(Node node, Duration timeDuration, int cycleNo){
		FadeTransition ft = initFadeTransitionObject(node, timeDuration);
		ft.setFromValue(FluidGlassBall.getFadeOutOpacityStart());
		ft.setToValue(FluidGlassBall.getFadeOutOpacityEnd());
		return ft;
	}

	public static Animation fadeOut(Node node, Duration timeDuration, double initVal, double endVal, int cycleNo) {
		FadeTransition ft = fade(node, timeDuration, initVal, endVal);
		return ft;
	}

	public static Animation fadeIn(Node node, Duration timeDuration, int cycleNo) {
		FadeTransition ft = initFadeTransitionObject(node, timeDuration);
		ft.setFromValue(FluidGlassBall.getFadeInOpacityStart());
		ft.setToValue(FluidGlassBall.getFadeInOpacityEnd());
		return ft;
	}
	
	public static Animation fadeIn(Node node, Duration timeDuration, double initVal, double endVal, int cycleNo) {
		FadeTransition ft = fade(node, timeDuration, initVal, endVal);
		return ft;
	}

	private static FadeTransition fade(Node node, Duration timeDuration, double initVal, double endVal) {
		FadeTransition ft = initFadeTransitionObject(node, timeDuration);
		ft.setFromValue(initVal);
		ft.setToValue(endVal);
		return ft;
	}

	private static FadeTransition initFadeTransitionObject(Node node, Duration timeDuration) {
		FadeTransition ft = new FadeTransition(timeDuration, node);
		ft.setCycleCount(1);
		return ft;
	}


}
