
import com.syntacticsugar.vooga.menu.MainMenu;

import javafx.application.Application;
import javafx.stage.Stage;
//import xml.data.ObjectData;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage astage) {
		new MainMenu();
	}
}
