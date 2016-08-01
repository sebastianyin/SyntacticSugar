package com.syntacticsugar.vooga.authoring.level.spawner;

public class SpawnerPair {

	private int mySpawnRate;
	private SpawnerView mySpawnView;
	
	public SpawnerPair(int i, SpawnerView spawnView) {
		mySpawnRate = i;
		mySpawnView = spawnView;
	}
	
	public int getRate() {
		return mySpawnRate;
	}
	
	public SpawnerView getSpawnerView() {
		return mySpawnView;
	}
	
	public void setSpawnRate(int i) {
		this.mySpawnRate = i;
	}
	
	public void setSpawnView(SpawnerView sv) {
		this.mySpawnView = sv;
	}
}
