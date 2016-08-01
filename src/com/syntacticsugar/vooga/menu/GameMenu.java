package com.syntacticsugar.vooga.menu;

import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.PulsingFadeWizard;
import com.syntacticsugar.vooga.gameplayer.manager.GameManager;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.xml.data.GameData;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class GameMenu extends AbstractMenu {

	private double FRAME_LENGTH;
	private double GAME_SIZE;
	private GameData myGame;

	public GameMenu(GameData data) {
		super(data.getName());
		myGame = data;
		initializeVariables();
	}

	private void initializeVariables() {
		FRAME_LENGTH = 1.0 / getDoubleProperty("FrameLength");
		GAME_SIZE = getDoubleProperty("DefaultGameSize");
	}

	@Override
	protected void initializeOptions(BorderPane pane) {
		Button launchGame = createButton(ResourceManager.getString("play_game"), e -> launchNewEngine());
		PulsingFadeWizard.applyEffect(launchGame).play();
		generateOptions(pane, launchGame);
	}

	private void launchGame(IVoogaApp app) {
		app.assignCloseHandler(e -> animatedShowStage());
	}

	private void launchNewEngine() {
		hideStage();
		launchGame(new GameManager(null, GAME_SIZE, myGame, FRAME_LENGTH));
	}

}
