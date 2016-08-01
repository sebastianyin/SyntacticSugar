package com.syntacticsugar.vooga.authoring.fluidmotion;

import javafx.animation.Interpolator;
import javafx.util.Duration;

public class FluidGlassBall {	
	// All durations are in milliseconds. 
	
	private static final Duration FADE_DURATION = Duration.millis(150); 
	private static final int FADE_IN_OPACITY_START = 0;
	private static final int FADE_IN_OPACITY_END =1;
	private static final int FADE_OUT_OPACITY_START = FADE_IN_OPACITY_END;
	private static final int FADE_OUT_OPACITY_END = FADE_IN_OPACITY_START;
	private static final int FADE_CYCLE_COUNT = 1;
	
	// DirectionalFade
	private static final Duration DIRECTIONAL_FADE_DURATION = Duration.millis(300);
	private static final Interpolator DIRECTIONAL_FADE_INTERPOLATOR = Interpolator.EASE_IN;
	private static final double DIRECTIONAL_FADE_OFFSET_WIDTH = 25;
	private static final int DIRECTIONAL_FADE_CYCLE_COUNT = 1;
	// PreviewTile SequentialTransition
	private static final Duration PREVIEW_TILE_PULSE_DURATION = Duration.millis(300);
	
	// PulsingFade Transition
	private static final double PULSING_FADE_OPACITY_MIN = 0.6;
	private static final double PULSING_FADE_OPACITY_MAX = 1;
	
	public static double getPulsingFadeOpacityMax() {
		return PULSING_FADE_OPACITY_MAX;
	}
	public static double getPulsingFadeOpacityMin() {
		return PULSING_FADE_OPACITY_MIN;
	}
	public static Duration getPreviewTilePulseDuration() {
		return PREVIEW_TILE_PULSE_DURATION;
	}
	public static Duration getDirectionalFadeDuration() {
		return DIRECTIONAL_FADE_DURATION;
	}
	public static Interpolator getDirectionalFadeInterpolator() {
		return DIRECTIONAL_FADE_INTERPOLATOR;
	}
	public static double getDirectionalFadeOffsetWidth() {
		return DIRECTIONAL_FADE_OFFSET_WIDTH;
	}
	public static int getDirectionalFadeCycleCount() {
		return DIRECTIONAL_FADE_CYCLE_COUNT;
	}

	public static Duration getFadeDuration() {
		return FADE_DURATION;
	}
	public static int getFadeInOpacityStart() {
		return FADE_IN_OPACITY_START;
	}
	public static int getFadeInOpacityEnd() {
		return FADE_IN_OPACITY_END;
	}
	public static int getFadeOutOpacityStart() {
		return FADE_OUT_OPACITY_START;
	}
	public static int getFadeOutOpacityEnd() {
		return FADE_OUT_OPACITY_END;
	}
	public static int getFadeCycleCount() {
		return FADE_CYCLE_COUNT;
	}

}
