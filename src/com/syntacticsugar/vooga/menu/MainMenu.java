package com.syntacticsugar.vooga.menu;

import com.syntacticsugar.vooga.authoring.AuthoringScreenManager;
import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.PulsingFadeWizard;
import com.syntacticsugar.vooga.help.HelpManager;
import com.syntacticsugar.vooga.social.SocialCenter;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.properties.PropertiesManager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenu extends AbstractMenu {
	private static PropertiesManager propManager = new PropertiesManager("com/syntacticsugar/vooga/resources/View");
	private static PropertiesManager helpPropManager = new PropertiesManager("com/syntacticsugar/vooga/resources/Help");

	public MainMenu() {
		super(propManager.getProperty("ProgramTitle"));
	}

	@Override
	protected void initializeOptions(BorderPane pane) {
		Button launchGame = createButton(ResourceManager.getString("play_game"), e -> launch(new GameChooser()));
		Button launchEditor = createButton(ResourceManager.getString("edit_game"), e -> launch(new AuthoringScreenManager()));
		Button launchSocial = createButton(ResourceManager.getString("be_social"), e -> launch(new SocialCenter()));
		Button launchHelp = createButton(ResourceManager.getString("help_label"), e -> launchHelpView(helpPropManager.getProperty("help")));
		PulsingFadeWizard.attachPulsingHandlers(launchGame,launchEditor, launchSocial, launchHelp);
		generateOptions(pane, launchGame, launchEditor, launchSocial, launchHelp);
	}

	private void launch(IVoogaApp app) {
		app.assignCloseHandler(e -> animatedShowStage());
		hideStage();
	}
	private void launchHelpView(String url) {
		Stage stage = new Stage();
		stage.setTitle(ResourceManager.getString("help_label"));
        Scene scene = new Scene(new HelpManager(url),900,500);
        stage.setScene(scene);
        stage.show();
	}
}
