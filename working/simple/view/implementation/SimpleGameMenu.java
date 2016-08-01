package simple.view.implementation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import simple.view.IViewManager;
import simple.view.SimpleAbstractMenu;

/**
 * 
 * Organizes and manages the game window, including starting/pausing and displaying menu. 
 *
 */

public class SimpleGameMenu extends SimpleAbstractMenu{

	public SimpleGameMenu(IViewManager manager, double width, double height, String title) {
		super(manager, width, height, title);
		generateOptions(createNewGameButton(),createLoadGameButton(), createHelpButton());
	}

	private Node createNewGameButton() {
		Button newGame = createButton("New Game");
		newGame.setOnAction(e -> launchGame());
		return newGame;
	}

	private Node createHelpButton() {
		Button newGame = createButton("Help");
		//set action to load game
		return newGame;
	}

	private Node createLoadGameButton() {
		Button newGame = createButton("Load Game");
		//set action to launch help file
		return newGame;
	}

}
