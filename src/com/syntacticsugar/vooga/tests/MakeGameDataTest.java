package com.syntacticsugar.vooga.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.syntacticsugar.vooga.gameplayer.attribute.HealthAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.IAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.ScoreAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.AIMovementAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.ConstantMovementAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.Direction;
import com.syntacticsugar.vooga.gameplayer.attribute.movement.MovementControlAttribute;
import com.syntacticsugar.vooga.gameplayer.attribute.weapons.StunWeaponAttribute;
import com.syntacticsugar.vooga.gameplayer.event.ICollisionEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.HealthChangeEvent;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.util.simplefilechooser.SimpleFileChooser;
import com.syntacticsugar.vooga.xml.data.GameData;
import com.syntacticsugar.vooga.xml.data.GlobalSettings;
import com.syntacticsugar.vooga.xml.data.LevelSettings;
import com.syntacticsugar.vooga.xml.data.MapData;
import com.syntacticsugar.vooga.xml.data.ObjectData;
import com.syntacticsugar.vooga.xml.data.SpawnerData;
import com.syntacticsugar.vooga.xml.data.TowerData;
import com.syntacticsugar.vooga.xml.data.TowerListData;
import com.syntacticsugar.vooga.xml.data.UniverseData;
import com.syntacticsugar.vooga.xml.data.WaveData;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MakeGameDataTest {
	
	public static void main(String args[]) {
		SimpleFileChooser.saveGame(makeEmptyData(), new Stage());
	}
	
	private static GameData makeEmptyData() {

		Collection<UniverseData> uni = new ArrayList<>();
		uni.add(generateTestUni());
		uni.add(generateTestUni());
		GlobalSettings settings = new GlobalSettings();
		GameData data = new GameData(uni, settings);
		data.setName("Go Bolts");

		return data;
	}

	private static UniverseData generateTestUni() {
		Collection<ObjectData> odata = new ArrayList<>();

		String enemyPath = "enemy_ghost_1.png";
		ObjectData enemyData = new ObjectData();
		Collection<IAttribute> enemyAttributes = new ArrayList<IAttribute>();
		HealthAttribute health = new HealthAttribute();
		health.setHealth(30.0);
		ScoreAttribute score = new ScoreAttribute();
		score.setScore(30);
		//ConstantMovementAttribute move = new ConstantMovementAttribute(Direction.DOWN, 0.5);
		enemyAttributes.add(health);
		enemyAttributes.add(score);
		//enemyAttributes.add(move);
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
		attributes.add(new StunWeaponAttribute(missilePath, 1.0, KeyCode.SPACE, 8.0, 5.0, 10.0, 60));
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
		enemyData3.setSpawnPoint(150, 150);
		enemyData3.setWidth(100);
		enemyData3.setHeight(100);
		enemyData3.setImagePath(enemyPath);
		enemyData3.setAttributes(enemyAttributes3);
		enemyData3.setCollisionMap(collisions3);

		odata.add(playerData);
		odata.add(enemyData);
		odata.add(enemyData2);
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
		// towerAttributes.add(new AIMovementAttribute(3));
		Map<GameObjectType, Collection<ICollisionEvent>> collisionst = new HashMap<GameObjectType, Collection<ICollisionEvent>>();
		Collection<ICollisionEvent> towerEvents = new ArrayList<ICollisionEvent>();
		towerEvents.add(new HealthChangeEvent(10.0));
		towerData.setType(GameObjectType.ENEMY);

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

		return new UniverseData(spawn, td, map, lSetting,new ArrayList<>());
	}
}
