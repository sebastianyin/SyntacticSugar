package com.syntacticsugar.vooga.gameplayer.universe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Observer;

import com.syntacticsugar.vooga.gameplayer.conditions.Conditions;
import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.manager.IEventManager;
import com.syntacticsugar.vooga.gameplayer.objects.GameObjectType;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.objects.towers.ITower;
import com.syntacticsugar.vooga.gameplayer.objects.towers.Tower;
import com.syntacticsugar.vooga.gameplayer.universe.map.GameMap;
import com.syntacticsugar.vooga.gameplayer.universe.map.IGameMap;
import com.syntacticsugar.vooga.gameplayer.universe.money.IMoney;
import com.syntacticsugar.vooga.gameplayer.universe.money.Money;
import com.syntacticsugar.vooga.gameplayer.universe.score.IScore;
import com.syntacticsugar.vooga.gameplayer.universe.score.Score;
import com.syntacticsugar.vooga.gameplayer.universe.spawner.ISpawner;
import com.syntacticsugar.vooga.gameplayer.universe.spawner.Spawner;
import com.syntacticsugar.vooga.gameplayer.view.IViewAdder;
import com.syntacticsugar.vooga.gameplayer.view.IViewRemover;
import com.syntacticsugar.vooga.xml.data.GlobalSettings;
import com.syntacticsugar.vooga.xml.data.LevelSettings;
import com.syntacticsugar.vooga.xml.data.MapData;
import com.syntacticsugar.vooga.xml.data.ObjectData;
import com.syntacticsugar.vooga.xml.data.SpawnerData;
import com.syntacticsugar.vooga.xml.data.TowerData;
import com.syntacticsugar.vooga.xml.data.TowerListData;
import com.syntacticsugar.vooga.xml.data.UniverseData;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class GameUniverse implements IGameUniverse {

	private Collection<IGameObject> myGameObjects;
	private Collection<ITower> myTowers;
	private SpawnYard mySpawnYard;
	private GraveYard myGraveYard;
	private Conditions myConditions;
	private ISpawner mySpawner;
	private IGameMap myGameMap;
	private IScore myScore;
	private IMoney myMoney;
	
	private Collection<KeyCode> myCurrentInput;

	private IEventManager myPoster;

	public GameUniverse(UniverseData data, GlobalSettings settings) {
		myScore = new Score(data.getSettings());
		myMoney = new Money(data.getSettings().getStartingMoney());
		myConditions = new Conditions(data.getSettings().getWinCondition(), data.getSettings().getLossCondition());;
		myGameObjects = new ArrayList<IGameObject>();
		myGameMap = new GameMap(data.getMap());
		mySpawner = new Spawner(data.getSpawns().getWaves(), myGameMap.getTileSize());
		myTowers = new ArrayList<>();
		Collection<TowerData> towerdata = data.getTowers().getTowers();
		for (TowerData d : towerdata) {
			myTowers.add(new Tower(d));
		}
//		Collection<ObjectData> objects = data.getObjects();
//		System.out.println(objects);
//
//		for (ObjectData e: objects) {
//			IGameObject obj = new GameObject(e);
//			obj.getBoundingBox().setWidth(myGameMap.getTileSize() * .75);
//			obj.getBoundingBox().setHeight(myGameMap.getTileSize() * .75);
//			myGameObjects.add(obj);
//		}
		
		//}
		
		// Need event managers
		myGraveYard = new GraveYard(this);
		mySpawnYard = new SpawnYard(this);
		myCurrentInput = new ArrayList<KeyCode>();
	}

	public void registerListeners(IEventManager manager) {
		myConditions.registerEventManager(manager);
		mySpawner.registerEventManager(manager);
		myGraveYard.registerEventManager(manager);
		mySpawnYard.registerEventManager(manager);
		myPoster = manager;
		manager.registerListener(myScore);
		manager.registerListener(myMoney);
		myScore.registerEventManager(manager);
		manager.registerListener(myGameMap);
	}

	@Override
	public Collection<IGameObject> getGameObjects() {
		return Collections.unmodifiableCollection(myGameObjects);
	}

	@Override
	public void addGameObject(IGameObject toAdd) {
		System.out.println("ADD");
		myGameObjects.add(toAdd);
	}

	@Override
	public void receiveKeyPress(KeyCode code) {
		if (!myCurrentInput.contains(code)) {
			myCurrentInput.add(code);
		}
	}

	@Override
	public void receiveKeyRelease(KeyCode code) {
		if (myCurrentInput.contains(code)) {
			myCurrentInput.remove(code);
		}
	}

	@Override
	public void receiveMouseEvent(MouseEvent mouseEvent) {
		// TODO handle mouse input??????
	}

	@Override
	public Collection<KeyCode> getCurrentKeyInput() {
		return Collections.unmodifiableCollection(myCurrentInput);
	}

	@Override
	public void addToSpawnYard(IGameObject toAdd) {
		mySpawnYard.addToYard(toAdd);
	}

	@Override
	public void addToGraveYard(IGameObject toRemove) {
		myGraveYard.addToYard(toRemove);
	}

	@Override
	public void removeFromUniverse(IViewRemover remover) {
		myGraveYard.alterUniverse(remover);
	}

	@Override
	public void addToUniverse(IViewAdder adder) {
		mySpawnYard.alterUniverse(adder);
	}

	@Override
	public IGameMap getMap() {
		return this.myGameMap;
	}

	@Override
	public void removeGameObject(IGameObject obj) {
		myGameObjects.remove(obj);
	}

	@Override
	public SpawnYard getSpawnYard() {
		return mySpawnYard;
	}

	@Override
	public GraveYard getGraveYard() {
		return myGraveYard;
	}

	@Override
	public ISpawner getSpawner() {
		return mySpawner;
	}

	@Override
	public void postEvent(IGameEvent event) {
		myPoster.postEvent(event);
	}

//	TODO : THIS WON'T WORK
	@Override
	public UniverseData saveGame() {
		SpawnerData spawn = mySpawner.saveGame();
		TowerListData towers = saveTowers();
		MapData map = new MapData(myGameMap);
		LevelSettings settings = new LevelSettings(myConditions.getWinCondition(), 
												   myConditions.getLossCondition(), 
												   myMoney.getMoney());
		Collection<ObjectData> objects = new ArrayList<>();
		for (IGameObject o: myGameObjects){
			objects.add(new ObjectData(o));
		}
		
		UniverseData data = new UniverseData(spawn, towers, map, settings, objects);
		return data;
	}

	private TowerListData saveTowers() {
		Collection<TowerData> data = new ArrayList<>();
		for (ITower o : myTowers) {
			data.add(new TowerData(o));
		}
		return new TowerListData(data);
	}

	@Override
	public Collection<TowerData> getAvailableTowers() {
		List<TowerData> towerData = new ArrayList<>();
		for (ITower tower : myTowers) {
			towerData.add(new TowerData(tower));
		}
		return towerData;
	}

	@Override
	public IScore getScore() {
		return myScore;
	}
	
	@Override
	public IMoney getMoney() {
		return myMoney;
	}

	@Override
	public void observeScore(Observer observer) {
		myScore.addObserver(observer);
	}
	
	@Override
	public void observeMoney(Observer observer){
		myMoney.addObserver(observer);
	}

	@Override
	public IGameObject getPlayer() {
		IGameObject player = null;
		for (IGameObject obj: myGameObjects) {
			if (obj.getType().equals(GameObjectType.PLAYER)){
				player = obj;
				break;
			}
		}
		return player;
	}

}
