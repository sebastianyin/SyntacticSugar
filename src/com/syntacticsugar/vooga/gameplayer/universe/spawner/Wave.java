package com.syntacticsugar.vooga.gameplayer.universe.spawner;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.syntacticsugar.vooga.gameplayer.objects.GameObject;
import com.syntacticsugar.vooga.gameplayer.objects.IGameObject;
import com.syntacticsugar.vooga.xml.data.ObjectData;
import com.syntacticsugar.vooga.xml.data.WaveData;

public class Wave implements IWave {
	
	private Queue<IGameObject> objs;
	
	private int myWaveNum;
	
	private int mySpawnRate;
	
	public Wave (WaveData data) {
		objs = new LinkedList<>();
		for(ObjectData o: data.getObjs()) {
			objs.add(new GameObject(o));
		}
		setSpawnRate(data.getSpawnRate());
	}

	@Override
	public IGameObject getObj() {
		return objs.poll();
	}
	
	@Override
	public int getWaveSize() {
		return objs.size();
	}
	
	@Override
	public int getWaveNum() {
		return myWaveNum;
	}

	@Override
	public Collection<IGameObject> getAllObjs() {
		return Collections.unmodifiableCollection(objs);
	}

	public int getSpawnRate() {
		return mySpawnRate;
	}

	public void setSpawnRate(int mySpawnRate) {
		this.mySpawnRate = mySpawnRate;
	}

}
