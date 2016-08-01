package com.syntacticsugar.vooga.xml.data;

import java.util.Collection;

public class SpawnerData {
	
	private Collection<WaveData> waves;
	
	public SpawnerData (Collection<WaveData> data) {
		waves = data;
	}
	
	public Collection<WaveData> getWaves() {
		return waves;
	}

}
