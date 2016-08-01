package com.syntacticsugar.vooga.menu;

import com.syntacticsugar.vooga.help.HelpManager;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.properties.PropertiesManager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelpMenu extends AbstractMenu {
	
	private PropertiesManager myPropertiesManager;

	public HelpMenu() {
		super(ResourceManager.getString("help_label"));
		myPropertiesManager = new PropertiesManager("com/syntacticsugar/vooga/resources/Help");
	}

	@Override
	protected void initializeOptions(BorderPane pane) {
		Button engineHelp = createButton(ResourceManager.getString("engine_help"), e -> launchEngineHelp());
		Button authoringHelp = createButton(ResourceManager.getString("authoring_help"), e -> launchAuthoringHelp());
		Button socialHelp = createButton(ResourceManager.getString("social_help"), e -> launchSocialHelp());
		generateOptions(pane, engineHelp, authoringHelp, socialHelp);
	}

	private void launchEngineHelp() {
		launch(myPropertiesManager.getProperty("enginehelp"));
	}

	private void launchAuthoringHelp() {
		launch(myPropertiesManager.getProperty("authoringhelp"));
	}

	private void launchSocialHelp() {
		launch(myPropertiesManager.getProperty("socialhelp"));
	}
	
	private void launch(String url) {
		Stage stage = new Stage();
		stage.setTitle(ResourceManager.getString("web_view"));
        Scene scene = new Scene(new HelpManager(url));
        stage.setScene(scene);
        stage.show();
	}
}
