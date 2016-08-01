package simple.view.implementation;

import javafx.scene.Node;
import javafx.scene.control.Button;
import simple.view.IViewManager;
import simple.view.SimpleAbstractMenu;

/**
 * 
 * Menu implementation, allows choice Game/Editor
 * 
 */

public class SimpleStartingMenu extends SimpleAbstractMenu{

	public SimpleStartingMenu(IViewManager manager, double width, double height, String title) {
		super(manager, width, height, title);
		generateOptions(createLaunchGameButton(), createLaunchEditorButton());
	}

	private Node createLaunchEditorButton() {
		Button edit = createButton("Let's edit");
		edit.setOnAction(e -> launchEditor());
		return edit;
	}

	private Node createLaunchGameButton() {
		Button launch = createButton("Play!");
		launch.setOnAction(e -> launchGame());
		return launch;
	}

}
