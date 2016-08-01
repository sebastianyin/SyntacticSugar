package com.syntacticsugar.vooga.xml.data;

import java.util.Collection;

public class UniverseData {
	
	private SpawnerData spawns;
	private TowerListData towers;
	private MapData map;
	private LevelSettings settings;
	private Collection<ObjectData> objects;
	
	public UniverseData(SpawnerData a, TowerListData b, MapData c, LevelSettings d, Collection<ObjectData> e) {
		spawns = a;
		towers = b;
		map = c;
		settings = d;
		setObjects(e);
	}
	
	public SpawnerData getSpawns() {
		return spawns;
	}
	
	public TowerListData getTowers() {
		return towers;
	}
	
	public MapData getMap() {
		return map;
	}
	
	public LevelSettings getSettings() {
		return settings;
	}

	public Collection<ObjectData> getObjects() {
		return objects;
	}

	public void setObjects(Collection<ObjectData> objects) {
		this.objects = objects;
	}
}
