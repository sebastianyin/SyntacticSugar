package simple.view.implementation;

import javafx.scene.Scene;
import javafx.stage.Stage;
import simple.view.IViewManager;

public class SimpleStartingScreenManager implements IViewManager{
	
	private final double WIDTH = 600.0;
	private final double HEIGHT = 600.0;
	private Stage myStage;
	
	public SimpleStartingScreenManager(Stage stage){
		SimpleStartingMenu startingScreen = new SimpleStartingMenu(this, WIDTH, HEIGHT, "Vooga Salad");
		Scene welcome = new Scene(startingScreen, WIDTH, HEIGHT);
		//The following lines can be refactored 
		myStage = stage;
		myStage.setScene(welcome);
		myStage.setTitle("Vooga Salad");
		myStage.show();
	}

	@Override
	public void launchGame() {
		myStage.close();
		new SimpleGameScreenManager();
	}
	
	public void launchEditor(){
		myStage.close();
		//create editor env, get buckets
	}

}
