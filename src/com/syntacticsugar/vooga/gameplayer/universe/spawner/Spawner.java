package com.syntacticsugar.vooga.gameplayer.universe.spawner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.syntacticsugar.vooga.gameplayer.event.IGameEvent;
import com.syntacticsugar.vooga.gameplayer.event.implementations.ObjectSpawnEvent;
import com.syntacticsugar.vooga.gameplayer.manager.IEventManager;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.gameplayer.universe.IEventPoster;
import com.syntacticsugar.vooga.xml.data.ObjectData;
import com.syntacticsugar.vooga.xml.data.SpawnerData;
import com.syntacticsugar.vooga.xml.data.WaveData;

public class Spawner implements ISpawner {

	private Queue<Wave> myWaves;
	private Wave myCurrentWave;

	IEventPoster myPoster;

	private int myFrameCount;
	private int mySpawnRate;
	
	private double myObjSize;

	public Spawner(Collection<WaveData> data, double size) {
		myWaves = new LinkedList<>();
		for (WaveData d : data) {
			myWaves.add(new Wave(d));
		}
		
		mySpawnRate = 0;
		myObjSize = size;
		
		if (myWaves.size() != 0 && myWaves.peek().getWaveNum() == 0) {
			nextWave();
		}
		
	}

	@Override
	public void registerEventManager(IEventManager eventmanager) {
		myPoster = eventmanager;

	}

	@Override
	public void nextWave() {
		if (myWaves != null && myWaves.size() != 0) {
			myCurrentWave = myWaves.poll();
			mySpawnRate = myCurrentWave.getSpawnRate();
		}
	}

	@Override
	public int getWaveNum() {
		return myCurrentWave.getWaveNum();
	}

	@Override
	public void update() {
		if (myCurrentWave == null) {
			return;
		}

		if (mySpawnRate == 0) {
			while (myCurrentWave.getWaveSize() != 0) {
				spawn();
			}
			return;
		}

		if (myFrameCount >= mySpawnRate && myCurrentWave.getWaveSize() != 0) {
			spawn();
			myFrameCount = 0;
		}
		myFrameCount++;
	}

	@Override
	public Wave getCurrentWave() {
		return myCurrentWave;
	}

	private void spawn() {
		IGameObject obj = myCurrentWave.getObj();
		obj.getBoundingBox().setWidth(myObjSize * .75);
		obj.getBoundingBox().setHeight(myObjSize * .75);
		ObjectSpawnEvent event = new ObjectSpawnEvent(obj);
		myPoster.postEvent(event);
	}

	@Override
	public void onEvent(IGameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public SpawnerData saveGame() {
		Collection<WaveData> waves = new ArrayList<>();
		for (Wave w : myWaves) {
			Collection<ObjectData> objs = new ArrayList<>();
			for (IGameObject o: w.getAllObjs()) {
				ObjectData obj = new ObjectData(o);
				obj.setImagePath(obj.getImagePath());
				objs.add(new ObjectData(o));
			}
			waves.add(new WaveData(objs, w.getSpawnRate()));
		}
		return new SpawnerData(waves);
	}
	
	@Override
	public Integer getSpawnRate() {
		return mySpawnRate;
	}

}
