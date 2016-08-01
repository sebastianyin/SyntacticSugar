package com.syntacticsugar.vooga.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.DirectionalFadeWizard;
import com.syntacticsugar.vooga.authoring.fluidmotion.mixandmatchmotion.PulsingFadeWizard;
import com.syntacticsugar.vooga.util.ResourceManager;
import com.syntacticsugar.vooga.util.dirview.IConverter;
import com.syntacticsugar.vooga.util.dirview.IDirectoryViewer;
import com.syntacticsugar.vooga.xml.XMLFileFilter;
import com.syntacticsugar.vooga.xml.XMLHandler;
import com.syntacticsugar.vooga.xml.data.GameData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameChooser implements IVoogaApp, IDirectoryViewer<String> {

	private static final File myDirectory = new File(ResourceManager.getString("game_data"));
	private Stage myStage;
	private ObservableList<String> myGameNames;
	private ListView<String> myView;
	private Map<String, GameData> stringToGameData;
	private Scene myScene;
	private GameData selectedGameData;
	private Button startButton;
	private VBox chooserContentBox;

	public GameChooser() {
		myStage = new Stage();
		stringToGameData = new HashMap<String, GameData>();
		myGameNames = FXCollections.observableArrayList();
		showDirectoryContents(myDirectory, e -> getGameDescriptions(myDirectory));
		myView = new ListView<String>(myGameNames);
		myView.setId("game-chooser-listview");
		myView.setOnMouseClicked(e -> {
			if (myView.getSelectionModel().getSelectedItem() != null) {
				startButton.setDisable(false);
			}
			selectedGameData = stringToGameData.get(myView.getSelectionModel().getSelectedItem());
		});

		// myView.getItems().add("HEY");
		// myView.getItems().add("LOLCANO");
		myScene = new Scene(buildScene());
		myScene.getStylesheets().add("/com/syntacticsugar/vooga/gameplayer/css/menu.css");

		myStage.setScene(myScene);
		myStage.show();

	}

	private VBox buildScene() {
		chooserContentBox = new VBox(10);
		chooserContentBox.setId("game-chooser-vbox");
		Label title = new Label("Choose a game:");
		title.setAlignment(Pos.CENTER);
		title.setId("game-chooser-title");
		startButton = createButton("Start", e -> startGame());
		startButton.setDisable(true);
		PulsingFadeWizard.attachPulsingHandlers(startButton);
		chooserContentBox.getChildren().addAll(title, myView, startButton);
		return chooserContentBox;
	}

	private void startGame() {
		// SimpleFileChooser.saveGame(makeEmptyData(), new Stage());
		myStage.hide();
		GameData data = selectedGameData;
		// GameData data = makeEmptyData();
		System.out.println(data);
		System.out.println(data.getName());
		launchGame(new GameMenu(data));
	}

	private void launchGame(IVoogaApp app) {
		app.assignCloseHandler(e -> animatedShowStage());
		myStage.hide();
	}

	protected void animatedShowStage() {
		DirectionalFadeWizard.applyEffect(chooserContentBox).play();
		myStage.show();
	}

	@Override
	public void assignCloseHandler(EventHandler<WindowEvent> onclose) {
		myStage.setOnCloseRequest(onclose);

	}

	@Override
	public void showDirectoryContents(File directory, IConverter<String> fileConverter) {
		myGameNames.clear();
		myGameNames.addAll(fileConverter.getContents(directory));
	}

	private Collection<String> getGameDescriptions(File directory) {
		File[] files = directory.listFiles(new XMLFileFilter());
		XMLHandler<GameData> xml = new XMLHandler<>();
		Collection<String> names = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			GameData data = xml.read(f);
			String gamename = data.getName();
			names.add(gamename);

			stringToGameData.put(gamename, data);
		}

		return names;

	}

	protected Button createButton(String name, EventHandler<ActionEvent> onAction) {
		Button button = new Button(name);
		button.setFont(new Font(30));
		button.setMaxWidth(Double.MAX_VALUE);
		button.setOnAction(onAction);
		return button;
	}

/*	private static GameData makeEmptyData() {

		Collection<UniverseData> uni = new ArrayList<>();
		uni.add(generateTestUni());
		uni.add(generateTestUni());
		GlobalSettings settings = new GlobalSettings();
		GameData data = new GameData(uni, settings);
		data.setName("Go Bolts");

		return data;
	}*/

	/*private static UniverseData generateTestUni() {
		Collection<ObjectData> odata = new ArrayList<>();

		String enemyPath = "enemy_ghost_1.png";
		ObjectData enemyData = new ObjectData();
		Collection<IAttribute> enemyAttributes = new ArrayList<IAttribute>();
		HealthAttribute health = new HealthAttribute();
		health.setHealth(30.0);
		ScoreAttribute score = new ScoreAttribute();
		score.setScore(30);
		// ConstantMovementAttribute move = new
		// ConstantMovementAttribute(Direction.DOWN, 0.5);
		enemyAttributes.add(health);
		enemyAttributes.add(score);
		// enemyAttributes.add(move);
		AIMovementAttribute enemyMotion = new AIMovementAttribute();
		enemyMotion.setSpeed(4.5);
		enemyAttributes.add(enemyMotion);
		Map<GameObjectType, Collection<ICollisionEvent>> collisions = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		Collection<ICollisionEvent> enemyEvents = new ArrayList<ICollisionEvent>();
		enemyEvents.add(new HealthChangeEvent(10.0));
		collisions.put(GameObjectType.PLAYER, enemyEvents);
		enemyData.setType(GameObjectType.ENEMY);
		enemyData.setSpawnPoint(0, 100);
		enemyData.setWidth(40);
		enemyData.setHeight(40);
		enemyData.setImagePath(enemyPath);
		enemyData.setAttributes(enemyAttributes);
		enemyData.setCollisionMap(collisions);

		ObjectData enemyData2 = new ObjectData();
		Collection<IAttribute> enemyAttributes2 = new ArrayList<IAttribute>();
		HealthAttribute enemyHealth = new HealthAttribute();
		enemyHealth.setHealth(30.0);
		ScoreAttribute enemyScore = new ScoreAttribute();
		enemyScore.setScore(40);
		enemyAttributes2.add(enemyHealth);
		enemyAttributes2.add(enemyScore);
		AIMovementAttribute enemyMotion2 = new AIMovementAttribute();
		enemyMotion2.setSpeed(4.0);
		enemyAttributes2.add(enemyMotion2);
		Map<GameObjectType, Collection<ICollisionEvent>> collisions2 = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		Collection<ICollisionEvent> enemyEvents2 = new ArrayList<ICollisionEvent>();
		enemyEvents2.add(new HealthChangeEvent(10.0));
		enemyData2.setType(GameObjectType.ENEMY);
		enemyData2.setSpawnPoint(0, 300);
		enemyData2.setWidth(50);
		enemyData2.setHeight(50);
		enemyData2.setImagePath(enemyPath);
		enemyData2.setAttributes(enemyAttributes2);
		enemyData2.setCollisionMap(collisions2);

		// i changed ISimpleObject to SimpleObject, else addViewObject does not
		// work
		String playerPath = "player_pacman.png";
		String missilePath = "scenery_pink.png";

		ObjectData playerData = new ObjectData();
		Collection<IAttribute> attributes = new ArrayList<>();
		attributes.add(new HealthAttribute());
		attributes.add(new MovementControlAttribute());
		BombAttribute bomb = new BombAttribute(missilePath, 40.0, KeyCode.SPACE, 30, 60);
		BasicWeaponAttribute wpn = new BasicWeaponAttribute();
		// wpn.
		// attributes.add(wpn);
		attributes.add(new StunWeaponAttribute(missilePath, 1.0, KeyCode.SPACE, 8.0, 5.0, 10.0, 60));
		// attributes.add(bomb);
		HealthAttribute playerHealth = new HealthAttribute();
		playerHealth.setHealth(100.0);
		MovementControlAttribute playerMove = new MovementControlAttribute();
		playerMove.setSpeed(3.0);
		attributes.add(playerHealth);
		attributes.add(playerMove);
		playerData.setType(GameObjectType.PLAYER);
		playerData.setSpawnPoint(0, 0);
		playerData.setWidth(50);
		playerData.setHeight(50);
		playerData.setImagePath(playerPath);
		playerData.setAttributes(attributes);

		ObjectData enemyData3 = new ObjectData();
		Collection<IAttribute> enemyAttributes3 = new ArrayList<IAttribute>();
		HealthAttribute enemyHealth3 = new HealthAttribute();
		enemyHealth3.setHealth(40.0);
		ScoreAttribute enemyScore3 = new ScoreAttribute();
		enemyScore3.setScore(10);
		enemyAttributes3.add(enemyHealth3);
		enemyAttributes3.add(enemyScore3);
		// AIMovementAttribute AI3 = new AIMovementAttribute();
		// AI3.setSpeed(5.0);
		ConstantMovementAttribute cm = new ConstantMovementAttribute(Direction.DOWN, 1.0);
		enemyAttributes3.add(cm);
		Map<GameObjectType, Collection<ICollisionEvent>> collisions3 = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		Collection<ICollisionEvent> enemyEvents3 = new ArrayList<ICollisionEvent>();
		enemyEvents3.add(new HealthChangeEvent(10.0));
		collisions3.put(GameObjectType.PLAYER, enemyEvents3);
		enemyData3.setType(GameObjectType.ENEMY);
		enemyData3.setSpawnPoint(100, 300);
		enemyData3.setWidth(20);
		enemyData3.setHeight(20);
		enemyData3.setImagePath(enemyPath);
		enemyData3.setAttributes(enemyAttributes3);
		enemyData3.setCollisionMap(collisions3);

		odata.add(playerData);
		odata.add(enemyData);
		odata.add(enemyData2);
		odata.add(enemyData3);

		WaveData wdata = new WaveData(odata, 60);
		Collection<WaveData> sdata = new ArrayList<>();
		sdata.add(wdata);
		SpawnerData spawn = new SpawnerData(sdata);

		MapData map = new MapData(10, "scenery_grass_2.png");

		ArrayList<TowerData> towers = new ArrayList<>();
		String imgPath = "tower_1.png";
		TowerData towerData = new TowerData();
		towerData.setImagePath(imgPath);
		Collection<IAttribute> towerAttributes = new ArrayList<IAttribute>();
		HealthAttribute newHealth = new HealthAttribute();
		newHealth.setHealth(30.0);
		towerAttributes.add(newHealth);
		towerAttributes.add(new TowerBasicWeaponAttribute());
		// towerAttributes.add(new AIMovementAttribute(3));
		Map<GameObjectType, Collection<ICollisionEvent>> collisionst = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		Collection<ICollisionEvent> towerEvents = new ArrayList<ICollisionEvent>();
		towerEvents.add(new HealthChangeEvent(10.0));
		towerData.setType(GameObjectType.TOWER);

		towerData.setImagePath(imgPath);
		towerData.setAttributes(towerAttributes);
		towerData.setCollisionMap(collisionst);
		towerData.setWidth(100);
		towerData.setHeight(100);
		towerData.setCost(-60);
		towerData.setName("Rock tower");
		towers.add(towerData);

		String imgPath1 = "tower_4.png";
		TowerData towerData2 = new TowerData();
		towerData2.setImagePath(imgPath1);
		Collection<IAttribute> towerAttributes2 = new ArrayList<IAttribute>();
		HealthAttribute healthAtt = new HealthAttribute();
		healthAtt.setHealth(30.0);
		towerAttributes2.add(healthAtt);
		// towerAttributes.add(new AIMovementAttribute(3));
		Map<GameObjectType, Collection<ICollisionEvent>> collisionst2 = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		Collection<ICollisionEvent> towerEvents2 = new ArrayList<ICollisionEvent>();
		towerEvents2.add(new HealthChangeEvent(10.0));
		towerData2.setType(GameObjectType.TOWER);
		towerData2.setImagePath(imgPath);
		towerData2.setAttributes(towerAttributes2);
		towerData2.setCollisionMap(collisionst2);
		towerData2.setWidth(100);
		towerData2.setHeight(100);
		towerData2.setCost(-100);
		towerData2.setName("Another Rock tower");
		towers.add(towerData2);

		TowerListData td = new TowerListData(towers);

		LevelSettings lSetting = new LevelSettings(1000);

		return new UniverseData(spawn, td, map, lSetting, new ArrayList<>());
	}*/

}