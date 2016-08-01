package com.syntacticsugar.vooga.authoring.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syntacticsugar.vooga.authoring.objectediting.IDataClipboard;
import com.syntacticsugar.vooga.util.gui.factory.AlertBoxFactory;
import com.syntacticsugar.vooga.xml.data.LevelSettings;
import com.syntacticsugar.vooga.xml.data.MapData;
import com.syntacticsugar.vooga.xml.data.ObjectData;
import com.syntacticsugar.vooga.xml.data.SpawnerData;
import com.syntacticsugar.vooga.xml.data.TowerData;
import com.syntacticsugar.vooga.xml.data.TowerListData;
import com.syntacticsugar.vooga.xml.data.UniverseData;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class LevelTabManager {

	private TabPane myTabPane;
	private Map<Tab, LevelEditor> myLevelMap;
	private IDataClipboard iObject;
	private IDataClipboard myClip;

	public LevelTabManager(IDataClipboard clip) {
		myClip = clip;
		myLevelMap = new HashMap<Tab, LevelEditor>();
		myTabPane = new TabPane();
		iObject = clip;
		addNewLevel();
	}

	public void addNewLevel() {
		LevelEditor newLevel = null;
		try {
			newLevel = new LevelEditor(iObject);
		} catch (Exception e) {
			AlertBoxFactory.createObject(e.getMessage());
			e.printStackTrace();
			return;
		}

		Tab newLevelTab = new Tab("");
		newLevelTab.setContent(newLevel.getContent());
		newLevelTab.setOnClosed(e -> removeLevel(newLevelTab));

		myLevelMap.put(newLevelTab, newLevel);

		myTabPane.getTabs().add(newLevelTab);
		myTabPane.getSelectionModel().select(newLevelTab);

		updateLevelNumbers();
	}

	public void addNewLevelsFromData(Collection<UniverseData> universes) {
		myLevelMap.clear();
		myTabPane.getTabs().clear();
		
		int i = 1;
		for (UniverseData d : universes) {
			Tab t = new Tab("Wave " + i);
			try {
				LevelEditor l = new LevelEditor(myClip);
				l.loadMap(d.getMap()); // im
				l.setConditions(d.getSettings()); // im
				l.setSpawners(d.getSpawns()); // im
				l.setTowers(d.getTowers());// im
				t.setContent(l.getContent());

				t.setOnClosed(e -> removeLevel(t));

				myLevelMap.put(t, l);

				myTabPane.getTabs().add(t);
				myTabPane.getSelectionModel().select(t);

				updateLevelNumbers();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void loadMap(MapData loadedMap) {
		myLevelMap.get(myTabPane.getSelectionModel().getSelectedItem()).loadMap(loadedMap);
	}

	public TabPane getTabPane() {
		return myTabPane;
	}

	public ArrayList<LevelEditor> getLevels() {
		ArrayList<LevelEditor> levels = new ArrayList<LevelEditor>();
		for (LevelEditor level : myLevelMap.values()) {
			levels.add(level);
		}
		return levels;
	}

	private void removeLevel(Tab levelTab) {
		myLevelMap.remove(levelTab);
		updateLevelNumbers();
	}

	private void updateLevelNumbers() {
		for (int i = 0; i < myTabPane.getTabs().size(); i++) {
			Tab t = myTabPane.getTabs().get(i);
			t.setText(String.format("%s %s", "Level", i + 1));
		}
	}

	public Collection<UniverseData> getAllUniverseData() {

		List<UniverseData> game = new ArrayList<UniverseData>();

		for (Tab t : myTabPane.getTabs()) {

			SpawnerData spawner = myLevelMap.get(t).getSpawnerQueues();
			MapData map = myLevelMap.get(t).getMapData();
			TowerListData tower = myLevelMap.get(t).getTowerList();
			LevelSettings conditions = myLevelMap.get(t).getConditions();
			List<ObjectData> onScreen = new ArrayList<>();
			UniverseData universe = new UniverseData(spawner, tower, map, conditions, onScreen);

			game.add(universe);
		}
		return game;

	}

	public MapData getIndividualMapData() {

		return myLevelMap.get(myTabPane.getSelectionModel().getSelectedItem()).getMapData();
	}

	public Map<Tab, LevelEditor> getCurrentLevelEditor() {
		return myLevelMap;
	}

	public void addCurrentSpawner(ObjectData data) {
		myLevelMap.get(myTabPane.getSelectionModel().getSelectedItem()).addToSpawner(data);

	}

	public void addCurrentTower(TowerData data) {
		myLevelMap.get(myTabPane.getSelectionModel().getSelectedItem()).addToTowers(data);

	}

}
