package com.syntacticsugar.vooga.gameplayer.view.gamepanels;

import java.util.Observable;
import java.util.Observer;

import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.PulsingFadeWizard;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class GlobalDataPanel extends HBox implements Observer {

	private final static double CONTENT_SPACING = 20.0;
	private Label myScore;
	private final String text = "Score: ";
	private Button waveButton;

	public GlobalDataPanel() {
		super(CONTENT_SPACING);
		this.setAlignment(Pos.CENTER_LEFT);
		myScore = new Label("Score: 0");
		myScore.getStyleClass().add("game-data");
		waveButton = new Button("Start Wave");
		PulsingFadeWizard.applyEffect(waveButton).play();
		waveButton.setAlignment(Pos.CENTER);
		waveButton.setMaxHeight(Double.MAX_VALUE);
		this.getChildren().addAll(waveButton, myScore);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int score = (int) arg1;
		myScore.setText(text + " " + Integer.toString(score));
	}
	
	public void setWaveButton(EventHandler<MouseEvent> onMouseClicked){
		waveButton.setOnMouseClicked(onMouseClicked);
	}

}
